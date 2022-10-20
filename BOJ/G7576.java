package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// x y �ݴ�� �ؾ߰ڴµ�
class Tomato {
	int x, y;  // x, y ��ǥ
	
	Tomato(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class G7576 {
	// static -> ���� ���� ���� �޸𸮿� ���� �ø�
	static int M, N;  // ������ ũ�� ���� M, ���� N
	static int[] dx = {-1, 1, 0, 0};  // ��(-1,0), ��(1,0), ��(0,-1), ��(0,1)
	static int[] dy = {0, 0, -1 ,1};
	
	static int[][] arr;
	static Queue<Tomato> q;

	// ���� �Լ� static �ʼ� �ڵ� ���� ������
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);  // Ű����κ��� ������ �Է�
		M = sc.nextInt();  // Ű����κ��� �ϳ��� ������ �޾� ������ ����
		N = sc.nextInt();
		
		// new �ν��Ͻ�(��ü) �������ִ� ����
		// �޸�(Heap ����)�� �����͸� ������ ������ �Ҵ�ް� ������ �������� ��ü���� ��ȯ�Ͽ� ������ ȣ��
		arr = new int[N][M];  // 2���� �迭 �ʱ�ȭ, N�� M��
		q = new LinkedList<Tomato>();  // ť ���� (���Լ���)

		// �丶�� �� �Է�
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				arr[i][j] = sc.nextInt();
				// ���� �丶��(1) ť�� �ֱ�
				if (arr[i][j] == 1)
					// ť�� �ִ� ���� bfs ������ �� ���� �丶�� �������� ���� �丶�� �������� ���� �켱 Ž��
					q.add(new Tomato(i, j));  // �ش� ť�� �� �ڿ� ���޵� ��Ҹ� ����, �����ϸ� true �����ϸ� IllegalStateExceeption
			}
		}
		System.out.print(BFS());  // bfs ����
	}
	
	// static���� static �Լ� ȣ��
	public static int BFS() {
		while (!q.isEmpty()) { // ť�� �� ������ �ݺ��� (������ �丶�䰡 ���� ������)
			// ���� �丶�� ��������
			Tomato t = q.poll(); // �� �տ� �ִ� ��� ��ȯ �� ����, ť�� ��������� null ��ȯ
			// remove�� �׳� ����
			
			for (int i=0; i<4; i++) {  // ���� �丶�� ���� �����¿�
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];
				
				if (nx >= 0 && ny >= 0 && nx < N && ny < M) {  // �迭 ����
					// ������ �丶�� ���� �丶��� ����
					if (arr[nx][ny] == 0) {  // ������ �丶�並 ã�� ���
						q.add(new Tomato(nx, ny));
						// �� �� ������ 1 �����ؼ� ��¥ ���
						arr[nx][ny] = arr[t.x][t.y] + 1;
					}
				}
			}
		}
		
		// �ִ� ��¥ ���ϱ�
		int max = 0;
		for (int i=0; i<N; i++) {  // �迭 ��ü �˻�
			for (int j=0; j<M; j++) {
				// ������ �丶�䰡 ���� ���
				if (arr[i][j] == 0)
					return -1;
				if (max < arr[i][j])  // �ִ밪 ���ϱ�
					max = arr[i][j];
			}
		}
		
		return max-1;  // ó�� ���� 1�ε� �ű⼭ +1�� �ѰŶ� -1�� �ؾ� ��¥ ����� ����
	}

}