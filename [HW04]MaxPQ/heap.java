import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class heap {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("data04.txt");
		BufferedReader bf = new BufferedReader(new FileReader(file));
		String line = bf.readLine();		
		ArrayList<Node> nodeList = new ArrayList<Node>();
		
		while(line != null) {
			Node node = new Node(Integer.parseInt(line.split(", ")[0]),line.split(", ")[1]);
			nodeList.add(node);
			line = bf.readLine();
		}
		//max heap �����
		makeHeap heapList = new makeHeap(nodeList);
		
		Scanner scan = new Scanner(System.in);
		boolean abort = false;
		String inputParameter ="";
		Node newNode;
		while(!abort) {
			inputParameter="";
			System.out.println("**** ���� �켱 ���� ť�� ����Ǿ� �ִ� �۾� ��� ����� ������ �����ϴ�.****");
			System.out.println(heapList.list.size()+"�� �Դϴ�.");
			System.out.println();

			for(int i=0;i<heapList.list.size();i++) {
				System.out.println(heapList.list.get(i));
			}
			System.out.println();
			System.out.println();
			System.out.println("---------------------------------------------------");
			System.out.println("1. �۾� �߰�   2. �ִ밪    3. �ִ� �켱���� �۾� ó��");
			System.out.println("4. ���� Ű�� ����                5. �۾� ����         6.����");
			System.out.println("---------------------------------------------------");
			int input = scan.nextInt();
			scan.nextLine();
			switch(input) {
			case 1:
				System.out.println("���� �̸��� Ű ���� �Է��ϼ���.");
				System.out.println("(ex) '��ǻ�ͱ׷��Ƚ�, 109' ");
				
				inputParameter = scan.nextLine();
				String newName = inputParameter.split(", ")[0];
				int newKey = Integer.parseInt(inputParameter.split(", ")[1]);
				newNode = new Node(newKey , newName);
				heapList.insert(newNode);
				break;
			case 2:
				System.out.println("�ִ� �켱 ������ ���� �۾��Դϴ�.");
				System.out.println(heapList.max());
				break;
			case 3:
				heapList.extract_max();
				break;
			case 4:
				System.out.println("���� �̸��� ���ο� Ű ���� �Է��ϼ���. (������ �Էµ� Ű ���� ���� Ű ������ Ŀ�� �մϴ�.)");
				System.out.println("(ex) '���α׷��־���, 159' ");
				inputParameter = scan.nextLine();
				heapList.increase_key(heapList.get(inputParameter.split(", ")[0]), Integer.parseInt(inputParameter.split(", ")[1]));//���� �� insert ����� ���,,?
				break;
			case 5:
				System.out.println("���� �̸��� �Է��ϼ���.");
				inputParameter = scan.nextLine();
				heapList.delete(heapList.get(inputParameter));
				break;
			case 6: 
				abort = true;
				break;
			default: break;
			}	
		}	
	}
}
 