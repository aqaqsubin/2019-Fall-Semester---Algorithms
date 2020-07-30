import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Huffman {
	String encodingLine;
	String decodingLine;
	int encodingLineLen;
	int decodingLineLen;
	HashMap<String, Integer> nodeIndex;
	HashMap<String, String> table;
	ArrayList<TreeNode> nodeList;
	
	public Huffman(String line) {
		encodingLine = line;
		nodeList = new ArrayList<TreeNode>();
		nodeIndex = new HashMap<String, Integer>();
		for(int i=0;i<line.length();i++) {
			if(nodeIndex.containsKey(String.valueOf(line.charAt(i)))){
				nodeList.get(nodeIndex.get(String.valueOf(line.charAt(i)))).key +=1;
			}else {
				nodeIndex.put(String.valueOf(line.charAt(i)), nodeList.size());
				nodeList.add(new TreeNode(1, String.valueOf(line.charAt(i))));
			}
		}
		
	}
	public void encoding() throws IOException {
		TreeNode startNode = makeTree();
		makeTable(startNode);
		encodingFile();
	}
	public void decoding(File toDecodingFile, File decodingTable) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(toDecodingFile));
		String toDecodingLine = bf.readLine();
		
		BufferedReader table = new BufferedReader(new FileReader(decodingTable));
		String tableLine = table.readLine();
		HashMap<String, String> decodingTableMap = new HashMap<String,String>();
		while(tableLine!=null) {
			
			String stringName = tableLine.split(",")[0];
			String path = tableLine.split(",")[1];
			decodingTableMap.put(path, stringName);
			tableLine = table.readLine();
		}
		decodingFile(toDecodingLine,decodingTableMap);
	}
	public TreeNode makeTree() {
		priorityQueue_forHuffman Queue = new priorityQueue_forHuffman((ArrayList<TreeNode>) nodeList.clone());
		for(int i=0;i<nodeList.size()-1;i++) {
			TreeNode x = Queue.extract_min();
			TreeNode y = Queue.extract_min();
			TreeNode z = new TreeNode(x, y, (x.key+y.key));
			Queue.insert(z);
		}
		return Queue.extract_min();
		
	}
	public void makeTable(TreeNode startNode){
		this.table = new HashMap<String, String>();
		for(int i=0;i<nodeList.size();i++) {
			String getPath = get(nodeList.get(i), startNode);
			table.put(nodeList.get(i).name,getPath);
			//System.out.println("nodeName : "+nodeList.get(i).name+" key : "+nodeList.get(i).key);
			//System.out.println(getPath);
		}
	}
	public String get(TreeNode node,TreeNode presentNode) {
		if(presentNode.name.equals(node.name)) return "";
		
		String getPath;
		if(presentNode.left!=null) {
			getPath = get(node, presentNode.left);
			if(getPath!=null) return "0"+getPath;
		}
		if(presentNode.right!=null) {
			getPath = get(node, presentNode.right);
			if(getPath!=null) return "1"+getPath;
		}
		
		return null;
	}
	public void encodingFile() throws IOException {
		FileWriter tableResult = new FileWriter("hw12_05_201701988_table.txt");
		FileWriter encoded = new FileWriter("hw12_05_201701988_encoded.txt");
		
		BufferedWriter bw_table = new BufferedWriter(tableResult);
		BufferedWriter bw = new BufferedWriter(encoded);
		
		for(Iterator iter = table.keySet().iterator();iter.hasNext();) {
			String charName = (String) iter.next();
			bw_table.write(charName+","+table.get(charName));
			bw_table.newLine();
		}
		String printResult = "";
		for(int i=0;i<encodingLine.length();i++) {
			printResult += table.get(String.valueOf(encodingLine.charAt(i)));
		}
		bw.write(printResult);
		bw.close();
		bw_table.close();
	}
	public void decodingFile(String toDecodingLine , HashMap<String, String> decodingTable) throws IOException{
		FileWriter decoded = new FileWriter("hw12_05_201701988_decoded.txt");
		BufferedWriter bw = new BufferedWriter(decoded);
		
		String printResult = "";
		String path = "";
		for(int i=0;i<toDecodingLine.length();i++) {
			path += toDecodingLine.charAt(i);
			if(decodingTable.containsKey(path)) {
				printResult += decodingTable.get(path);
				path="";
			}
		}
		bw.write(printResult);
		bw.close();


	}

}
