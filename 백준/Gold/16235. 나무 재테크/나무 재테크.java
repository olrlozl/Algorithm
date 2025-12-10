import java.io.*;
import java.util.*;

public class Main {
    static int[][] S2D2;
    static int[][] nutrient;
    static PriorityQueue<Integer>[][] treeAge;
    static ArrayList<Tree> dead = new ArrayList<>();
    static int[][] delta = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nutrient = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(nutrient[i], 5);
        }

        S2D2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S2D2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        treeAge = new PriorityQueue[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                treeAge[i][j] = new PriorityQueue<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            treeAge[r - 1][c - 1].add(age);
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(count());
    }

    public static class Tree {
        int r;
        int c;
        int age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }
    }

    public static void spring() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                PriorityQueue<Integer> newPQ = new PriorityQueue<>();

                while (!treeAge[i][j].isEmpty()) {
                    int age = treeAge[i][j].poll();

                    if (age <= nutrient[i][j]) {
                        nutrient[i][j] -= age;
                        newPQ.add(age + 1);
                    } else {
                        dead.add(new Tree(i, j, age));
                    }
                }

                treeAge[i][j] = newPQ;
            }
        }
    }

    public static void summer() {
        for (Tree tree : dead) {
            nutrient[tree.r][tree.c] += tree.age / 2;
        }
        dead.clear();
    }

    public static void fall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                for (int age : treeAge[i][j]) {
                    if (age % 5 != 0) continue;

                    for (int k = 0; k < 8; k++) {
                        int nr = i + delta[k][0];
                        int nc = j + delta[k][1];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                        treeAge[nr][nc].add(1);
                    }
                }
            }
        }
    }

    public static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nutrient[i][j] += S2D2[i][j];
            }
        }
    }

    public static int count() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += treeAge[i][j].size();
            }
        }
        return cnt;
    }
}