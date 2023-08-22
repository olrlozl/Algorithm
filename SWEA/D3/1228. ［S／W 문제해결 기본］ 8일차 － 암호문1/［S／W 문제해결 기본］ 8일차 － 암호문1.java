import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int t = 1; t <= 10; t++) {

            int N = sc.nextInt(); // 원문 암호문의 길이
            LinkedList<Integer> linkedList = new LinkedList<>(); // 원문 암호문
            for (int i = 0; i < N; i++) linkedList.add(i, sc.nextInt());

            int M = sc.nextInt(); // 명령어 개수
            for(int i = 0; i < M; i++) {
                char order = sc.next().charAt(0);

                if (order == 'I') { // 삽입
                    int x = sc.nextInt(); // 위치
                    int y = sc.nextInt(); // 숫자 개수
                    for (int j = 0; j < y; j++) linkedList.add(x+j, sc.nextInt());

                } else if (order == 'D') { // 삭제
                    int x = sc.nextInt(); // 위치
                    int y = sc.nextInt(); // 숫자 개수
                    for (int j = 0; j < y; j++) linkedList.remove(x);

                } else if (order == 'A') { // 추가
                    int y = sc.nextInt(); // 숫자 개수
                    for (int j = 0; j < y; j++) linkedList.add(sc.nextInt());
                }

            }

            System.out.print("#" + t);
            for (int i = 0; i < 10; i++) {
                System.out.print(" " + linkedList.get(i));
            }
            System.out.println();

        }
    }
}