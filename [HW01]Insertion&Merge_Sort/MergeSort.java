import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class MergeSort {
	static int merge_num =0;

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

		writeResult(mergeSort(inputNums));
	}
	public static int[] merge(int[] halfInput, int[] otherInput) {
		int halfIndex = 0;
		int otherIndex = 0;
		int[] output = new int[halfInput.length+otherInput.length];
		int outputIndex = 0;

		while((halfInput.length>halfIndex) && (otherInput.length>otherIndex)) {
			if(halfInput[halfIndex]<otherInput[otherIndex]) {
				output[outputIndex]=halfInput[halfIndex];
				halfIndex++;
			}else {
				output[outputIndex]=otherInput[otherIndex];
				otherIndex++;
			}
			outputIndex++;
		}
		if(outputIndex<output.length) {
			System.arraycopy((halfInput.length-halfIndex>otherInput.length-otherIndex)?halfInput:otherInput,(halfInput.length-halfIndex>otherInput.length-otherIndex)?halfIndex:otherIndex, output, outputIndex, output.length-outputIndex);
		}
		merge_num++;
		return output;
	}
	public static int[] mergeSort(int[] inputArray) {
		
		if(inputArray.length==1) return inputArray;
		else {
			int[] halfArray=new int[(int)inputArray.length/2];
			int[] otherArray=new int[inputArray.length%2==0?inputArray.length/2:(int)(inputArray.length/2)+1];
			
			System.arraycopy(inputArray, 0, halfArray, 0, halfArray.length);
			System.arraycopy(inputArray, halfArray.length, otherArray, 0, otherArray.length);

			return merge(mergeSort(halfArray),mergeSort(otherArray));
		}
	}

	public static void writeResult(int[] resultArray) throws IOException{
		FileWriter writer = new FileWriter("hw01_05_201701988_merge.txt");
		String result = "";
		result = result+(String)(resultArray[0]+"");
		
		for(int i=1;i<resultArray.length;i++) {
			result = result + (String) (","+resultArray[i]);
		}
		result = result+","+merge_num;
		
		writer.write(result);
		writer.close();
		
	}
}
