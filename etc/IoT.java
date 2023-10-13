import java.awt.*;
import java.io.*;
import java.util.*;

public class Solution {
    public static int n;
    public static int Rap;
    public static int[][] arr;
    public static ArrayList<Point> port;
    public static ArrayList<Point> IoT;
    public static int min;

    public static void combination(boolean[] linkPort, int idx, int cnt, int r) {
        if (cnt == r) {
            // 모든 IoT가 다 연결 가능한지 확인
            boolean allIoT = true;
            for (int i = 0; i < IoT.size(); i++) {
                // 이번 IoT가 연결 가능한지 확인
                boolean thisIoT = false;
                for (int j = 0; j < port.size(); j++) {
                    if (linkPort[j]) {
                        // 이번 IoT가 연결 가능하다면
                        if (arr[IoT.get(i).x][IoT.get(i).y] + Rap >= Math.abs(IoT.get(i).x - port.get(j).x) + Math.abs(IoT.get(i).y - port.get(j).y)) {
                            thisIoT = true;
                            break; // 다음 port 확인 불필요. 다음 IoT 확인하러 가기.
                        }
                    }
                }
                // 이번 IoT가 연결 불가능하다면
                if (!thisIoT) {
                    allIoT = false;
                    break; // 다음 IoT 확인 불필요.
                };
            }
            // 모든 IoT가 연결 가능한 경우 최소 포트 개수 갱신
            if (allIoT) min = r;

            return;
        }

        if (idx == linkPort.length) return;

        linkPort[idx] = true;
        combination(linkPort, idx + 1, cnt + 1, r);
        linkPort[idx] = false;
        combination(linkPort, idx + 1, cnt, r);


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            Rap = Integer.parseInt(st.nextToken());

            arr = new int[n][n];

            port = new ArrayList<>();
            IoT = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 9) port.add(new Point(i, j));
                    if (arr[i][j] == 1 || arr[i][j] == 2 || arr[i][j] == 3) IoT.add(new Point(i, j));
                }
            }

            min = -1;

            // port r개 선택
            for (int r = 1; r <= Math.min(5, port.size()); r++) {
                combination(new boolean[port.size()], 0, 0, r);
                if (min != -1) break;
            }

            bw.write("#" + t + " " + min + "\n");
        }

        bw.close();
    }
}

//5
//5 2
//0 0 0 0 0
//0 0 0 9 0
//2 0 0 9 0
//0 0 0 0 0
//0 0 0 0 0
//5 2
//0 0 0 0 0
//0 0 0 0 0
//1 0 0 0 9
//0 0 0 0 9
//0 0 0 0 0
//6 2
//0 0 0 0 0 9
//9 0 0 0 0 0
//0 0 0 0 1 0
//0 0 0 0 0 0
//1 0 0 0 0 0
//0 0 0 9 0 0
//6 2
//9 0 0 0 0 0
//9 0 0 0 0 0
//0 0 0 0 1 0
//0 0 0 0 0 0
//1 0 0 0 0 0
//0 0 0 9 0 0
//7 2
//0 0 1 0 0 0 1
//0 0 0 0 0 0 0
//0 9 0 9 9 0 0
//0 1 0 0 0 0 9
//0 3 0 0 0 0 0
//0 0 0 0 0 0 0
//0 0 0 0 0 0 1
