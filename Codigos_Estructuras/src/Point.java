public class Point extends Shape{
	int x, y;
	
	Point(){
		this.x = this.y = 0;
	}
	
	Point(int x, int y){
		this.x = x; 
		this.y = y;
	}
	
	public String getName(){
		return "Point";
	}
	
	public String toString(){
		return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ")";
	}
}

