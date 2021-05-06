package language.collections;

import java.util.*;
import java.util.stream.*;

public class Mapped {
    public static void main(String[] args) {
        //HashMap | ConcurrentHashMap, LinkedHashMap, TreeMap | ConcurrentSkipListMap
        Map<String, String> conn = new TreeMap<>( (key1, key2) -> {
            return key2.compareTo(key1); // Sort in reverse order of keys.
        });

        //Put
        conn.put("mongo", "mongodb://mongodb0.example.com:27017");
        conn.putIfAbsent("mongo", "mongodb://mongodb0.example.com:27017" );
        conn.put("postgresql", "127.0.0.1");

        //Replace
        conn.replace("mongo","mongodb://mongodb1.example.com:27017" );

        //Compute
        conn.computeIfAbsent("mysql", (key) -> {  //Idempotent
            return "jdbc:"+key+"://"+key+".db.server:3306/my_database";
        });

        conn.computeIfPresent("postgresql", (key, value) -> {  //Not idempotent
            return "jdbc:"+key+"://"+value+":9000/database";
        });

        //Get
        System.out.println(conn.getOrDefault("oracle", "jdbc:oracle:thin:@db.server:1521:orcl"));

        //Remove
        conn.remove("mongo", "mongodb://mongodb0.example.com:27017"); // Only when both key , value matches

        System.out.println(conn);

        //Merge
        Map<String, String> servers = new HashMap<>();
        servers.put("frontend", "https://a12jnjj.aws.com");
        servers.put("backend", "https://awe9888.aws.com");
        servers.put("database", "https://oci90901.oraclecloud.com");
        servers.put("mongo","mongodb://mongodb2.example.com:27017" ); //Collision

        servers.forEach( ( s_key, s_value ) -> {
            conn.merge( s_key
                      , s_value
                      , (old_value , new_value) -> { //Merge function
                          return new_value; // Override with new value
                        }
                      );
            }
        );
        System.out.println(conn);

        //Sort by values
        HashMap<String,String> conn_value_sorted = conn.entrySet()
            .stream()
            .sorted( Map.Entry.comparingByValue() )
            .collect(Collectors.toMap(
                          e -> e.getKey() // Key mapper
                        , e -> e.getValue() // Value mapper
                        , (old_val, new_val) -> { return new_val; } // Merge function
                        , () -> new LinkedHashMap<>() //Define map structure
                    )
            );

        System.out.println(conn_value_sorted);
    }
}
