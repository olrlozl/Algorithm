import java.io.*;
import java.util.*;

public class Main {
    public static int[][][] arr;
    public static int[][] delta = {{-1, 0, 0}, {1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
    public static int min = Integer.MAX_VALUE;

    public static class point {
        int x;
        int y;
        int z;

        public point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void bfs(int[][][] cube) {
        Queue<point> queue = new LinkedList<>();
        int[][][] visit = new int[5][5][5];

        if (cube[0][0][0] == 0) return;

        queue.add(new point(0, 0, 0));

        while (!queue.isEmpty()) {

            point now = queue.poll();

            for(int d = 0; d < 6; d++) {
                int nx = now.x + delta[d][0];
                int ny = now.y + delta[d][1];
                int nz = now.z + delta[d][2];

                if (0 > nx || nx >= 5 || 0 > ny || ny >= 5 || 0 > nz || nz >= 5 || visit[nx][ny][nz] != 0 || cube[nx][ny][nz] == 0) continue;

                queue.add(new point(nx, ny, nz));
                visit[nx][ny][nz] = visit[now.x][now.y][now.z] + 1;

                if (nx == 4 && ny == 4 && nz == 4) {
                    min = Math.min(min, visit[4][4][4]);
                    return;
                }
            }
        }
    }

    public static int[][] rotate(int[][] arr, int turn) {
        // 원본 배열 복사
        int[][] board = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = arr[i][j];
            }
        }

        int[][] rotateBoard = new int[5][5];

        for (int t = 0; t < turn; t++) {
            // 한 번 회전 시키고
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    rotateBoard[i][j] = board[4 - j][i];
                }
            }
            // 복사하고
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    board[i][j] = rotateBoard[i][j];
                }
            }
        }

        return board;
    }

    public static void permutation(int[] tgt, int[] dir, boolean[] visit, int idx) {
        if (idx == tgt.length) {
//            System.out.println("tgt " + Arrays.toString(tgt));
//            System.out.println("dir " + Arrays.toString(dir));

//            for (int i = 0; i < 5; i++) {
//                for (int j = 0; j < 5; j++) {
//                    System.out.println(Arrays.toString(arr[i][j]));
//                }
//                System.out.println();
//            }
//            System.out.println("----------------");

            int[][][] cube = new int[5][5][5];

            for (int i = 0; i < 5; i++) cube[i] = rotate(arr[tgt[i]], dir[i]);

            bfs(cube);

            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!visit[i]) {
                tgt[idx] = i;
                visit[i] = true;

                for (int j = 0; j < 4; j++) {
                    dir[idx] = j;
                    permutation(tgt, dir, visit, idx + 1);
                }

                visit[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        arr = new int[5][5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        permutation(new int[5], new int[5], new boolean[5], 0);

        bw.write((min == Integer.MAX_VALUE ? -1 : min) + "");
        bw.close();

    }
}