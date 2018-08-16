
public class Circle extends Point{
	double radius;
	public Circle() {super(); radius = 0.0;}
	
	public Circle(int x, int y, double radius) {
		super(x, y); this.radius = radius;
	}
	
	public double getArea(){ return Math.PI * radius * radius; }
	
	public String getName(){ return "Circle"; }
	
	public String toString() {
		return "Center " + super.toString() + " Radius: " + radius;
	}
}
