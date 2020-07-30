import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class loop_Invariant {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		File Afile = new File("data06_a.txt");
		File Bfile = new File("data06_b.txt");
		
		BufferedReader bf_A = new BufferedReader(new FileReader(Afile));
		BufferedReader bf_B = new BufferedReader(new FileReader(Bfile));
		
		String aText = bf_A.readLine();
		String bText = bf_B.readLine();

		
		int[] database_A = new int[aText.split(", ").length];
		for(int i=0;i<database_A.length;i++) database_A[i]=Integer.parseInt(aText.split(", ")[i]);
		int[] database_B = new int[bText.split(", ").length];
		for(int i=0;i<database_B.length;i++) database_B[i]=Integer.parseInt(bText.split(", ")[i]);
		
		System.out.println("중간 값 : "+(int)find_Median_loop(database_A,database_B));
	}
	//반복
	public static int find_Median_loop(int[] database_A, int[] database_B) { 
	    int Alen = database_A.length;
	    int Blen = database_B.length; 
	    
	    
	    int AStart =0;
	    int BStart =0;
	    int N = (Alen + Blen)/2;
	    int AMedian = Integer.MAX_VALUE;
	    int BMedian = Integer.MAX_VALUE;    
	    int k = Math.min(database_A[AStart], database_B[BStart]);
	    
	    //pre-condition : database_A와 database_B는 각각 오름차순 정렬되어 있다.
	    while(AStart < database_A.length-1 && BStart < database_B.length - 1 && N > 1) {
		    
	    	if (AStart+N/2-1 < database_A.length) AMedian = database_A[AStart + N/2 - 1];
			if (BStart+N/2-1 < database_B.length) BMedian = database_B[BStart + N/2 - 1];
			
			if (AMedian < BMedian) {
				AStart = AStart+N/2;
			}else {
				BStart = BStart+N/2;
			}
			N = N- N/2;
			
			if(AStart >= database_A.length-1) 
				k= database_B[BStart+N-1];
			else if (BStart >= database_B.length-1)
				k= database_A[AStart+N-1];
			else k = Math.min(database_A[AStart], database_B[BStart]);
	    }
	    
	    return k;
	} 
	
}
