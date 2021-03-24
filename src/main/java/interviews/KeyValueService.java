package interviews;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class KeyValueService {
    private HashMap<String, String>  kvStore = new HashMap<>();
    private String fName ;

    public  KeyValueService(String fName) throws FileNotFoundException {
        this.fName = fName;
        Scanner rdr = new Scanner(new File(fName));
        while ( rdr.hasNextLine() ) {
            String[] data = rdr.nextLine().split(",");
            if ( data.length > 1 ) {
                kvStore.put(data[0], data[1]);
            }
        }
    }

    public synchronized String get(String key) {
        return kvStore.get(key);
    }

    public synchronized Integer getSize() {
        return kvStore.entrySet().size();
    }

    public void put(String key, String value) throws FileNotFoundException {
        synchronized ( fName ) {
            PrintWriter pw = new PrintWriter(new FileOutputStream(fName, true));
            pw.append("\n" + key + "," + value);
            pw.flush();
        }
        synchronized (kvStore) {
            kvStore.put(key, value);
        }
    }

    public void backup() {
        new Thread( () -> {
                try {
                    PrintWriter pw = new PrintWriter(
                            new FileOutputStream(
                                    "src/main/java/interviews/kvstore_"+System.currentTimeMillis()+".txt"
                                    ,true
                            )
                    );
                    Boolean firstLine = true;
                    HashMap<String,String> cln = (HashMap<String, String>) kvStore.clone();
                    for( Map.Entry<String,String> memData :  cln.entrySet() ) {
                        if ( firstLine ) {
                            pw.append(memData.getKey()+","+memData.getValue());
                            firstLine = false;
                        } else {
                            pw.append("\n" + memData.getKey()+","+memData.getValue());
                        }
                        Thread.sleep(1000);
                    }
                    pw.flush();
                } catch (FileNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        ).start();
    }

    public static void main(String[] args) throws FileNotFoundException {
        KeyValueService kvs = new KeyValueService("src/main/java/interviews/kvstore.txt");
        System.out.println(kvs.get("Key1"));
        addKeys(kvs, kvs.getSize(), kvs.getSize() + 10);
        kvs.backup();
        addKeys(kvs, kvs.getSize(), kvs.getSize() + 10);
    }

    public static void addKeys(KeyValueService kvs, Integer startRange, Integer endRange) {
        IntStream.range(startRange, endRange).forEach( i -> {
            try {
                kvs.put("Key"+i, "Value"+i);
                Thread.sleep(1000);
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
