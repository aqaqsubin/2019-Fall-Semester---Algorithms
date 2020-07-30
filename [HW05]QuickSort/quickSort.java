import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class quickSort {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		File file = new File("data05.txt");
		BufferedReader bf = new BufferedReader(new FileReader(file));
		String line = bf.readLine();	
		StringTokenizer st = new StringTokenizer(line, ",");
		ArrayList numbers = new ArrayList();
		for(String ns ="";st.hasMoreTokens();) {
			ns = st.nextToken();
			numbers.add(Integer.parseInt(ns));
		}
		ArrayList numbersRandom = (ArrayList) numbers.clone();
//		System.out.println(numbersRandom.size());
		
//		File file2 = new File("data05_sorted2.txt");
//		BufferedReader bf2 = new BufferedReader(new FileReader(file2));
//		String line2 = bf2.readLine();
		
		quickSort(numbers,0,numbers.size()-1);
		quickSort_withRandom(numbersRandom,0,numbersRandom.size()-1);
		String checking = file_output(numbers,"hw05_05_201701988_quick.txt");
		String checkingRandom = file_output(numbersRandom,"hw05_05_201701988_quickRandom.txt");
//		System.out.println(checking.hashCode()==line2.hashCode());
//		System.out.println(checkingRandom.hashCode()==line2.hashCode());
		
	}

	public static int partition(ArrayList A, int p, int r) {
		int pivot = r;
		int i = p-1;
		int temp;
		for(int j= p; j<r;j++) {
			if((int)A.get(j) <= (int)A.get(pivot)) {
				i++;
				temp = (int)A.get(j);
				A.set(j,(int)A.get(i));
				A.set(i, temp);
			}
		}
		i++;
		temp = (int)A.get(r);
		A.set(r,(int)A.get(i));
		A.set(i, temp);
		return i;
	}
	public static int randomizedPartition(ArrayList A, int p, int r) {
		int i = (int)(Math.random() * (r-p+1)) + Math.min(p, r);
		int temp = (int)A.get(r);
		A.set(r,(int)A.get(i));
		A.set(i, temp);
		return partition(A,p,r);
	}
	public static void quickSort(ArrayList A, int p, int r) {
		if(p>=r) return;
		int q = partition(A,p,r);
		quickSort(A,p,q-1);
		quickSort(A,q+1,r);
		return;
	}
	public static void quickSort_withRandom(ArrayList A, int p, int r) {
		if(p>=r) return;
		int q = randomizedPartition(A,p,r);
		quickSort_withRandom(A,p,q-1);
		quickSort_withRandom(A,q+1,r);
		
		return;
	}
	public static String file_output(ArrayList A,String fileName) throws IOException{
		FileWriter writer = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(writer);
		String result ="";
		for(int i=0;i<A.size()-1;i++) {
			result = result+(int)A.get(i)+",";
		}
		result = result+(int)A.get(A.size()-1);
		
		bw.write(result);
		bw.close();
		return result;
	}

}
