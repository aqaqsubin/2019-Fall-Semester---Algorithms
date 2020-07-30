import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class testClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Node> nodeList = new ArrayList<Node>();
		HashMap<String, Integer> nodeIndex = new HashMap<String, Integer>();

		int[][] edge = null;
		readyToPrims(nodeList,nodeIndex,edge,"a");
		
		File dataFile = new File("data12.txt");
		File dataencoded = new File("data12_encoded.txt");
		File datatable = new File("data12_table.txt");
		BufferedReader bf = new BufferedReader(new FileReader(dataFile));
		String line = bf.readLine();
		Huffman huffman = new Huffman(line);
		

		huffman.encoding();
		huffman.decoding(dataencoded, datatable);
		

	}
	public static void readyToPrims(ArrayList<Node> nodeList,HashMap<String, Integer> nodeIndex,int[][] edge, String startNode) {
		nodeIndex.put("a", nodeList.size());
		nodeList.add(new Node("a"));
		nodeIndex.put("b", nodeList.size());
		nodeList.add(new Node("b"));
		nodeIndex.put("c", nodeList.size());
		nodeList.add(new Node("c"));
		nodeIndex.put("d", nodeList.size());
		nodeList.add(new Node("d"));
		nodeIndex.put("e", nodeList.size());
		nodeList.add(new Node("e"));
		nodeIndex.put("f", nodeList.size());
		nodeList.add(new Node("f"));
		nodeIndex.put("g", nodeList.size());
		nodeList.add(new Node("g"));
		nodeIndex.put("h", nodeList.size());
		nodeList.add(new Node("h"));
		nodeIndex.put("i", nodeList.size());
		nodeList.add(new Node("i"));
		
		edge = new int[nodeList.size()][nodeList.size()];
		for(int i=0;i<nodeList.size();i++) {
			for(int j=0;j<nodeList.size();j++) {
				edge[i][j]=Integer.MAX_VALUE;
			}
		}
		edge[nodeIndex.get("a")][nodeIndex.get("b")]=4;
		edge[nodeIndex.get("a")][nodeIndex.get("h")]=8;
		
		edge[nodeIndex.get("b")][nodeIndex.get("a")]=4;
		edge[nodeIndex.get("b")][nodeIndex.get("c")]=8;
		edge[nodeIndex.get("b")][nodeIndex.get("h")]=11;
		
		edge[nodeIndex.get("c")][nodeIndex.get("b")]=8;
		edge[nodeIndex.get("c")][nodeIndex.get("d")]=7;
		edge[nodeIndex.get("c")][nodeIndex.get("i")]=2;
		edge[nodeIndex.get("c")][nodeIndex.get("f")]=4;
		
		edge[nodeIndex.get("d")][nodeIndex.get("c")]=7;
		edge[nodeIndex.get("d")][nodeIndex.get("e")]=9;
		edge[nodeIndex.get("d")][nodeIndex.get("f")]=14;
		
		edge[nodeIndex.get("e")][nodeIndex.get("d")]=9;
		edge[nodeIndex.get("e")][nodeIndex.get("f")]=10;
		
		edge[nodeIndex.get("f")][nodeIndex.get("c")]=4;
		edge[nodeIndex.get("f")][nodeIndex.get("d")]=14;
		edge[nodeIndex.get("f")][nodeIndex.get("e")]=10;
		edge[nodeIndex.get("f")][nodeIndex.get("g")]=2;
		
		edge[nodeIndex.get("g")][nodeIndex.get("h")]=1;
		edge[nodeIndex.get("g")][nodeIndex.get("i")]=6;
		edge[nodeIndex.get("g")][nodeIndex.get("f")]=2;
		
		edge[nodeIndex.get("h")][nodeIndex.get("a")]=8;
		edge[nodeIndex.get("h")][nodeIndex.get("i")]=7;
		edge[nodeIndex.get("h")][nodeIndex.get("g")]=1;
		
		edge[nodeIndex.get("i")][nodeIndex.get("c")]=2;
		edge[nodeIndex.get("i")][nodeIndex.get("h")]=7;
		edge[nodeIndex.get("i")][nodeIndex.get("g")]=6;
		
		primAlgorithm execute = new primAlgorithm(nodeList, nodeIndex,edge, nodeList.get(nodeIndex.get(startNode)));
		execute.executePrimAlgorithm();
	}

}
