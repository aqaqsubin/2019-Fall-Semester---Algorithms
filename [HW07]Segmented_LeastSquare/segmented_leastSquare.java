import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class segmented_leastSquare {
	
	private static class Point{
		double x =0.0;
		double y =0.0;
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	private static class OPT{
		double leastError = 0.0;
		int i=0;
		int segmentCount =0;
		public OPT(double leastError, int i, int segmentCount) {
			this.leastError =leastError;
			this.i =i;
			this.segmentCount =segmentCount;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File inputFile = new File("data07.txt");
		BufferedReader bf = new BufferedReader(new FileReader(inputFile));
		String inputs = bf.readLine();
		StringTokenizer st = new StringTokenizer(inputs, ",");
		
		Point[] points = new Point[Integer.parseInt(st.nextToken())];
		for(int i=0;i<points.length;i++) {
			points[i] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		double c = Double.parseDouble(st.nextToken());
		double M = Segmented_Least_Squares(points,c);
	}	
	public static double Segmented_Least_Squares(Point[] P,double c) {
		OPT[] M = new OPT[P.length+1];
		M[0]= new OPT(0.0, 0, 0);
		
		double minValue = Double.MAX_VALUE;
		double[][] E = new double[P.length+1][P.length];
		int segmentCount =0;
		int brokenPoint=0;
		E[0][0]=0;
		
		for(int j = 1; j < M.length; j++) {	
			for(int i = 0; i < j; i++) {
				E[j][i] = SSE(P, i, j)[0];
			}
		}
		for(int j=1;j<M.length;j++) {
			minValue = Double.MAX_VALUE;
			segmentCount = 0;
			brokenPoint = 0;
			
			for(int i=0; i<j; i++) {
				double newValue = E[j][i] + c + M[i].leastError;
				if(minValue > newValue) {
					minValue = newValue;
					brokenPoint=i+1;
				}
			}
			segmentCount = brokenPoint== 0? M[j-1].segmentCount : M[brokenPoint-1].segmentCount+1;
			M[j] = new OPT(minValue, brokenPoint, segmentCount);
		}
		
		//결과 화면 출력
		double[][] linears = new double[M[M.length-1].segmentCount][3];

		int pre_brokenPoint=0;
		int post_brokenPoint=P.length;
		
		System.out.println("Cost of the optimal solution : "+M[M.length-1].leastError);
		System.out.println();
		System.out.println("An optimal solution");
		
		for(int i=0;i<M[M.length-1].segmentCount;i++) {
			while(M[post_brokenPoint-1].i-1 > pre_brokenPoint) {
				post_brokenPoint = M[post_brokenPoint-1].i-1;
			}	
			linears[i]= SSE(P,pre_brokenPoint,post_brokenPoint);
			System.out.println("[Segment "+(pre_brokenPoint+1)+" - "+post_brokenPoint+"] : y = "+linears[i][1]+" * x + "+linears[i][2]+ "\t// square error : "+linears[i][0]);
			
			pre_brokenPoint = post_brokenPoint;
			post_brokenPoint = P.length;
		}
		
		
		return M[M.length-1].leastError;
		
	}
	//P[p1]와 P[p2]를 지나는 직선 ㅣ을 가질때, sum of squared error를 반환하는 메소드
	public static double[] SSE(Point[] P, int p1, int p2) {
		double sumX = 0.0;
		double sumY = 0.0;
		double sumXY = 0.0;
		double sumX2 = 0.0;
		int n = p2-p1;
		
		for(int i=p1;i<p2;i++) {
			sumX = sumX + P[i].x;
			sumY = sumY + P[i].y;
			sumXY = sumXY + P[i].x * P[i].y;
			sumX2 = sumX2 + P[i].x * P[i].x;
		}
		double a = (n*sumXY - (sumX*sumY))/(n*sumX2-(sumX*sumX));
		double b = (sumY - a*sumX)/n;
		
		double sumOfError =0.0;
		
		for(int i=p1; i<p2; i++) {
			sumOfError = sumOfError + (P[i].y - a* P[i].x - b)*(P[i].y - a* P[i].x - b);
		}
		double[] returnValue = {sumOfError,a,b};
		return returnValue;
	}
	
}
