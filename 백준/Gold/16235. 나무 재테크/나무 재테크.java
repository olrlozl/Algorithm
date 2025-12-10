import java.io.*;
import java.util.*;

public class Main {
    static int[][] S2D2;
    static int[][] nutrient;
    static Deque<Tree> trees;
    static Deque<Tree> new_trees = new ArrayDeque<>(); // 번식할 나무
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

        S2D2 = new int[N][N];
        nutrient = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S2D2[i][j] = Integer.parseInt(st.nextToken());
                nutrient[i][j] = 5;
            }
        }

        ArrayList<Tree> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            list.add(new Tree(r, c, age));
        }
        Collections.sort(list);
        trees = new ArrayDeque<>(list);

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
        int size = trees.size();

        for (int i = 0; i < size; i++) {
            Tree t = trees.poll();

            if (t.age <= nutrient[t.r][t.c]) {
                nutrient[t.r][t.c] -= t.age;
                t.age++;
                trees.add(t);

                if (t.age % 5 == 0) {
                    new_trees.add(t);
                }
            } else {
                dead.add(t);
            }
        }
    }

    public static void summer() {
        for (Tree t : dead) {
            nutrient[t.r][t.c] += t.age / 2;
        }
        dead.clear();
    }

    public static void fall() {
        while (!new_trees.isEmpty()) {
            Tree t = new_trees.poll();

            for (int i = 0; i < 8; i++) {
                int nr = t.r + delta[i][0];
                int nc = t.c + delta[i][1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                trees.addFirst(new Tree(nr, nc, 1)); // 맨 앞에 넣어서 정렬 맞추기
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