import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] seat;
    public static Student[] students;
    public static int[][] delta = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        seat = new int[N][N];
        students = new Student[N * N];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            students[i] = new Student(num);
            for (int j = 0; j < 4; j++) {
                students[i].favorite.add(Integer.parseInt(st.nextToken()));
            }
        }

        sit();
        bw.write(calSatisfaction() + "");
        bw.close();
    }

    public static void sit() {
        for (Student student : students) {
            int x = -1;
            int y = -1;
            int maxCntNearFavorite = -1;
            int maxCntNearEmpty = -1;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // 비어있는 칸 중에서
                    if (seat[r][c] == 0) {
                        int cntNearFavorite = 0;
                        int cntNearEmpty = 0;
                        for (int d = 0; d < 4; d++) {
                            int nr = r + delta[d][0];
                            int nc = c + delta[d][1];
                            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                            // 인접한 칸에 좋아하는 학생이 있다면
                            if(student.favorite.contains(seat[nr][nc])) cntNearFavorite++;
                            // 인접한 칸이 비어있다면
                            else if (seat[nr][nc] == 0) cntNearEmpty++;
                        }
                        // 인접한 칸에 좋아하는 학생이 현재까지 가장 많다면
                        if (cntNearFavorite > maxCntNearFavorite) {
                            maxCntNearFavorite = cntNearFavorite;
                            maxCntNearEmpty = cntNearEmpty;
                            x = r;
                            y = c;
                        }
                        // 인접한 칸 중에서 비어있는 칸이 현재까지 가장 많다면
                        else if (cntNearFavorite == maxCntNearFavorite && cntNearEmpty > maxCntNearEmpty) {
                            maxCntNearEmpty = cntNearEmpty;
                            x = r;
                            y = c;
                        }
                    }
                }
            }
            // 좌석배정
            seat[x][y] = student.num;
            student.r = x;
            student.c = y;
        }
    }

    public static int calSatisfaction() {
        int satisfaction = 0;
        for (Student student : students) {
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nr = student.r + delta[i][0];
                int nc = student.c + delta[i][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (student.favorite.contains(seat[nr][nc])) cnt++;
            }
            if (cnt > 0) satisfaction += Math.pow(10, cnt - 1);
        }
        return satisfaction;
    }

    public static class Student{
        int num;
        List<Integer> favorite;
        int r;
        int c;

        public Student (int num) {
            this.num = num;
            this.favorite = new ArrayList<>();
        }
    }
}