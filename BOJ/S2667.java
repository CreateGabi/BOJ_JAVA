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
	static int count;  // 집의 수
	static ArrayList<Integer> result;  // 집의 수를 담는 리스트
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Scanner 대신 BufferedReader 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		// 배열 값 입력
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<N; j++) {
				// 입력값에 공백이 없기 때문에 charAt으로 한 글자씩 가져와서 getNumericValue로 int형으로 변환
				map[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}
		
		result = new ArrayList<>();
		// 탐색
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				// 집이 있고 방문하지 않은 경우
				if(map[i][j] == 1 && !visited[i][j]) {
					count = 1;
					dfs(i, j);
					result.add(count);
				}
			}
		}
		
		// 리스트 정렬
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
