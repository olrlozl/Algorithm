import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int type = Integer.parseInt(st.nextToken());
        String str = st.nextToken();

        // camelCase, snake_case, PascalCase
        switch (type) {
            case 1:
                bw.write(str + "\n" + toSnakeCase(str) + "\n" + toPascalCase(str));
                break;
            case 2:
                bw.write(toCamelCase(str) + "\n" + str + "\n" + toPascalCase(str));
                break;
            case 3:
                bw.write(toCamelCase(str) + "\n" + toSnakeCase(str) + "\n" + str);
                break;
        }
        bw.close();
    }
    // camelCase, snake_case, PascalCase

    public static String toCamelCase(String str) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (char c : str.toCharArray()) {
            if (sb.length() == 0) {
                sb.append(Character.toLowerCase(c));
            } else if (c == '_') {
                flag = true;
            } else {
                if (flag) {
                    sb.append(Character.toUpperCase(c));
                    flag = false;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public static String toSnakeCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                if (sb.length() > 0) sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String toPascalCase(String str) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (char c : str.toCharArray()) {
            if (sb.length() == 0) {
                sb.append(Character.toUpperCase(c));
            } else if (c == '_') {
                flag = true;
            } else {
                if (flag) {
                    sb.append(Character.toUpperCase(c));
                    flag = false;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}