package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class G11657 {
	
	public static class Bus {
		int a, b, c;  // 시작점, 도착점, 걸리는 시간
		
		public Bus(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	
	static final int INF = Integer.MAX_VALUE;
	static int n, m;  // 도시(노드)의 개수, 버스 노선(간선)의 개수
	static long dist[];  // 최단거리 테이블  int로 선언하면 underflow 발생
	static Bus arr[];  // 모든 간선에 대한 정보
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new Bus[m+1];
		dist = new long[n+1];
		// 최단거리 무한대로 초기화
		for (int i = 1; i <= n; i++)
			dist[i] = INF;
		
		// 입력값 초기화
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			arr[i] = new Bus(a, b, c);
		}
		
		// 결과 출력을 위한 객체
		StringBuilder sb = new StringBuilder();
		// 음의 사이클이 있는 경우
		if (bf()) {
			sb.append("-1\n");
		// 음의 사이클이 없는 경우
		} else {
			for (int i=2; i<=n; i++)
				sb.append(dist[i] == INF ? "-1\n" : dist[i] + "\n");
		}
		
		bw.write(sb.toString().stripTrailing());
		bw.close();
		br.close();
	}

	// 벨만포드 알고리즘 
	private static boolean bf() {
		// 시작점 최단거리 0 갱신
		dist[1] = 0;
		
		// v-1번 수행
		for (int i=1; i<n; i++) {
			for (int j=0; j<m; j++) {
				Bus bus = arr[j];
				// 더 작은 최단거리가 있는 경우 갱신
				if ((dist[bus.a] != INF) && (dist[bus.b] > dist[bus.a] + bus.c)) {
					dist[bus.b] = dist[bus.a] + bus.c;
				}
			}
		}
		
		// 음수 cycle 확인
		// 갱신되는게 있다면 음수 cycle이 있다는 뜻
		for (int i=0; i<m; i++) {
			Bus bus = arr[i];
			if ((dist[bus.a] != INF) && (dist[bus.b] > dist[bus.a] + bus.c))
				return true;
		}
		
		return false;
	}
}
