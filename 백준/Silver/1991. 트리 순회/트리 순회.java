import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static HashMap<Character, Children> tree = new HashMap<>();
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree.put(parent, new Children(left, right));
        }

        preOrder('A');
        bw.newLine();
        inOrder('A');
        bw.newLine();
        postOrder('A');
        bw.close();
    }

    public static class Children{
        char left;
        char right;

        public Children(char left, char right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void preOrder(char node) throws IOException {
        if (node == '.') return;
        bw.write(node);
        preOrder(tree.get(node).left);
        preOrder(tree.get(node).right);
    }

    public static void inOrder(char node) throws IOException {
        if (node == '.') return;
        inOrder(tree.get(node).left);
        bw.write(node);
        inOrder(tree.get(node).right);
    }

    public static void postOrder(char node) throws IOException {
        if (node == '.') return;
        postOrder(tree.get(node).left);
        postOrder(tree.get(node).right);
        bw.write(node);
    }
}