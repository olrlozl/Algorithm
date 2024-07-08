import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = br.readLine();
        String b = br.readLine();

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        
        for (char c : a.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (char c : b.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }

        int result = 0;
        for (char key : map1.keySet()) {
            result += Math.abs(map1.get(key) - map2.getOrDefault(key, 0));
        }
        for (char key : map2.keySet()) {
            if (map1.getOrDefault(key, 0) > 0) continue;
            result += map2.get(key);
        }
        
        bw.write(result + "");
        bw.close();
    }
}