import java.util.*;

class Solution {

    public static int max = 0;
	
	public static int gcd(int[] arr) {
		int res = arr[0];
		for (int i = 1; i < arr.length; i++) res = gcd(res, arr[i]);
		return res;
	}
	
	public static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	
	public static ArrayList<Integer> cd(int a) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i <= (int)(Math.pow(a, 1/2)) ; i++) {
			if (a % i == 0) {
				if (i * i == a) {
					list.add(i);
				}
				else {
					list.add(i);
					list.add(a / i);
				}
				
			}
		}
		list.sort(Comparator.reverseOrder());
		return list;
	}
    
    public int solution(int[] arrayA, int[] arrayB) {
        // A의 최대공약수의 약수가  B의 모든 수를 나눌 수 없는지 큰 수 부터 확인
		loop: for (int a : cd(gcd(arrayA))) {
			boolean flag = true;
			for (int b : arrayB) {
				if (b % a == 0) {
					flag = false;
					continue loop;
				}
			}
			if (flag) {
				max = Math.max(max, a);
				break;
			}
		}
		
		// B의 최대공약수의 약수가 A의 모든 수를 나눌 수 없는지 큰 수 부터 확인
		loop: for (int b : cd(gcd(arrayB))) {
			boolean flag = true;
			for (int a : arrayA) {
				if (a % b == 0) {
					flag = false;
					continue loop;
				}
			}
			if (flag) {
				max = Math.max(max, b);
				break;
			}
		}
		
		return max == 1 ? 0 : max;
    }
}