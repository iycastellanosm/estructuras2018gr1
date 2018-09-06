import java.util.*;

public class WireRouter {
    static int[][] grid;
    static int size;
    static Position start, finish;
    static Position[] path;

    private static void inputData(){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter grid size");
        size = s.nextInt();
        System.out.println("Enter the start position");
        start = new Position(s.nextInt(), s.nextInt());
        System.out.println("Enter the finish position");
        finish = new Position(s.nextInt(), s.nextInt());
        grid = new int[size + 2][size + 2];
        System.out.println("Enter the wiring grid in row-major order");
        for(int i=1;i<=size;i++)
            for(int j=1;j <= size; j++)
                grid[i][j] = s.nextInt();
    }
    
    private static boolean findPath(){
		if(start.equals(finish)) return true;
		Position[] offset = {new Position(0, 1), new Position(1, 0), new Position(0, -1), new Position(-1, 0)};
		Position here, nbr = new Position();
		for(int i=0; i<= size + 1; i++)
			grid[0][i] = grid[size + 1][i] = grid[i][0] = grid[i][size + 1] = 1;
		int numOfNbrs = 4;
		LinkedQueue<Position> q = new LinkedQueue<>();
		q.put(new Position(start.row, start.col));
		grid[start.row][start.col] = 2;
		while(true){
			here = q.remove();
			for(int i=0; i<numOfNbrs; i++){
				nbr = here.add(offset[i]);
				if(grid[nbr.row][nbr.col] == 0){
					grid[nbr.row][nbr.col] = grid[here.row][here.col] + 1;
					if(nbr.equals(finish)) break;
					q.put(nbr);
				}
			}
			if(nbr.equals(finish)) break;
			if(q.isEmpty()) return false;
		}
		int pathLength = grid[finish.row][finish.col]-2;
		path = new Position[pathLength];
		here = finish;
		for(int j = pathLength - 1; j>= 0; j--){
			path[j] = here;
			for(int i = 0; i < numOfNbrs; i++){
				nbr = here.add(offset[i]);
				if(grid[nbr.row][nbr.col] == j + 2) break;
			}
			here = nbr;
		}
		return true;
	}	
    
    private static void outputPath(){
        System.out.println("The wire path is");
        for(Position x: path)
            System.out.println(x);
    }
    
    public static void main(String[] args){
        inputData();
        if(findPath()) outputPath();
        else System.out.println("There is no wire path");
    }
}