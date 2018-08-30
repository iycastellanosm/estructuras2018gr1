import java.io.*;

public class ejercicio2 {
	
	public static boolean triangle[][];
	
	ejercicio2(int size){
		triangle = new boolean[size][size];
	}
	
	public static void sierpinski(int y, int x, int size, int level){
		if(level == 0){
			for(int i = 0; i < size; i++) {
				for(int j = 0; j <= i; j++) {
					triangle [y + i][x + j] = true;
				}
			}
			return;
		}
		sierpinski(y, x, size/2, level - 1);
		sierpinski(y + size/2, x, size/2, level - 1);
		sierpinski(y + size/2, x + size/2, size/2, level - 1);
		return;
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt ( reader.readLine() );
		int level = Integer.parseInt ( reader.readLine() );
		triangle = new boolean[size][size];
		sierpinski(0, 0, size, level);
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(triangle[i][j]) System.out.print("#");
				else System.out.print("_");
			}
			System.out.println("");
		}
	} 
}
