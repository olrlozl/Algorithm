import java.io.*;

public class Main {

    // 상품 금액이 주어졌을 때, 상품의 금액을 지불하기 위해 최소로 필요한 동전의 수 출력
    // 동전 종류 : 3원, 5원, 8원
    // 금액을 지불할 수 없는 경우는 -1 출력
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        
        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine()); // 지불해야할 금액
            
            int total = n / 8; // 최소로 필요한 동전의 수
            
            n %= 8; // n은 0, 1, 2, 3, 4, 5, 6, 7 가능
            
            if (n % 3 == 0) total += n / 3; // n이 3 또는 6일 때
            else if (total > 0 && n % 5 == 0) total += n / 5;
            else if (total > 0 && (n == 1 || n == 4)) total += -1 + (n + 8) / 3;
            else if (total > 0 && (n == 2 || n == 7)) total += -1 +  (n + 8) / 5;
            else total = -1;
            
            bw.write("#" + t + " " + total + "\n");
        }
        bw.close();
    }
}
