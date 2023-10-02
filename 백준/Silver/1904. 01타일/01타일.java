import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] fibo = new int[n];
        fibo[0] = 1;
        if (n > 1) fibo[1] = 2;
        for (int i = 2; i < n; i++) fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 15746;

        bw.write(fibo[n - 1] + "");
        bw.close();
    }
}