import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 비트마스킹
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int M = Integer.parseInt(br.readLine());
        int S = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            switch (order) {
                case "add":
                    S |= (1 << Integer.parseInt(st.nextToken()));
                    break;
                case "remove":
                    S &= ~(1 << Integer.parseInt(st.nextToken()));
                    break;
                case "check":
                    bw.write(((S & (1 << Integer.parseInt(st.nextToken()))) == 0 ? 0 : 1) + "\n");
                    break;
                case "toggle":
                    S ^= (1 << Integer.parseInt(st.nextToken()));
                    break;
                case "all":
                    S = (1 << 22) - 1;
                    break;
                case "empty":
                    S = 0;
                    break;
            }
        }
        bw.close();
    }
}