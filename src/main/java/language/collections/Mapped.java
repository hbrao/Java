package language.collections;

import java.util.*;
import java.util.stream.*;

public class Mapped {
    public static void main(String[] args) {
        //HashMap | ConcurrentHashMap
        Map<String, String> conn = new HashMap<>();

        //NOTE: put / replace /  compute  methods returns the reference to value
        //Put
        conn.put("mongo", "mongodb://mongodb0.example.com:27017");
        conn.put("postgresql", "127.0.0.1");

        //Put if key is NOT present.
        String existingValue = conn.putIfAbsent("mongo", "mongodb://mongodb3.example.com:27017" );
        System.out.println(existingValue);

        //Contains
        System.out.println(conn.containsKey("mongo"));

        //Replace NOTE: Only when key is present.
        String oldValue = conn.replace("mongo","mongodb://mongodb1.example.com:27017" );
        System.out.println(oldValue);

        //Compute
        String computedValue1 = conn.computeIfAbsent("mysql", (key) -> {  //Idempotent
            return "jdbc:"+key+"://"+key+".db.server:3306/my_database";
        });

        String computedValue2 = conn.computeIfPresent("postgresql", (key, value) -> {  //Not idempotent
            return "jdbc:"+key+"://"+value+":9000/database";
        });

        //Get
        String defaultValue = conn.getOrDefault("oracle", "jdbc:oracle:thin:@db.server:1521:orcl");

        //Remove
        conn.remove("mongo", "mongodb://mongodb0.example.com:27017"); // Only when both key , value matches

        System.out.println(conn);

        //Merge
        //NOTE: If merge function returns null key gets deleted.
        Map<String, String> servers = new HashMap<>();
        servers.put("frontend", "https://a12jnjj.aws.com");
        servers.put("backend", "https://awe9888.aws.com");
        servers.put("database", "https://oci90901.oraclecloud.com");
        servers.put("mongo","mongodb://mongodb2.example.com:27017" ); //Collision

        servers.forEach(( s_key, s_value ) -> {
                String newValue = conn.merge( s_key
                      , s_value
                      , (old_value , new_value) -> { //Merge function
                          return new_value; // Override with new value NOTE: If we return null entry gets deleted.
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

        //Replace all values to empty string
        conn_value_sorted.replaceAll( (k, v) -> "empty" );
        System.out.println(conn_value_sorted);
    }
}
