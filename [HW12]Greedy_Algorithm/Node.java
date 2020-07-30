
public class Node{
	Node connect;
	String name;
	int key;
	
	public Node(String name) {
		this.name = name;
		this.key =0;
	}
	public Node(int key, String name) {
		this.name = name;
		this.key =key;
	}
	public String toString() {
		return connect.name+", "+name;
	}
	public boolean same(Node newNode) {
		if(this.name.equals(newNode.name)) {
			return true;
		}
		return false;
	}
}
