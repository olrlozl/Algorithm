import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] cnt = new int[26];
        for (char c : br.readLine().toCharArray()) cnt[c - 'a']++;
        for (char c : br.readLine().toCharArray()) cnt[c - 'a']--;

        int result = 0;
        for (int i = 0; i < 26; i++) result += Math.abs(cnt[i]);

        bw.write(result + "");
        bw.close();
    }
}