import java.io.*;
import java.util.*;

public class Main {
    public static BufferedWriter bw;
    public static int N;
    public static int M;
    public static int[] src;
    public static int[] tgt;
    public static boolean[] select;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N];
        tgt = new int[M];
        select = new boolean[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) src[i] = Integer.parseInt(st.nextToken());
        
        Arrays.sort(src);
        func(0);

        bw.close();
    }

    public static void func(int tgtIdx) throws IOException {
        if (tgtIdx == M) {
            for (int i = 0; i < M; i++) bw.write(tgt[i] + " ");
            bw.newLine();
            return;
        }

        int lastSelected = -1;
        for (int i = 0; i < N; i++) {
            if (!select[i] && (lastSelected == -1 || src[i] != src[lastSelected])) {
                tgt[tgtIdx] = src[i];
                select[i] = true;
                func(tgtIdx + 1);
                select[i] = false;
                lastSelected = i;
            }
        }
    }
}