import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) queue.add(i);
		
		System.out.print("<");
		
		while (!queue.isEmpty()) {
			for(int i = 1; i < K; i++) queue.add(queue.poll());
			System.out.print(queue.poll());
			if (!queue.isEmpty()) System.out.print(", ");
		}
		
		System.out.print(">");
	}

}
