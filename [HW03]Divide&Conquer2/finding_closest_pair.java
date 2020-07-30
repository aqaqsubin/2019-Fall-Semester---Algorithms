import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class finding_closest_pair {
	private static class point {
		double x;
		double y;
		public point() {
			double x = 0.0;
			double y = 0.0;
		}
	}
	
	public static void finding(File inputFile) throws IOException {
		BufferedReader bf_closest = new BufferedReader(new FileReader(inputFile));
		String text = "";
		String line = bf_closest.readLine();
		
		ArrayList<point> points = new ArrayList<point>();
		while(line!=null) {
			text = text+"\n"+line;
			StringTokenizer st = new StringTokenizer(line, ",");
			
			point newP = new point();
			newP.x = Double.valueOf(st.nextToken());
			newP.y = Double.valueOf(st.nextToken());
			points.add(newP);
			line = bf_closest.readLine();
		}
		System.out.println("Input Data : "+text);
		
		point[] sorted_points = new point[points.size()];
		for(int i=0;i<points.size();i++) sorted_points[i] =points.get(i);
		
//		sorted_points = sorting(sorted_points,0);
		System.out.printf("Output Data : %.3f\n",closest_pair(sorted_points));
		
	}
	public static point[] sorting(point[] points, int flag) {
		if(points.length<=1) return points;
		
		point[] list_a = new point[(int) points.length%2==0? points.length/2:(points.length-1)/2];
		point[] list_b = new point[(points.length-list_a.length)];
		System.arraycopy(points, 0, list_a, 0, list_a.length);
		System.arraycopy(points, list_a.length, list_b, 0, list_b.length);
		
		list_a = sorting(list_a, flag);
		list_b = sorting(list_b, flag);
		points = merge(list_a, list_b, flag);
		return points;
	}
	public static point[] merge(point[] listA, point[] listB, int flag) {
		int indexA=0;
		int indexB =0;
		double compute_A=0.0;
		double compute_B=0.0;
		
		point[] points = new point[listA.length+listB.length];
		while((listA.length!=indexA)&&(listB.length!=indexB)) {			
			compute_A = flag==0?listA[indexA].x:listA[indexA].y;
			compute_B= flag==0?listB[indexB].x:listB[indexB].y;	
			if(compute_A<=compute_B) {
				points[indexA+indexB]=listA[indexA];
				indexA++;
			}else {
				points[indexA+indexB]=listB[indexB];
				indexB++;
			}
			
		}
		if(listA.length-indexA==0) System.arraycopy(listB, indexB, points, indexA+indexB, points.length-(indexA+indexB));
		else System.arraycopy(listA,indexA, points, indexA+indexB, points.length-(indexA+indexB));
		return points;
	}
	public static double closest_pair(point[] points) {
		
		if(points.length <= 3) {
			double min = Math.sqrt(Math.pow((points[0].x-points[1].x),2) +Math.pow((points[0].y-points[1].y),2)); 
			for(int i=0;i<points.length-1;i++) {		
				for(int j=i+1;j<points.length;j++) {
					if(min > Math.sqrt(Math.pow((points[i].x-points[j].x),2) +Math.pow((points[i].y-points[j].y),2)))
						min = Math.sqrt(Math.pow((points[i].x-points[j].x),2) +Math.pow((points[i].y-points[j].y),2));
				}
			}
			return min;
		}
		points = sorting(points,0);
		point[] listA= new point[(int) points.length/2];
		point[] listB = new point[(points.length-listA.length)];
		System.arraycopy(points, 0, listA, 0, listA.length);
		System.arraycopy(points, listA.length, listB, 0, listB.length);
		
		double L = (double)(points[listA.length-1].x + points[listA.length].x)/(double)2;
		
		//min distance theta
		double distance_A = closest_pair(listA);
		double distance_B = closest_pair(listB);
		double theta = distance_A >distance_B? distance_B:distance_A;
		//delete
		int indexA=listA.length-1;
		int indexB=0;
		
		for(indexA=listA.length-1;indexA>=0;indexA--) {
			if(listA[indexA].x < L-theta) break;
		}
		for(indexB=0;indexB<listB.length;indexB++) {
			if(listB[indexB].x > L+theta) break;
		}
		point[] temp_list = new point[(listA.length-indexA-1)+indexB];
		System.arraycopy(listA, indexA+1, temp_list, 0, listA.length-indexA-1);
		System.arraycopy(listB, 0, temp_list, listA.length-indexA-1, indexB);
		
		temp_list = sorting(temp_list,1);	
		for(int i=0;i<temp_list.length;i++) {
			for(int j=i-1;j>=0;j--) {
				if(Math.abs(temp_list[i].y-temp_list[j].y)<=theta) {
					if(Math.sqrt(Math.pow(temp_list[i].x-temp_list[j].x, 2)+ Math.pow(temp_list[i].y-temp_list[j].y,2)) < theta 
							&& Math.sqrt(Math.pow(temp_list[i].x-temp_list[j].x, 2)+ Math.pow(temp_list[i].y-temp_list[j].y,2))!=0) {
						theta = Math.sqrt(Math.pow(temp_list[i].x-temp_list[j].x, 2)+ Math.pow(temp_list[i].y-temp_list[j].y,2));
					}
				}else break;
			}
			for(int j=i+1;j<temp_list.length;j++) {
				if(Math.abs(temp_list[i].y-temp_list[j].y)<=theta) {
					if(Math.sqrt(Math.pow(temp_list[i].x-temp_list[j].x, 2)+ Math.pow(temp_list[i].y-temp_list[j].y,2)) < theta 
							&& Math.sqrt(Math.pow(temp_list[i].x-temp_list[j].x, 2)+ Math.pow(temp_list[i].y-temp_list[j].y,2))!=0) {
						theta = Math.sqrt(Math.pow(temp_list[i].x-temp_list[j].x, 2)+ Math.pow(temp_list[i].y-temp_list[j].y,2));
					}
				}else break;
			}
		}
		
	
		return theta;
	}
	

}
