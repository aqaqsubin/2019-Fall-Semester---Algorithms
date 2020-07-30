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
		//max heap 만들기
		makeHeap heapList = new makeHeap(nodeList);
		
		Scanner scan = new Scanner(System.in);
		boolean abort = false;
		String inputParameter ="";
		Node newNode;
		while(!abort) {
			inputParameter="";
			System.out.println("**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다.****");
			System.out.println(heapList.list.size()+"개 입니다.");
			System.out.println();

			for(int i=0;i<heapList.list.size();i++) {
				System.out.println(heapList.list.get(i));
			}
			System.out.println();
			System.out.println();
			System.out.println("---------------------------------------------------");
			System.out.println("1. 작업 추가   2. 최대값    3. 최대 우선순위 작업 처리");
			System.out.println("4. 원소 키값 증가                5. 작업 제거         6.종료");
			System.out.println("---------------------------------------------------");
			int input = scan.nextInt();
			scan.nextLine();
			switch(input) {
			case 1:
				System.out.println("원소 이름과 키 값을 입력하세요.");
				System.out.println("(ex) '컴퓨터그래픽스, 109' ");
				
				inputParameter = scan.nextLine();
				String newName = inputParameter.split(", ")[0];
				int newKey = Integer.parseInt(inputParameter.split(", ")[1]);
				newNode = new Node(newKey , newName);
				heapList.insert(newNode);
				break;
			case 2:
				System.out.println("최대 우선 순위를 갖는 작업입니다.");
				System.out.println(heapList.max());
				break;
			case 3:
				heapList.extract_max();
				break;
			case 4:
				System.out.println("원소 이름과 새로운 키 값을 입력하세요. (새로이 입력된 키 값은 기존 키 값보다 커야 합니다.)");
				System.out.println("(ex) '프로그래밍언어개론, 159' ");
				inputParameter = scan.nextLine();
				heapList.increase_key(heapList.get(inputParameter.split(", ")[0]), Integer.parseInt(inputParameter.split(", ")[1]));//삭제 후 insert 방식은 어떤지,,?
				break;
			case 5:
				System.out.println("원소 이름을 입력하세요.");
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
 