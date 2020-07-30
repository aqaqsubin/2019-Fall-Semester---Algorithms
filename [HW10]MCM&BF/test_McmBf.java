import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class test_McmBf {
	public static class Matrix{
		int m;
		int n;
		public Matrix(int m, int n) {
			this.m = m;
			this.n = n;
		}
	}
	public static class Edge {
		int u;
		int v;
		int distance;

		public Edge(int u, int v, int distance) {
			this.u = u;
			this.v = v;
			this.distance = distance;
		}
	}

	public static class Graph {
		ArrayList<Integer> nodeList;
		HashMap<Integer, Integer> nodeIndex;
		ArrayList<Edge> edgeList;

		public Graph(ArrayList<Integer> nodeList, HashMap<Integer, Integer> nodeIndex, ArrayList<Edge> edgeList) {
			this.nodeList = nodeList;
			this.nodeIndex = nodeIndex;
			this.edgeList = edgeList;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/*Matrix Chain Multiplication*/
		File matrix_file = new File("data11_matrix_chain.txt");
		BufferedReader bf = new BufferedReader(new FileReader(matrix_file));
		
		String line = bf.readLine();
		StringTokenizer st;
		int m,n;
		ArrayList<Matrix> matrixList = new ArrayList<Matrix>();
		while(line != null) {
			st = new StringTokenizer(line, ",");
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			matrixList.add(new Matrix(m,n));
			line = bf.readLine();
		}
//		matrix_chain(matrixList);
		
		/*Bellman-Ford Algorithm*/
		File graph_file = new File("data11_bellman_ford_1.txt");
		File graph_file2 = new File("data11_bellman_ford_2.txt");
		BufferedReader bf_bellman = new BufferedReader(new FileReader(graph_file));
		BufferedReader bf_bellman2 = new BufferedReader(new FileReader(graph_file2));

		line = bf_bellman2.readLine();
		st = new StringTokenizer(line, ",");

		ArrayList<Integer> nodeList = new ArrayList<Integer>();
		HashMap<Integer, Integer> nodeIndex = new HashMap<Integer, Integer>();
		ArrayList<Edge> edgeList = new ArrayList<Edge>();

		int TokenLength = st.countTokens();
		for (int i = 0; i < TokenLength; i++) {
			int key = Integer.parseInt(st.nextToken());
			nodeList.add(key);
			nodeIndex.put(key, i);
		}

		line = bf_bellman2.readLine();
		while (line != null) {
			st = new StringTokenizer(line, ",");

			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			edgeList.add(new Edge(start, dest, value));

			line = bf_bellman2.readLine();
		}
		Graph graph = new Graph(nodeList, nodeIndex, edgeList);
		findShortestPath(graph, graph.nodeIndex.get(0));

	}
	public static boolean findShortestPath(Graph graph, int s) {
		int size = graph.nodeList.size();
		int[] D = Init_Single_Source(graph, s);
		boolean returnValue = true;
		
		for (int i = 0; i < size; i++) {
			System.out.println();
			System.out.println("---------iteration " + i + "----------------");

			for (int j = 0; j < graph.edgeList.size(); j++) {
				Edge edge = graph.edgeList.get(j);
				
				int v =graph.nodeIndex.get(edge.v);
				int preValue = D[v];
				int newValue = RELAX(graph, D, edge, s);

				if (newValue < preValue) {
					System.out.println("Update distance of " + v + " from in to " + newValue);
					D[v] = newValue;
					if(i==size-1) returnValue = false;
				} else D[v] = preValue;

			}
			System.out.print("Iteration " + (i) + " distance : [");
			for (int k = 0; k < size; k++) {
				if (k == size - 1) System.out.print(D[k] + "]");
				else System.out.print(D[k] + ", ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.print("final Iteration : [");
		for (int k = 0; k < size; k++) {
			if (k == size - 1) System.out.print(D[k] + "]");
			else System.out.print(D[k] + ", ");
		}
		System.out.println();
		if(!returnValue) System.out.println("The graph has negative cycle");

		return returnValue;
		

	}

	public static int RELAX(Graph graph, int[] D, Edge edge, int s) {
		int v = graph.nodeIndex.get(edge.v);
		int u = graph.nodeIndex.get(edge.u);
		int prev = D[v];
		int returnValue = D[v];
		if (D[u] != Integer.MAX_VALUE) {
			returnValue = D[u] + edge.distance;
		}

		return returnValue;
	}

	public static int[] Init_Single_Source(Graph graph, int s) {
		int size = graph.nodeList.size();
		int[] D = new int[size];
		for (int j = 0; j < size; j++) {
			if (j == s) D[j] = 0;
			else D[j] = Integer.MAX_VALUE;
		}
		return D;
	}

	public static void matrix_chain(ArrayList<Matrix> matrixList) {
		int n = matrixList.size();
		int[][] M = new int[n][n];
		for(int i=0;i<n;i++) {
			M[i][i]=0;
		}
		for(int l=1;l<n;l++) {
			for(int i=0;i < n-l;i++) {
				int j= i+l;
				M[i][j]= Integer.MAX_VALUE;
				for(int k=i;k<j;k++) {
					int dim_1 = matrixList.get(i).m;
					int dim_2 = matrixList.get(k).n;
					int dim_3 = matrixList.get(j).n;
					int q = M[i][k]+M[k+1][j]+dim_1*dim_2*dim_3;
					if(q < M[i][j]) {
						M[i][j]= q;
					}
				}
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(M[i][j]==0) System.out.print("     ");
				else if((int)(M[i][j]/10)==0) System.out.print("     ");
				else if((int)(M[i][j]/100)==0) System.out.print("    ");
				else if((int)(M[i][j]/1000)==0) System.out.print("   ");
				else if((int)(M[i][j]/10000)==0) System.out.print("  ");
				else System.out.print(" ");
				System.out.print(M[i][j]);
			}
			System.out.println();
		}
	}
	

}
