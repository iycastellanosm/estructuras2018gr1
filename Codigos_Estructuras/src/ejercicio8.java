import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ejercicio8 {
	static NodoArbol arbol[];
	
	static double resolver(int nodo){
		if(arbol[nodo].isResuelto()){
			return arbol[nodo].getValor();
		}
		double op1 = resolver(arbol[nodo].getHijoizq());
		double op2 = resolver(arbol[nodo].getHijoder());
		if(!arbol[arbol[nodo].getHijoizq()].isResuelto() || !arbol[arbol[nodo].getHijoder()].isResuelto()){
			arbol[nodo].setResuelto(false);
			return 0.0;
		}
		arbol[nodo].setResuelto(true);
		if(arbol[nodo].getOperacion().equals("/")) {
			if(Math.abs(op2) < 0.0000000001) {
				arbol[nodo].setResuelto(false);
				return 0.0;
			}
			return op1 / op2;
		}
		if(arbol[nodo].getOperacion().equals("+")) {
			return op1 + op2;
		}
		if(arbol[nodo].getOperacion().equals("-")) {
			return op1 - op2;		
		}
		return op1 * op2;
	}
	
	static double resolver2(){
		int nodo = 1;
		Stack<Integer> pila = new ArrayStack<Integer>();
		pila.push(nodo);
		while(!pila.isEmpty()){
			nodo = pila.peek();
			if(arbol[nodo].isResuelto()){
				pila.pop();
			}
			else {
				if(arbol[arbol[nodo].getHijoizq()].isResuelto() && arbol[arbol[nodo].getHijoder()].isResuelto()) {
					double op1 = arbol[arbol[nodo].getHijoizq()].getValor();
					double op2 = arbol[arbol[nodo].getHijoder()].getValor();
					double op3 = 0;
					if(arbol[nodo].getOperacion().equals("/")) {
						if(Math.abs(op2) < 0.0000000001) {
							break;
						}
						op3 = op1 / op2;
					}
					if(arbol[nodo].getOperacion().equals("+")) {
						op3 = op1 + op2;
					}
					if(arbol[nodo].getOperacion().equals("-")) {
						op3 = op1 - op2;		
					}
					if(arbol[nodo].getOperacion().equals("*")) {
						op3 = op1 * op2;		
					}
					arbol[nodo].setValor(op3);
					arbol[nodo].setResuelto(true);
				}
				else {
					pila.push(arbol[nodo].getHijoizq());
					pila.push(arbol[nodo].getHijoder());
				}
			}
		}
		if(!pila.isEmpty()) arbol[1].setResuelto(false);
		return arbol[1].getValor();
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		arbol = new NodoArbol[n+1];
		for(int i = 1; i<=n; i++) {
			st = new StringTokenizer(bf.readLine());
			arbol[i] = new NodoArbol(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		double resultado = resolver2();
		if(arbol[1].isResuelto()) {
			System.out.printf("%.6f", resultado);
			System.out.println("");
		}
		else {
			System.out.println("ERROR");
		}
	}
	
	static class NodoArbol{
		String operacion;
		double valor;
		boolean resuelto;
		int hijoizq;
		int hijoder;
		
		public NodoArbol(String operacion, int hijoizq, int hijoder) {
			if(operacion.equals("+") || operacion.equals("-") || operacion.equals("*") || operacion.equals("/")){
				this.valor = 0.0;
				this.operacion = operacion;
				this.resuelto = false;
			}
			else {
				this.valor = Double.parseDouble(operacion);
				this.operacion = null;
				this.resuelto = true;
			}
			this.hijoizq = hijoizq;
			this.hijoder = hijoder;
		}

		public String getOperacion() {
			return operacion;
		}

		public void setOperacion(String operacion) {
			this.operacion = operacion;
		}

		public double getValor() {
			return valor;
		}

		public void setValor(double resultado) {
			this.valor = resultado;
		}

		public boolean isResuelto() {
			return resuelto;
		}

		public void setResuelto(boolean resuelto) {
			this.resuelto = resuelto;
		}

		public int getHijoizq() {
			return hijoizq;
		}

		public void setHijoizq(int hijoizq) {
			this.hijoizq = hijoizq;
		}

		public int getHijoder() {
			return hijoder;
		}

		public void setHijoder(int hijoder) {
			this.hijoder = hijoder;
		}
		
		
	}
}
