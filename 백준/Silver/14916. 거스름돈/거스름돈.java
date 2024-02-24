import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int total = 0;

        total += n / 5;
        n %= 5;

        if (n % 2 == 0) total += n / 2;
        else if (total > 0 && n % 2 == 1) total += -1 + (n + 5) / 2;
        else total = -1;

        bw.write(total + "");
        bw.close();
    }
}