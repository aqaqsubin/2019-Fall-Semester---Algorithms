import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class shortestPath {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ArrayList<Node> nodeList = new ArrayList<Node>();
		HashMap<String, Integer> nodeIndex = new HashMap<String, Integer>();
		nodeList.add(new Node("A"));
		nodeIndex.put("A", 0);
		nodeList.add(new Node("B"));
		nodeIndex.put("B", 1);
		nodeList.add(new Node("C"));
		nodeIndex.put("C", 2);
		nodeList.add(new Node("D"));
		nodeIndex.put("D", 3);
		nodeList.add(new Node("E"));
		nodeIndex.put("E", 4);
		int nodeSize = 5;
		
		int[][] path = new int[nodeSize][nodeSize];
		for(int i=0;i<path.length;i++) {
			for(int j=0;j<path[0].length;j++) {
				if(i!=j)path[i][j]=Integer.MAX_VALUE;
			}
		}	
		path[nodeIndex.get("A")][nodeIndex.get("B")]=10;
		path[nodeIndex.get("A")][nodeIndex.get("C")]=3;
		path[nodeIndex.get("B")][nodeIndex.get("C")]=1;
		path[nodeIndex.get("B")][nodeIndex.get("D")]=2;
		path[nodeIndex.get("C")][nodeIndex.get("B")]=4;
		path[nodeIndex.get("C")][nodeIndex.get("D")]=8;
		path[nodeIndex.get("C")][nodeIndex.get("E")]=2;
		path[nodeIndex.get("D")][nodeIndex.get("E")]=7;
		path[nodeIndex.get("E")][nodeIndex.get("D")]=9;
		Dijkstra_algorithm(path, nodeList, nodeIndex, nodeList.get(0));
	}
	public static int[] Dijkstra_algorithm(int[][] path, ArrayList<Node> nodeList,HashMap<String, Integer> nodeIndex, Node s){
		int[] d = new int[path.length];
		
		for(int i=0;i<nodeList.size();i++) {
			d[i]=Integer.MAX_VALUE;
			nodeList.get(i).key = Integer.MAX_VALUE;
		}
		d[nodeIndex.get(s.name)] = 0;
		nodeList.get(nodeIndex.get(s.name)).key=0;
		
		ArrayList<Node> S = new ArrayList<Node>();
		priorityQueue Queue = new priorityQueue((ArrayList<Node>)nodeList.clone());

		System.out.println("Dijkstra's algorithm으로 계산한 결과는 다음과 같습니다.");
		while(!Queue.isEmpty()) {
			System.out.println("____________________________________________");
			Node u = Queue.extract_min();
			System.out.println("S["+S.size()+"]  :  d["+u.name+"] = "+u.key);
			System.out.println("-----------------------------------------------");
			S.add(u);
			for(int i=0;i<Queue.heap_size;i++) {
				Node v = Queue.Q.get(i);
				System.out.print("Q["+i+"]  :  d["+v.name+"] = "+v.key+"  ");
				if(path[nodeIndex.get(u.name)][nodeIndex.get(v.name)]!=Integer.MAX_VALUE) {
					if(d[nodeIndex.get(v.name)] > d[nodeIndex.get(u.name)] + path[nodeIndex.get(u.name)][nodeIndex.get(v.name)]) {
						d[nodeIndex.get(v.name)]= d[nodeIndex.get(u.name)] + path[nodeIndex.get(u.name)][nodeIndex.get(v.name)];
						Queue.decrease_key(nodeList.get(nodeIndex.get(v.name)), d[nodeIndex.get(v.name)]);
						System.out.println("->  d["+v.name+"] = "+d[nodeIndex.get(v.name)]);
					}
				}else System.out.println();
			}
			System.out.println();
		}
		
		
		
		return d;
	}

}
