public class Ejemplos_Objetos {
	
	int x;
	
	int max(int a, int b){
		return 0;
	}
	
	int max(int a, int b, int c){
		return 1;
	}
	
	int max(float a, float b){
		return 2;
	}
	
	static void initialize(int a){
		a = 0;
	}
	
	static void initialize(Point a){
		a.x = a.y = 0;
	}
	
	public static void main(String[] args){
		int a, b;
		a = 5;
		b = 5;
		System.out.println(a==b);
		Integer c, d;
		c = new Integer(5);
		d = new Integer(5);
		System.out.println(c==d);
		
		byte e;
		double f = 199.99;
		//e = f;
		e = (byte) f;
		System.out.println(e);
		
		Ejemplos_Objetos ejemplo = new Ejemplos_Objetos();
		
		System.out.println(ejemplo.max(10, 20));
		System.out.println(ejemplo.max(10, 20, 30));
		System.out.println(ejemplo.max((float)10.0, (float)20.0));
		System.out.println(ejemplo.max(10, (float)20.0));
		
		int g = 1;
		initialize(g);
		System.out.println(g);
		
		Point p1 = new Point(1, 1);
		initialize(p1);
		System.out.println(p1);
		
	}
}
