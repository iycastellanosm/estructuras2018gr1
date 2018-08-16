
public class PoliTest {
	static void printAll(Shape[] arr){
		for(int i = 0; i<arr.length; i++) {
			if(arr[i] != null) System.out.println(arr[i].getName() + ": " + arr[i] + "\n" + "Area: " + arr[i].getArea() + " Volume: " + arr[i].getVolume() + "\n");
		}
	}
	
	public static void main(String args[]){
		Shape[] a = { new Point(7, 11),
				new Circle(22, 8, 3.5), null,
				new Metal_Cylinder (20, 30, 3.3, 10.75)};
		printAll(a);
	}
}

