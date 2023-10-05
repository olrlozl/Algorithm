import java.io.*;
import java.util.*;

public class Main {
    public static int[][] arr; // 입력 배열
    public static int[] tgt; // 놀이판 결합 순서
    public static int[][] delta = {{1, 0}, {0, 1}, {0, -1}, {-1,0}}; // 하 우 좌 상
    public static boolean pass; // 구슬 통과 가능 여부
    public static int cnt; // 구슬이 통과 가능하도록 놀이판을 결합하는 방법의 수
    
    public static void dfs(int[][] board, boolean[][] visit, int r, int c) {
        if (r == 4) { // 마지막 줄까지 구슬이 이동했다면
            pass = true; // 구슬 통과 가능!
            return; // 종료
        }
        
        visit[r][c] = true; // 현재 위치 방문 처리
        
        // 이웃한 위치 중에서
        for (int d = 0; d < 4; d++) {
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];
            // 범위를 벗어나지 않고, 아직 방문하지 않은, 구멍이 있다면
            if (0 <= nr && nr < 5 && 0 <= nc && nc < 10 && !visit[nr][nc] && board[nr][nc] == 0) {
                dfs(board, visit, nr, nc); // dfs 재귀 호출
            }
        }
    }
    
    public static void Permutation(int[] tgt, boolean[] select, int tgtIdx) {
        // 기저조건
        if (tgtIdx == tgt.length) { // tgt이 다 채워졌다면
            // 생성한 순열의 순서대로 놀이판 결합
            int[][] board = new int[5][10];
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 10; c++) {
                    board[r][c] = arr[tgt[r]][c];
                }
            }
            
            pass = false; // 구슬 통과 가능 여부 초기화
            
            // 맨 윗줄 구멍에 구슬 넣기
            for (int c = 0; c < 10; c++) {
                if (board[0][c] == 0) { // 구멍이라면
                    if (pass) break; // 이미 구슬 통과 가능하다면, 다음 칸 확인 불필요
                    boolean[][] visit = new boolean[5][10];
                    dfs(board, visit, 0, c); // dfs 호출
                }
            }
            
            if (pass) cnt++; // 구슬 통과 가능하다면 cnt 증가
            
            return; // 종료
        }
        
        for (int i = 0; i < 5; i++) { // 모든 src에 대하여
            if (!select[i]) { // 아직 선택하지 않았다면
                tgt[tgtIdx] = i; // tgtIdx 위치에 넣고
                select[i] = true; // 선택 처리
                Permutation(tgt, select, tgtIdx + 1); // 다음 tgtIdx 재귀 호출
                select[i] = false; // 선택 해제
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            
            arr = new int[5][10];
            
            for (int r = 0; r < 5; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < 10; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            
            cnt = 0; // 구슬이 통과 가능하도록 놀이판을 결합하는 방법의 수(cnt) 초기화
            tgt = new int[5]; // 놀이판 결합 순서(tgt) 초기화
            boolean[] select = new boolean[5]; // 각 행 선택 여부(select) 선언 및 초기화
            Permutation(tgt, select, 0); // 순열 함수 호출
            
            bw.write("#" + t + " " + cnt + "\n");
        }
        bw.close();
    }
}
