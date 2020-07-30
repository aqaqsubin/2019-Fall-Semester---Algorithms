
public class TreeNode{
	TreeNode left;
	TreeNode right;
	int key;
	String name;
	public TreeNode(int key, String name) {
		this.left= null;
		this.right = null;
		this.key = key;
		this.name = name;
	}
	public TreeNode(TreeNode left, TreeNode right, int key) {
		this.left= left;
		this.right = right;
		this.key = key;
		this.name="";
	}
	
}
