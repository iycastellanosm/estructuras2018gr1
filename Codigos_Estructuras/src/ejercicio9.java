import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ejercicio9 {
	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		int turnos = 0;
		Coladeprioridad sistema_embebido = new Coladeprioridad();
		while((line=reader.readLine())!=null) {
			if(line.equals("TASK")) {
				Proceso nuevoproceso = new Proceso(Integer.parseInt(reader.readLine()), Long.parseLong(reader.readLine()), reader.readLine(), turnos++);
				sistema_embebido.add(nuevoproceso);
			}
			else if(line.equals("EXECUTE")) {
				Proceso maximo = sistema_embebido.poll();
				if(maximo == null) {
					System.out.println("TASK NOT FOUND");
				}
				else {
					System.out.println(maximo);
				}
			}
			else if(line.equals("KILL")) {		
				if(sistema_embebido.kill(Integer.parseInt(reader.readLine()))){
					System.out.println("TASK KILLED");
				}
				else{
					System.out.println("TASK NOT FOUND");
				}
				
			}
			else if(line.equals("CHANGE")) {
				if(sistema_embebido.change(Integer.parseInt(reader.readLine()), Long.parseLong(reader.readLine()))){
					System.out.println("TASK RESCHEDULED");
				}
				else{
					System.out.println("TASK NOT FOUND");
				}
			}
			else{
				sistema_embebido = new Coladeprioridad();
				System.out.println("CLEARED");
			}
			//System.out.println(sistema_embebido);
		}
	}
}

class Proceso implements Comparable<Proceso>{
	int id;
	long priority;
	int turno;
	String description;
	
	public Proceso(int id, long priority, String description, int turno) {
		this.id = id;
		this.priority = priority;
		this.description = description;
		this.turno = turno;
	}
	
	public Proceso() {
		this(0, 0, null, 0);
	}

	public int compareTo(Proceso otro) {
		if(this.priority > otro.priority)return 1;
		if(this.priority < otro.priority)return -1;
		if(this.turno < otro.turno)return 1;
		if(this.turno > otro.turno)return -1;
		return 0;
	}
	
	public String toString(){
		return description;
	}
}

class Coladeprioridad{
	Proceso arbol[];
	int posicion_en_arbol[];
	int size;
	
	public Coladeprioridad(int cantidad) {
		this.arbol = new Proceso[cantidad];
		this.posicion_en_arbol = new int[200001];
	}
	
	public Coladeprioridad() {
		this(10);
	}
	
	public void add(Proceso tarea) {
		if(size == arbol.length - 1) {
			Proceso nuevoarbol[] = new Proceso [2 * (size + 1)];
			System.arraycopy(arbol, 0, nuevoarbol, 0, size + 1);
			arbol = nuevoarbol;
		}
		int currElement = ++size;
		while(currElement > 1 && arbol[currElement/2].compareTo(tarea) < 0) {
			arbol[currElement] = arbol[currElement/2];
			posicion_en_arbol[arbol[currElement].id] = currElement;
			currElement /= 2;
		}
		arbol[currElement] = tarea;
		posicion_en_arbol[arbol[currElement].id] = currElement;
	}
	
	public Proceso poll() {
		if(size==0) return null;
		Proceso lastElement = arbol[size--];
		Proceso maxElement = arbol[1];
		int currElement = 1;
		int child = 2;
		while(child <= size){
			if(child < size && arbol[child].compareTo(arbol[child+1]) < 0) child++;
			if(lastElement.compareTo(arbol[child]) >= 0) break;
			arbol[currElement] = arbol[child];
			posicion_en_arbol[arbol[currElement].id] = currElement;
			currElement = child;
			child *= 2;
		}
		arbol[currElement] = lastElement;
		posicion_en_arbol[arbol[currElement].id] = currElement;
		posicion_en_arbol[maxElement.id] = 0;
		return maxElement;
	}
	
	public Proceso peek() {
		return size == 0? null:arbol[1];
	}
	
	public boolean kill(int idproceso){
		if(!change(idproceso, Long.MAX_VALUE))return false;
		poll();
		return true;
	}
	
	public boolean change(int idproceso, long nuevaprioridad){
		if(posicion_en_arbol[idproceso] == 0)return false;
		int currElement = posicion_en_arbol[idproceso];
		int child = currElement * 2;
		arbol[currElement].priority = nuevaprioridad;
		Proceso elementoCamb = arbol[currElement];
		while(child <= size) {
			if(child < size && arbol[child].compareTo(arbol[child + 1]) < 0) child++;
			if(elementoCamb.compareTo(arbol[child]) >= 0) break;
			arbol[currElement] = arbol[child];
			posicion_en_arbol[arbol[currElement].id] = currElement;
			currElement = child;
			child *= 2;
		}
		arbol[currElement] = elementoCamb;
		posicion_en_arbol[arbol[currElement].id] = currElement;
		while(currElement > 1 && arbol[currElement/2].compareTo(elementoCamb) < 0) {
			arbol[currElement] = arbol[currElement/2];
			posicion_en_arbol[arbol[currElement].id] = currElement;
			currElement /= 2;
		}
		arbol[currElement] = elementoCamb;
		posicion_en_arbol[arbol[currElement].id] = currElement;
		return true;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		StringBuffer strb = new StringBuffer();
		for(int i = 1; i<=size; i++) {
			strb.append(arbol[i].toString() + " ");
		}
		return strb.toString();
	}
}