import java.util.ArrayList;

public class priorityQueue {
	static ArrayList<Node> Q;
	static int heap_size=0;
	
	public priorityQueue(ArrayList<Node> Q) {
		this.Q = Q;
		build_min_heap(this.Q);
	}
	private void build_min_heap(ArrayList<Node> Q) {
		heap_size = Q.size();
		for(int i= (int)Math.floor((Q.size()-1)/2);i>=0;i--) {
			min_heapify(Q,i);
		}
	}
	private static void min_heapify(ArrayList<Node> Q, int i) {
		int left = 2*i+1;
		int right = 2*i+2;
		int smallest = 0;
		if (left < heap_size && (Q.get(left).key < Q.get(i).key)){
			smallest = left;
		}else smallest = i;
		if(right < heap_size && Q.get(right).key < Q.get(smallest).key) {
			smallest = right;
		}
		if(smallest != i) {
			Node temp = Q.get(i);
			Q.set(i, Q.get(smallest));
			Q.set(smallest, temp);
			min_heapify(Q, smallest);
		}
	}
	public static void insert(Node node) {
		Q.add(node);
		int index = heap_size;
		while(Q.get((int)(index-1)/2).key > node.key && index!=0) {
			Q.set(index, Q.get((int)(index-1)/2));
			index = (int)(index-1)/2;
		}
		Q.set(index, node);
		heap_size++;
	}
	public static Node get(String name) {
		Node newNode = new Node(name);
		for(int i=0;i<Q.size();i++) {
			if(Q.get(i).same(newNode)) return Q.get(i);
		}
		return null;
	}
	public static int indexOf(Node node) {
		for(int i=0;i<Q.size();i++) {
			if(Q.get(i).same(node)) return i;
		}
		return -1;
	}
	public static Node min() {
		return Q.get(0);
	}
	public static Node extract_min() {
		Node returnNode = Q.get(0);
		delete(returnNode);
		
		return returnNode;
	}
	public static void decrease_key(Node node, int key) {
		
		int index = indexOf(node);
		Node newNode = new Node(key, node.name);
		Q.set(index, newNode);
		while(Q.get((int)(index-1)/2).key > key && index!=0) {
			Q.set(index, Q.get((int)(index-1)/2));
			index = (int)(index-1)/2;
		}
		Q.set(index, newNode);
	}
	public static void delete(Node node) {
		int index = indexOf(node);
		Q.set(index, Q.get(--heap_size));		
		Q.remove(heap_size);
		min_heapify(Q,index);
	}
	public static boolean isEmpty() {
		return Q.isEmpty();
	}

}
