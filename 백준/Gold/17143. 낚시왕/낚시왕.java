import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M;
    static Shark[][] sea;
    static int result;
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sea = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(r, c, s, d, z);
            sea[r][c] = shark;
        }

        simulate(1);
        System.out.println(result);
    }

    public static class Shark{
        int r; // 행
        int c; // 열
        int s; // 속력
        int d; // 방향
        int z; // 크기

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void simulate(int pc) {
        if (pc == C + 1) return;
        hook(pc);
        sharkMove();
        simulate(pc + 1);
    }

    public static void hook(int pc) {
        for (int r = 1; r <= R; r++) {
            Shark shark = sea[r][pc];
            if (shark != null) {
                sea[r][pc] = null;
                result += shark.z;
                break;
            }
        }
    }

    public static int changeDir(int d) {
        switch(d) {
            case 1: return 2;
            case 2: return 1;
            case 3: return 4;
            case 4: return 3;
            default: return 0;
        }
    }

    public static void sharkMove() {
        Shark[][] new_sea = new Shark[R + 1][C + 1];

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                Shark shark = sea[r][c];

                if (shark == null) continue;


                int dist = shark.s;
                if (shark.d == 1 || shark.d == 2) {
                    dist %= 2 * R - 2;
                } else {
                    dist %= 2 * C - 2;
                }

                while (dist > 0) {
                    int nr = shark.r + dr[shark.d];
                    int nc = shark.c + dc[shark.d];

                    if (nr < 1 || nr > R || nc < 1 || nc > C) {
                        shark.d = changeDir(shark.d);
                        nr = shark.r + dr[shark.d];
                        nc = shark.c + dc[shark.d];
                    }

                    shark.r = nr;
                    shark.c = nc;
                    dist--;
                }

                if (new_sea[shark.r][shark.c] == null) {
                    new_sea[shark.r][shark.c] = shark;
                } else if (new_sea[shark.r][shark.c].z < shark.z) {
                    new_sea[shark.r][shark.c] = shark;
                }
            }
        }

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                sea[r][c] = new_sea[r][c];
            }
        }
    }
}