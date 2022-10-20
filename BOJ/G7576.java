package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// x y 반대로 해야겠는데
class Tomato {
	int x, y;  // x, y 좌표
	
	Tomato(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class G7576 {
	// static -> 전역 변수 역할 메모리에 먼저 올림
	static int M, N;  // 상자의 크기 가로 M, 세로 N
	static int[] dx = {-1, 1, 0, 0};  // 상(-1,0), 하(1,0), 좌(0,-1), 우(0,1)
	static int[] dy = {0, 0, -1 ,1};
	
	static int[][] arr;
	static Queue<Tomato> q;

	// 메인 함수 static 필수 코드 실행 시작점
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);  // 키보드로부터 데이터 입력
		M = sc.nextInt();  // 키보드로부터 하나의 정수를 받아 변수에 저장
		N = sc.nextInt();
		
		// new 인스턴스(객체) 생성해주는 역할
		// 메모리(Heap 영역)에 데이터를 저장할 공간을 할당받고 공간의 참조값을 객체에게 반환하여 생성자 호출
		arr = new int[N][M];  // 2차원 배열 초기화, N행 M열
		q = new LinkedList<Tomato>();  // 큐 생성 (선입선출)

		// 토마토 판 입력
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				arr[i][j] = sc.nextInt();
				// 익은 토마토(1) 큐에 넣기
				if (arr[i][j] == 1)
					// 큐에 넣는 이유 bfs 실행할 때 익은 토마토 기준으로 익은 토마토 기준으로 넓이 우선 탐색
					q.add(new Tomato(i, j));  // 해당 큐의 맨 뒤에 전달된 요소를 삽입, 성공하면 true 실패하면 IllegalStateExceeption
			}
		}
		System.out.print(BFS());  // bfs 실행
	}
	
	// static에서 static 함수 호출
	public static int BFS() {
		while (!q.isEmpty()) { // 큐가 빌 때까지 반복문 (안익은 토마토가 없을 때까지)
			// 익은 토마토 가져오기
			Tomato t = q.poll(); // 맨 앞에 있는 요소 반환 후 제거, 큐가 비어있으면 null 반환
			// remove는 그냥 제거
			
			for (int i=0; i<4; i++) {  // 익은 토마토 기준 상하좌우
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];
				
				if (nx >= 0 && ny >= 0 && nx < N && ny < M) {  // 배열 범위
					// 안익은 토마토 익은 토마토로 변경
					if (arr[nx][ny] == 0) {  // 안익은 토마토를 찾을 경우
						q.add(new Tomato(nx, ny));
						// 그 전 값에서 1 증가해서 날짜 계산
						arr[nx][ny] = arr[t.x][t.y] + 1;
					}
				}
			}
		}
		
		// 최대 날짜 구하기
		int max = 0;
		for (int i=0; i<N; i++) {  // 배열 전체 검색
			for (int j=0; j<M; j++) {
				// 안익은 토마토가 있을 경우
				if (arr[i][j] == 0)
					return -1;
				if (max < arr[i][j])  // 최대값 구하기
					max = arr[i][j];
			}
		}
		
		return max-1;  // 처음 값이 1인데 거기서 +1씩 한거라 -1을 해야 날짜 계산이 맞음
	}

}