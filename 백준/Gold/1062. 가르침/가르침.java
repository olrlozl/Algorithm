import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int K;
    public static String[] words;
    public static boolean[] learn;
    public static int maxCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            s = s.replace("anta", "");
            s = s.replace("tica", "");
            words[i] = s;
        }

        learn = new boolean[26];
        learn['a' - 'a'] = true;
        learn['n' - 'a'] = true;
        learn['t' - 'a'] = true;
        learn['i' - 'a'] = true;
        learn['c' - 'a'] = true;

        backtracking(0, 0);

        bw.write(maxCnt + "");
        bw.close();
    }

    public static void backtracking(int index, int tgtCnt){
        if (tgtCnt == K - 5) {
            int cnt = 0;
            for (String word : words) {
                boolean read = true;
                for (int i = 0; i < word.length(); i++) {
                    if (!learn[word.charAt(i) - 'a']) {
                        read = false;
                        break;
                    }
                }
                if (read) cnt++;
            }
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }
        for (int i = index; i < 26; i++) {
            if (!learn[i]) {
                learn[i] = true;
                backtracking(i, tgtCnt + 1);
                learn[i] = false;
            }
        }
    }
}