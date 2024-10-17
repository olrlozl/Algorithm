import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();
        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            sb.append(word.charAt(i));

            if (bomb.length() <= sb.length()) {
                boolean flag = true;

                for (int j = 0; j < bomb.length(); j++) {
                    if (bomb.charAt(j) != sb.charAt(sb.length() - bomb.length() + j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    sb.setLength(sb.length() - bomb.length());
                }
            }
        }

        bw.write((sb.length() == 0 ? "FRULA" : sb.toString()) + "");
        bw.close();
    }
}