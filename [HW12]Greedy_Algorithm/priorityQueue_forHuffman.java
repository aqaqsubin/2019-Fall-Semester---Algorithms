import java.util.ArrayList;

public class priorityQueue_forHuffman {
	static ArrayList<TreeNode> Q;
	static int heap_size=0;
	
	public priorityQueue_forHuffman(ArrayList<TreeNode> Q) {
		this.Q = Q;
		build_min_heap(this.Q);
	}
	public void build_min_heap(ArrayList<TreeNode> Q) {
		heap_size = Q.size();
		for(int i= (int)Math.floor((Q.size()-1)/2);i>=0;i--) {
			min_heapify(Q,i);
		}
	}
	private static void min_heapify(ArrayList<TreeNode> Q, int i) {
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
			TreeNode temp = Q.get(i);
			Q.set(i, Q.get(smallest));
			Q.set(smallest, temp);
			min_heapify(Q, smallest);
		}
	}
	public static void insert(TreeNode node) {
		Q.add(node);
		int index = heap_size;
		while(Q.get((int)(index-1)/2).key > node.key && index!=0) {
			Q.set(index, Q.get((int)(index-1)/2));
			index = (int)(index-1)/2;
		}
		Q.set(index, node);
		heap_size++;
	}
	public static TreeNode get(int key) {
		for(int i=0;i<Q.size();i++) {
			if(Q.get(i).key==key) return Q.get(i);
		}
		return null;
	}
	public static int indexOf(TreeNode node) {
		for(int i=0;i<Q.size();i++) {
			if(Q.get(i).key==node.key) return i;
		}
		return -1;
	}
	
	public static TreeNode extract_min() {
		TreeNode returnNode = Q.get(0);
		delete(returnNode);
		
		return returnNode;
	}
	public static void delete(TreeNode node) {
		int index = indexOf(node);
		Q.set(index, Q.get(--heap_size));		
		Q.remove(heap_size);
		min_heapify(Q,index);
	}
	public static boolean isEmpty() {
		return Q.isEmpty();
	}

}
