import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class InsertionSort {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		File file = new File("data02.txt");
		BufferedReader bf = new BufferedReader(new FileReader(file));
		String inputNumber = bf.readLine();
		StringTokenizer st= new StringTokenizer(inputNumber, ",");
		int length = st.countTokens();
		int[] inputNums = new int[length];
		for(int i=0;i<length;i++) {
			int num= Integer.parseInt(st.nextToken());
			inputNums[i] = num;
		}
		
		int[] insertion = Insertion_Sort(inputNums);		
		writeResult(insertion);
	}
	public static int[] Insertion_Sort(int[] input) {
		int key=0;
		int i=0;
		
		for(int j=1;j<input.length;j++) {
			key = input[j];
			i=j-1;
			while(i>-1 && input[i] > key) {
				input[i+1]=input[i];
				i=i-1;
			}
			input[i+1]=key;
		}
		return input;
	}
	public static void writeResult(int[] resultArray) throws IOException{
		FileWriter writer = new FileWriter("hw01_05_201701988_insertion.txt");
		String result = "";
		result = result+(String)(resultArray[0]+"");
		
		for(int i=1;i<resultArray.length;i++) {
			result = result + (String) (","+resultArray[i]);
		}
		System.out.println(result);
		
		writer.write(result);
		writer.close();
		
	}

}


