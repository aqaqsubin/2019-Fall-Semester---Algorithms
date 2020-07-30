import java.io.File;
import java.io.IOException;

public class hw04_DC2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File closestFile = new File("data03_closest.txt");
		finding_closest_pair.finding(closestFile);
		
		File inversionFile = new File("data03_inversion.txt");
		inversion_counting.counting(inversionFile);
		
	}

}
