
public class Metal_Cylinder extends Cylinder implements I_Shape, I_Material{

	public Metal_Cylinder(){ super(); }
	
	public Metal_Cylinder(int x, int y, double radius, double height) {
		super(x, y, radius, height);
	}
	
	@Override
	public double getDensidad() {
		return aluminio * getVolume();
	}
	
	public String getName(){
		return "Metal_Cylinder";
	}

}
