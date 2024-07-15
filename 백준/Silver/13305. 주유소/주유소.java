import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 도시 개수
        int[] dist = new int[N - 1]; // 도로 길이 (필요한 기름)
        int[] price = new int[N - 1]; // 리터당 가격

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        int d = dist[0];
        int p = price[0];
        
        for (int i = 0; i < N - 1; i++) {
            if (i == N - 2) { // 마지막 주유소일 때
                result += d * p; // 지금까지 필요한 금액 정산
                break; 
            }
            if (price[i] > price[i + 1]) { // 다음 주유소가 더 싸다!
                result += d * p; // 지금까지 필요한 금액 정산
                d = dist[i + 1];
                p = price[i + 1];
            } else { // 다음 주유소는 비싸
                d += dist[i + 1]; // 다음에 필요한 기름을 미리 넣자
            }
        }

        bw.write(result + "");
        bw.close();
    }
}