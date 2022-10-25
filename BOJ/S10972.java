package BOJ;

import java.util.Scanner;

public class S10972 {

	static int N;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		for (int i = 0; i < N; i++)
			arr[i] = sc.nextInt();
		
		if (nextPermutation(arr, N)) {
			for (int i : arr) {
				System.out.print(i + " ");
			}
		}
		else
			System.out.println("-1");
	}

	public static boolean nextPermutation(int[] a, int n) {
		int i = n-1;
		while (i > 0 && a[i-1] >= a[i]) i-= 1;
		if (i <= 0) return false; // 마지막 순열
		
		return true;
	}
}
