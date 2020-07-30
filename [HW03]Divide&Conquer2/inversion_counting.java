import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class inversion_counting {
	private static class inversionsList{
		double[] array=null;
		int count=0;
		public inversionsList(){
			count =0;
			array=null;
		}
	}

	public static void counting(File inputFile) throws IOException {
		
		BufferedReader bf_inversion = new BufferedReader(new FileReader(inputFile));
		
		String inversion_nums = bf_inversion.readLine();
		System.out.println("Input Data : "+inversion_nums);
		
		StringTokenizer st = new StringTokenizer(inversion_nums, ",");
		int num = st.countTokens();
		
		inversionsList input_list= new inversionsList();
		input_list.array = new double[num];
		
		for(int i=0;i<num;i++) {
			input_list.array[i]=Double.valueOf(st.nextToken());
		}
		inversionsList output = sort_count(input_list);

		System.out.println("Output Data : "+output.count);
	}
	public static inversionsList sort_count(inversionsList list) {
		if(list.array.length == 1) {
			list.count=0;
			return list;
		}
		
		inversionsList Alist = new inversionsList();
		inversionsList Blist =  new inversionsList();
		Alist.array = new double[(int)(list.array.length/2)];
		Blist.array = new double[(int)(list.array.length-Alist.array.length)];
		System.arraycopy(list.array, 0, Alist.array, 0, Alist.array.length);
		System.arraycopy(list.array, Alist.array.length, Blist.array, 0, Blist.array.length);
		
		Alist = sort_count(Alist);
		Blist = sort_count(Blist);
		list = merge_sort(Alist,Blist);
		list.count = Alist.count+list.count+Blist.count;
		return list;
	}
	public static inversionsList merge_sort(inversionsList Alist, inversionsList Blist) {
		int inversion_count = 0;
		int indexA =0;
		int indexB =0;
		inversionsList list =  new inversionsList();
		list.array = new double[Alist.array.length+Blist.array.length];
		while((Alist.array.length!=indexA)&&(Blist.array.length!=indexB)) {
			if(Alist.array[indexA]>Blist.array[indexB]) {
				inversion_count= inversion_count+Alist.array.length-indexA;
				list.array[indexA+indexB]=Blist.array[indexB];
				indexB++;
			}else {
				list.array[indexA+indexB]=Alist.array[indexA];
				indexA++;
			}
		}
		if(Alist.array.length-indexA==0) System.arraycopy(Blist.array,indexB, list.array, indexA+indexB, list.array.length-(indexA+indexB));
		else System.arraycopy(Alist.array,indexA, list.array, indexA+indexB, list.array.length-(indexA+indexB));
		
		list.count= inversion_count;
		return list;
	}
}
