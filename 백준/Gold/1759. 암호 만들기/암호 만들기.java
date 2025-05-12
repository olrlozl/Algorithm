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

        for (int i = 1; i <= N - 2; i++) {
            comb(0, i, N - i, 0, 0);
        }

        for (String t : treeSet) {
            bw.write(t + "\n");
        }
        bw.close();
    }

    public static void comb(int startIdx, int M, int J, int curM, int curJ) {
        if (curM == M && curJ == J) {
            addPW();
            return;
        }

        for (int i = startIdx; i < C; i++) {
            if (!select[i]) {
                if ("aeiou".indexOf(src[i]) != -1 && curM < M) {
                    select[i] = true;
                    comb(i + 1, M, J, curM + 1, curJ);
                    select[i] = false;
                } else if ("aeiou".indexOf(src[i]) == -1 && curJ < J) {
                    select[i] = true;
                    comb(i + 1, M, J, curM, curJ + 1);
                    select[i] = false;
                }
            }
        }
    }

    public static void addPW() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < C; i++) {
            if (select[i]) {
                sb.append(src[i]);
            }
        }
        treeSet.add(sb.toString());
    }
}