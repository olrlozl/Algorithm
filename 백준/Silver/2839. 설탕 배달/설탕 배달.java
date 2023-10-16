import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int total = n / 5;
        n %= 5;

        if (n == 3) total += 1;
        else if (total > 0 && (n == 1 || n == 4)) total += -1 + (n + 5) / 3;
        else if (total > 1 && n == 2) total += -2 + (n + 10) / 3;
        else if (n != 0) total = -1;

        bw.write(total + "");
        bw.close();

    }
}