package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class G7569 {
	// ��ǥ
	public static class Tomato2 {
		int z, y, x;
		
		Tomato2(int z, int y, int x) {
			this.z = z;
			this.y = y;
			this.x = x;
		}
	}
	
	static int M, N, H;
	static int[] dz = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dx = {0, 0, 0, 0, -1, 1};
	
	static int[][][] arr;
	static Queue<Tomato2> q;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		
		arr = new int[H][N][M];
		q = new LinkedList<Tomato2>();
				
		// �迭 �Է�
		for (int i=0; i<H; i++) {
			for (int j=0; j<N; j++) {
				for (int k=0; k<M; k++) {
					arr[i][j][k] = sc.nextInt();
					if (arr[i][j][k] == 1)
						q.add(new Tomato2(i, j, k));
				}
			}
		}
		
		System.out.print(BFS());
	}
	
	public static int BFS() {
		while (!q.isEmpty()) {
			Tomato2 t = q.poll();
			
			// ���� �丶�� �������� �ֺ��� ������ �丶�並 ���� �丶��� ����
			for (int i=0; i<6; i++) {
				int nz = t.z + dz[i];
				int ny = t.y + dy[i];
				int nx = t.x + dx[i];
				
				if (nz >= 0 && ny >= 0 && nx >= 0 && nz < H && ny < N && nx < M) {
					if (arr[nz][ny][nx] == 0) {
						q.add(new Tomato2(nz, ny, nx));
						arr[nz][ny][nx] = arr[t.z][t.y][t.x] + 1;
						
					}
				}
			}
		}
		
		// ��¥
		int max = 0;
		for (int i=0; i<H; i++) {
			for (int j=0; j<N; j++) {
				for (int k=0; k<M; k++) {
					if (arr[i][j][k] == 0)
						return -1;
					if (max < arr[i][j][k])
						max = arr[i][j][k];
				}
			}
		}
		return max - 1;
	}

}
