import java.io.*;
import java.util.*;

public class Main {
    public static int N, C;
    public static char[] src;
    public static boolean[] select;
    public static TreeSet<String> treeSet = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        src = new char[C];
        select = new boolean[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            src[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(src);

        comb(0, 0);

        for (String t : treeSet) {
            bw.write(t + "\n");
        }
        bw.close();
    }

    public static void comb(int startIdx, int tgtCnt) {
        if (tgtCnt == N) {
            addPW();
            return;
        }

        for (int i = startIdx; i < C; i++) {
            if (!select[i]) {
                select[i] = true;
                comb(i + 1, tgtCnt + 1);
                select[i] = false;
            }
        }
    }

    public static void addPW() {
        int cnt = 0;
        for (int i = 0; i < C; i++) {
            if (select[i]) {
                if ("aeiou".indexOf(src[i]) != -1) cnt++;
            }
        }

        if (cnt >= 1 && N - cnt >= 2) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < C; i++) {
                if (select[i]) {
                    sb.append(src[i]);
                }
            }
            treeSet.add(sb.toString());
        }
    }
}