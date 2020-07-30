import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Scanner;

public class fibonacci {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("¹æ¹ý");
		System.out.println(" 1 : Recursion");
		System.out.println(" 2 : Array");
		System.out.println(" 3 : Recursive squaring");
		
		int kindOfFunc = scan.nextInt();
		BigInteger num = scan.nextBigInteger();
		DecimalFormat format = new DecimalFormat();
		format.applyLocalizedPattern("0.000000000");
		
		long startTime = 0;
		long endTime = 0;
		
		switch(kindOfFunc) {
		case 1 :
			for(BigInteger i=BigInteger.valueOf(0);i.compareTo(num)<1;i=i.add(BigInteger.valueOf(1))) {
				if(i.mod(BigInteger.valueOf(10))==BigInteger.valueOf(0)) System.out.println("-----------------------------------------------");

				startTime = System.nanoTime();
				System.out.print("f <"+i+"> = "+recursion(i));
				endTime = System.nanoTime();
				System.out.println("\t"+format.format((float)(endTime-startTime)/(float)1000000000)+" sec");
				
			}
			break;
		case 2 :
			for(BigInteger i=BigInteger.valueOf(0);i.compareTo(num)<1;i=i.add(BigInteger.valueOf(1))) {
				if(i.mod(BigInteger.valueOf(10))==BigInteger.valueOf(0)) System.out.println("-----------------------------------------------");

				startTime = System.nanoTime();
				System.out.print("f <"+i+"> = "+array(i));
				endTime = System.nanoTime();
				System.out.println("\t"+format.format((float)(endTime-startTime)/(float)1000000000)+" sec");
				
			}
			break;
		case 3 : 
			for(BigInteger i=BigInteger.valueOf(0);i.compareTo(num)<1;i=i.add(BigInteger.valueOf(1))) {
				if(i.mod(BigInteger.valueOf(10))==BigInteger.valueOf(0)) System.out.println("-----------------------------------------------");
				startTime =System.nanoTime();
				System.out.print("f <"+i+"> = "+recursive_squaring(i));
				endTime = System.nanoTime();
				System.out.println("\t"+format.format((float)(endTime-startTime)/(float)1000000000)+" sec");
				
			}
			break;
		}

	}

	public static BigInteger recursion(BigInteger n) {
		if(n.compareTo(BigInteger.valueOf(2))==-1) return n;
		return recursion(n.subtract(BigInteger.valueOf(1))).add(recursion(n.subtract(BigInteger.valueOf(2))));
	}
	public static BigInteger array(BigInteger n) {
		BigInteger[] fibo_array = new BigInteger[3];
		fibo_array[0]=BigInteger.valueOf(0);
		fibo_array[1]=BigInteger.valueOf(1);
		
		for(BigInteger i=BigInteger.valueOf(2);i.compareTo(n)<1;i=i.add(BigInteger.valueOf(1))) {
			int n_1 = ((i.subtract(BigInteger.valueOf(1))).mod(BigInteger.valueOf(3))).intValue();
			int n_2 = ((i.subtract(BigInteger.valueOf(2))).mod(BigInteger.valueOf(3))).intValue();
			fibo_array[(i.mod(BigInteger.valueOf(3))).intValue()]= fibo_array[n_1].add(fibo_array[n_2]);
		}
		return fibo_array[(n.mod(BigInteger.valueOf(3))).intValue()];
	}	
	public static BigInteger recursive_squaring(BigInteger n) {
		if(n.compareTo(BigInteger.valueOf(2))==-1) return n;
		
		BigInteger [][] A = {{BigInteger.valueOf(1),BigInteger.valueOf(1)},{BigInteger.valueOf(1),BigInteger.valueOf(0)}};
		return pow(A, n)[0][1];
	}
	public static BigInteger[][] pow(BigInteger[][] A, BigInteger n) {
		if(n.compareTo(BigInteger.valueOf(1))==0) return A;
		
		if(n.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(0))==0) {
			BigInteger[][] pow_result = pow(A,n.divide(BigInteger.valueOf(2)));
			return mul(pow_result, pow_result);
		}else {
			return mul(pow(A, (n.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2))), pow(A, (n.add(BigInteger.ONE)).divide(BigInteger.valueOf(2))));
		}
	}
	public static BigInteger[][] mul(BigInteger[][] A, BigInteger[][] B){
		int n = A.length;
		BigInteger[][] C = new BigInteger[n][n];

		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				C[i][j] = BigInteger.ZERO;
				for(int k=0;k<n;k++) {
					C[i][j] = C[i][j].add(A[i][k].multiply(B[k][j]));
				}
			}
		}
		return C;
		
	}
}

