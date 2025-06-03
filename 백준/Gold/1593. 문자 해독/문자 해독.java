import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int lenW = Integer.parseInt(st.nextToken());
        int lenS = Integer.parseInt(st.nextToken());
        String w = br.readLine();
        String s = br.readLine();

        HashMap<Character, Integer> mapW = new HashMap<>();
        HashMap<Character, Integer> mapS = new HashMap<>();
        int cnt = 0;

        for (int i = 0; i < lenW; i++) {
            mapW.put(w.charAt(i), mapW.getOrDefault(w.charAt(i), 0) + 1);
            mapS.put(s.charAt(i), mapS.getOrDefault(s.charAt(i), 0) + 1);
        }

        int start = 0;
        while (true) {
            if (mapW.equals(mapS)) cnt++;
            if (start == lenS - lenW) break;

            // 가장 왼쪽 문자 제거
            char c = s.charAt(start++);
            if (mapS.get(c) == 1) mapS.remove(c);
            else mapS.replace(c, mapS.get(c) - 1);

            // 가장 오른쪽 문자 추가
            mapS.put(s.charAt(start + lenW - 1), mapS.getOrDefault(s.charAt(start + lenW - 1), 0) + 1);

        }

        bw.write(cnt + "");
        bw.close();
    }
}