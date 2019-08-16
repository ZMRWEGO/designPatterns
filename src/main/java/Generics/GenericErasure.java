package Generics;

import java.util.HashMap;
import java.util.Map;

public class GenericErasure {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("hj", "fds");
        System.out.println(map.get("hj"));
     }
}
