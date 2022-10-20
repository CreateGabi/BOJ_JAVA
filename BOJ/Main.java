package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static class tomato {  // 이너클래스로 해본것
		int x, y;
		
		tomato(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int M, N;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] arr;
	static Queue<tomato> q;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		
		arr = new int[N][M];
		q = new LinkedList<tomato>();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 1)
					q.add(new tomato(i, j));
			}
		}
		
		System.out.print(bfs());

	}
	
	public static int bfs() {
		while(!q.isEmpty()) {
			tomato t = q.poll();
			
			for (int i=0; i<4; i++) {
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];
				
				if (nx>=0 && ny>=0 && nx<N && ny<M) {
					if (arr[nx][ny] == 0) {
						q.add(new tomato(nx, ny));
						arr[nx][ny] = arr[t.x][t.y] + 1;
					}
				}
			}
		}
		
		int max=0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (arr[i][j] == 0)
					return -1;
				if (max < arr[i][j])
					max = arr[i][j];
			}
		}
		return max-1;
	}

}
