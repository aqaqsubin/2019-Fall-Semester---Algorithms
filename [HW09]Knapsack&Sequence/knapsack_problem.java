import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class knapsack_problem {
	public static class Item{
		int number;
		int value;
		int weight;
		public Item(int number, int value, int weight) {
			this.number = number;
			this.value = value;
			this.weight = weight;
		}
	}
	public static class OPT{
		int total_value;
		ArrayList<Item> itemList;
		public OPT(int total_value, ArrayList<Item> itemList) {
			this.total_value = total_value;
			this.itemList = itemList;
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		File knapsackDataFile = new File("data09_knapsack.txt");
		BufferedReader bf = new BufferedReader(new FileReader(knapsackDataFile));
		
		String line = bf.readLine();
		StringTokenizer st = new StringTokenizer(line, ",");
		ArrayList<Item> itemList = new ArrayList<Item>();
		int number, value, weight;
		itemList.add(new Item(0,0,0));
		while(line != null) {
			st = new StringTokenizer(line, ",");
			number = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			itemList.add(new Item(number,value,weight));
			line = bf.readLine();
			
		}
		System.out.print("배낭의 사이즈를 입력하세요.(0~50) : ");
		int w = scan.nextInt();
		OPT[][] returnTable = getOPT(itemList,itemList.size()-1,w);
		findMaxValue(returnTable);
	}
	public static OPT[][] getOPT(ArrayList<Item> itemList, int n, int w){
		OPT[][] OPT_Table = new OPT[n+1][w+1];
		
		for(int i=0;i<n+1;i++) {
			for(int j=0;j<w+1;j++) {
				if(i==0) OPT_Table[i][j] = new OPT(0, new ArrayList<Item>());
				else if(itemList.get(i).weight > j) {
					OPT_Table[i][j] = OPT_Table[i-1][j];
				}else {
					int value = Math.max(OPT_Table[i-1][j].total_value, itemList.get(i).value + OPT_Table[i-1][j-itemList.get(i).weight].total_value);
					ArrayList<Item> list;
					if(value == OPT_Table[i-1][j].total_value) {
						OPT_Table[i][j] = OPT_Table[i-1][j];
					}else {
						list =(ArrayList<Item>) OPT_Table[i-1][j-itemList.get(i).weight].itemList.clone();
						list.add(itemList.get(i));
						
						OPT_Table[i][j] = new OPT(value,list);
					}
				}
			}
		}
		return OPT_Table;
	}
	
	public static void findMaxValue(OPT[][] optTable) {
		for(int i=0;i<optTable.length;i++) {
			for(int j=0;j<optTable[0].length;j++) {
				int totalValue = optTable[i][j].total_value;
				
				if(totalValue >= 10) System.out.print("  "+totalValue);
				else System.out.print("   "+totalValue);
			}
			System.out.println();
		}
		OPT maxValue = optTable[optTable.length-1][optTable[0].length-1];
		System.out.println("max : "+ maxValue.total_value);
		System.out.print("item : ");
		for(int i=0;i<maxValue.itemList.size();i++) {
			System.out.print(maxValue.itemList.get(i).number+" ");
		}
		System.out.println();
	}
}
