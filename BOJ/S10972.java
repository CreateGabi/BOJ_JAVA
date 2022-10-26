package BOJ;

import java.util.Scanner;

public class S10972 {

	static int N;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = sc.nextInt();
		
		if (nextPermutation()) {
			for (int i : arr) {
				System.out.print(i + " ");
			}
		}
		else
			System.out.print("-1");
	}

	public static boolean nextPermutation() {
		int i = N-1;
		while (i > 0 && arr[i-1] >= arr[i]) i-= 1;
		if (i <= 0) return false; // 마지막 순열
		
		int j = N-1;
		while (arr[j] <= arr[i-1]) j-=1;
		swap(i-1, j);
		j = N-1;
		while (i < j) {
			swap(i, j);
			i += 1;
			j -= 1;
		}
		
		return true;
	}
	
	public static void swap(int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
