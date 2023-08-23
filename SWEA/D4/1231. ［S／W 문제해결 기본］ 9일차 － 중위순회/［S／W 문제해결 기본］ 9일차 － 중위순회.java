import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void inOrder(char[] tree, int idx) throws Exception {

        if (idx < tree.length) { // 범위를 벗어나지 않았다면
            inOrder(tree, idx * 2); // 왼쪽 자식 방문
            bw.write(tree[idx]+""); // 부모 방문
            inOrder(tree, idx * 2 + 1); // 오른쪽 자식 방문
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) { // 테스트 케이스 10개
            int N = Integer.parseInt(br.readLine()); // 정점 개수
            char[] tree = new char[N + 1]; // 트리

            for (int n = 0; n < N; n++) { // 트리 채우기
                st = new StringTokenizer(br.readLine()); // 한 줄 입력 받기
                tree[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0); // 트리[정점번호] = 정점문자
            }

            bw.write("#" + t + " ");
            inOrder(tree, 1);
            bw.newLine();
        }
        bw.close();
    }
}