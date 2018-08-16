
public class Cylinder extends Circle{
	double height;
	public Cylinder() {super(); height = 0.0;}
	
	public Cylinder(int x, int y, double radius, double height) {
		super(x, y, radius); this.height = height;
	}
	
	public double getArea(){
		return 2.0 * (super.getArea() + Math.PI * this.radius);
	}
	
	public double getVolume(){
		return super.getArea() * height;
	}
	
	public String getName(){ return "Cylinder";}
	
	public String toString() {
		return super.toString() + " Height: " + height;
	}
}

