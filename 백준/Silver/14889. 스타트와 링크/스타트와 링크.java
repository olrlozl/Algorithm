import java.io.*;
import java.util.*;

public class Main {
    public static int[][] arr;
    public static int min = Integer.MAX_VALUE;

    public static void Combination(boolean[] team, int idx, int cnt) {
        if (cnt == team.length / 2) {
            int score = 0;
            for (int i = 0; i < team.length - 1; i++) {
                for (int j = i + 1; j < team.length; j++) {
                    if (team[i] && team[j]) score += arr[i][j] + arr[j][i];
                    else if (!team[i] && !team[j]) score -= arr[i][j] + arr[j][i];
                }
            }

            min = Math.min(min, Math.abs(score));
            return;
        }

        if (idx == team.length) return;

        team[idx] = true;
        Combination(team, idx + 1, cnt + 1);
        team[idx] = false;
        Combination(team, idx + 1, cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Combination(new boolean[n], 0, 0);

        bw.write(min + "");
        bw.close();
    }
}