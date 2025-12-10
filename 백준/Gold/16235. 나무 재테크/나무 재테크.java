import java.io.*;
import java.util.*;

public class Main {
    static int[][] S2D2;
    static int[][] nutrient;
    static PriorityQueue<Tree> trees = new PriorityQueue<>();
    static PriorityQueue<Tree> new_trees = new PriorityQueue<>();
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

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(r, c, age));
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(trees.size());
    }

    public static class Tree implements Comparable<Tree> {
        int r;
        int c;
        int age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static void spring() {
        while (!trees.isEmpty()) {
            Tree t = trees.poll();

            if (t.age <= nutrient[t.r][t.c]) {
                nutrient[t.r][t.c] -= t.age;
                t.age++;
                new_trees.add(t);
            } else {
                dead.add(t);
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
        while (!new_trees.isEmpty()) {
            Tree t = new_trees.poll();
            trees.add(t);

            if (t.age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nr = t.r + delta[i][0];
                    int nc = t.c + delta[i][1];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    trees.add(new Tree(nr, nc, 1));
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
}