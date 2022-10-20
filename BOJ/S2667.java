package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class S2667 {

	static int N;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static int[][] map;
	static boolean[][] visited;
	static int count;  // ���� ��
	static ArrayList<Integer> result;  // ���� ���� ��� ����Ʈ
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Scanner ��� BufferedReader ���
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		// �迭 �� �Է�
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<N; j++) {
				// �Է°��� ������ ���� ������ charAt���� �� ���ھ� �����ͼ� getNumericValue�� int������ ��ȯ
				map[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}
		
		result = new ArrayList<>();
		// Ž��
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// ���� �ְ� �湮���� ���� ���
				if(map[i][j] == 1 && !visited[i][j]) {
					count = 1;
					dfs(i, j);
					result.add(count);
				}
			}
		}
		
		// ����Ʈ ����
		Collections.sort(result);
		System.out.println(result.size());
		for (int i=0; i < result.size(); i++)
			System.out.println(result.get(i));
	}
	
	static void dfs(int y, int x) {
		visited[y][x] = true;
		
		for (int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny >= 0 && nx >= 0 && ny < N && nx < N) {
				if (map[ny][nx] == 1 && !visited[ny][nx]) {
					dfs(ny, nx);
					count++;					
				}
			}
		}
	}

}
