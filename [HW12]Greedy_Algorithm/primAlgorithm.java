import java.util.ArrayList;
import java.util.HashMap;

public class primAlgorithm {
	ArrayList<Node> V;
	Node s;
	int[][] edge;
	HashMap<String, Integer> nodeIndex;

	public primAlgorithm(ArrayList<Node> V, HashMap<String, Integer> nodeIndex, int[][] edge, Node s) {
		this.V = V;
		this.s = s;
		this.nodeIndex = nodeIndex;
		this.edge = edge;
	}

	public void executePrimAlgorithm() {
		for (int i = 0; i < V.size(); i++) {
			V.get(i).key = Integer.MAX_VALUE;
			if (V.get(i).name.equals(s.name))
				V.get(i).key = 0;
		}
		priorityQueue Queue = new priorityQueue((ArrayList<Node>) V.clone());
		int value = 0;

		while (!Queue.isEmpty()) {
			Node u = Queue.extract_min();
			int indexU = nodeIndex.get(u.name);

			if (!u.equals(s))
				System.out.println("w<" + u.connect.name + ", " + u.name + "> =  " + u.key);
			else
				System.out.println("w< , " + u.name + "> =  " + u.key);

			value += u.key;

			for (int i = 0; i < Queue.heap_size; i++) {
				Node v = Queue.Q.get(i);
				int indexV = nodeIndex.get(v.name);

				if (edge[indexU][indexV] != Integer.MAX_VALUE && edge[indexU][indexV] <= v.key) {
					v.key = edge[indexU][indexV];
					v.connect = u;
				}
			}
			Queue.build_min_heap(Queue.Q);

		}
		System.out.println();
		System.out.println("w<MST> = " + value);
	}
}
