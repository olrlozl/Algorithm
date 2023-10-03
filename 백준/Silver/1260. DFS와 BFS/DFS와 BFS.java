import java.io.*;
import java.util.*;

public class Main {
	
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void dfs(List<Integer>[] graph, int V) throws Exception {
		Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.length];
        
        stack.add(V); // 시작 정점을 스택에 넣고 
        visited[V] = true; // 시작 정점을 방문 처리
        bw.write(V + " ");
        
        while (!stack.isEmpty()) { // 스택이 빌 때까지
        	int node = stack.peek(); // 스택의 최상단 노드를 확인
        	boolean hasNode = false; // 이웃한 노드들을 모두 방문했다면 false
        	for (int n : graph[node]) { // 이웃한 노드 중에서
        		if (!visited[n]) { // 방문하지 않은 노드가 있다면
        			hasNode = true;
        			stack.add(n); // 스택에 넣고
        			visited[n] = true; // 방문 처리
        			bw.write(n + " ");
        			break;
        		}
        	}
        	if (!hasNode) { // 이웃한 노드들을 모두 방문했다면
        		stack.pop(); // 스택의 최상단 노드를 빼내기
        	}
        }
	}
	
	public static void bfs(List<Integer>[] graph, int V) throws Exception {
		Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        
        queue.add(V); // 시작 정점을 큐에 넣고
        visited[V] = true; // 시작 정점을 방문 처리
        bw.write(V + " ");
        
        while (!queue.isEmpty()) { // 큐가 빌 때까지
        	int node = queue.poll(); // 큐에서 원소 하나 꺼내고
        	for (int n : graph[node]) { // 이웃한 노드 중에서
        		if (!visited[n]) { // 방문하지 않은 노드가 있다면
        			queue.add(n); // 큐에 넣고
        			visited[n] = true; // 방문 처리
        			bw.write(n + " ");
        		}
        	}
        }
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 정점 개수
        int M = Integer.parseInt(st.nextToken()); // 간선 개수
        int V = Integer.parseInt(st.nextToken()); // 시작 정점 번호
        
        List<Integer>[] graph = new ArrayList[N + 1]; // 정점 정보 담을 리스트 배열
        
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();
        
        for (int m = 0; m < M; m++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	graph[u].add(v); 
        	graph[v].add(u); 
        }
        
        for (int i = 0; i < N + 1; i++) graph[i].sort(Comparator.naturalOrder()); // 오름차순 정렬
        
        dfs(graph, V);
        bw.newLine();
        bfs(graph, V);
        
        bw.close();
    }
}