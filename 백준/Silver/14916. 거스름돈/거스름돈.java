import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int five = n / 5;
        int two = 0;

        if (n % 5 != 0) {
            two = (n - 5 * five) / 2;
            while ((n - 5 * five) % 2 != 0) {
                five -= 1;
                if (five < 0) {
                    two = 0;
                    break;
                }
                two = (n - 5 * five) / 2;
            }
        }

        bw.write(five + two + "");
        bw.close();
    }
}