import java.util.ArrayList;

public class makeHeap {
	static ArrayList<Node> list;
	static int heap_size=0;
	
	public makeHeap(ArrayList<Node> list) {
		this.list = list;
		build_max_heap(this.list);
	}
	private void build_max_heap(ArrayList<Node> list) {
		heap_size = list.size();
		for(int i= (int)Math.floor((list.size()-1)/2);i>=0;i--) {
			max_heapify(list,i);
		}
	}
	private static void max_heapify(ArrayList<Node> list, int i) {
		int left = 2*i+1;
		int right = 2*i+2;
		int largest = 0;
		if (left < heap_size && (list.get(left).key > list.get(i).key)){
			largest = left;
		}else largest = i;
		if(right < heap_size && list.get(right).key > list.get(largest).key) {
			largest = right;
		}
		if(largest != i) {
			Node temp = list.get(i);
			list.set(i, list.get(largest));
			list.set(largest, temp);
			max_heapify(list, largest);
		}
	}
	public static void insert(Node node) {
		list.add(node);
		int index = heap_size;
		while(list.get((int)(index-1)/2).key < node.key && index!=0) {
			list.set(index, list.get((int)(index-1)/2));
			index = (int)(index-1)/2;
		}
		list.set(index, node);
		heap_size++;
	}
	public static Node get(String name) {
		Node newNode = new Node(name);
		for(int i=0;i<list.size();i++) {
			if(list.get(i).same(newNode)) return list.get(i);
		}
		return null;
	}
	public static int indexOf(Node node) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).same(node)) return i;
		}
		return -1;
	}
	public static Node max() {
		return list.get(0);
	}
	public static void extract_max() {
		delete(list.get(0));
	}
	public static void increase_key(Node node, int key) {
		
		int index = indexOf(node);
		Node newNode = new Node(key, node.name);
		list.set(index, newNode);
		while(list.get((int)(index-1)/2).key < key && index!=0) {
			list.set(index, list.get((int)(index-1)/2));
			index = (int)(index-1)/2;
		}
		list.set(index, newNode);
	}
	public static void delete(Node node) {
		int index = indexOf(node);
		list.set(index, list.get(--heap_size));		
		list.remove(heap_size);
		max_heapify(list,index);
	}
}