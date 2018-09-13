import java.util.*;

public class ImageComponents {
    static int[][] pixel;
    static int size;
    
    private static void inputImage(){
        Scanner s = new Scanner (System.in);
        System.out.println("Enter Image size");
        size = s.nextInt();

        pixel = new int[size + 2][size + 2];
        System.out.println("Enter the pixel array in row-major order");
        for(int i = 1; i<= size; i++)
            for(int j = 1; j<= size; j++)
                pixel[i][j] = s.nextInt();
    }
    
    private static void labelComponents(){
		Position[] offset = {new Position(0, 1), new Position(1, 0), new Position(0, -1), new Position(-1, 0)};
		for(int i = 0; i <= size + 1; i++)
			pixel[0][i] = pixel[size+1][i] = pixel[i][0] = pixel[i][size+1] = 0;
		int numOfNbrs = 4, id = 1;
		ArrayQueue<Position> q = new ArrayQueue<>();
		for(int r = 1; r<=size ; r++)
			for(int c = 1; c<=size ; c++)
				if(pixel[r][c]== 1){
					pixel[r][c] = ++id;
					q.put(new Position(r, c));
					while(!q.isEmpty()){
						Position here = q.remove();
						for(int i = 0; i<numOfNbrs; i++){
							Position nbr = here.add(offset[i]);
							if(pixel[nbr.row][nbr.col] == 1){
								pixel[nbr.row][nbr.col] = id;
								q.put(nbr);
							}
						}
					}
				}
	}
    
    public static void outputImage(){
        System.out.println("the labeled imaged is");
        for(int i = 1; i<= size;i++){
            for(int j = 1; j<= size; j++)
                System.out.print(pixel[i][j] + "  ");
            System.out.println();
        }
    }

    public static void main(String[] args){
        inputImage();
        labelComponents();
        outputImage();
    }
}
