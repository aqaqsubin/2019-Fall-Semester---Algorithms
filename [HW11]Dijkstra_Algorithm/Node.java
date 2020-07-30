
public class Node {
	int key;
	String name;
	
	public Node(String name) {
		this.name = name;
		key = 0;
	}
	public Node(int key, String name) {
		this.key = key;
		this.name = name;
	}
	public String toString() {
		return key+", "+name;
	}
	public boolean same(Node newNode) {
		if(this.name.equals(newNode.name)) {
			return true;
		}
		return false;
	}
}
