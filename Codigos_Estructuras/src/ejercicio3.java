import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ejercicio3 {	
	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		long n = Integer.parseInt(st.nextElement().toString());
		long a1 = Integer.parseInt(st.nextElement().toString());
		long b = Integer.parseInt(st.nextElement().toString());
		long c = Integer.parseInt(st.nextElement().toString());
		rivales elmasdebil = new rivales();
		for(int i = 1; i<=n; i++){
			elmasdebil.add_final(i);
		}
		long mov = a1;
		while(n>1){
			elmasdebil.mover(mov);
			elmasdebil.eliminar_mas_debil();
			n--;
			mov = ( b * mov + c ) % 1000000007;
		}
		System.out.println(elmasdebil);
	}
}

class nodo_estudiante{
	int numero_estudiante;
	nodo_estudiante siguiente;
	nodo_estudiante anterior;
	
	nodo_estudiante(){
		this.numero_estudiante = 1;
		this.siguiente = this;
		this.anterior = this;
	}
	
	nodo_estudiante(int numero, nodo_estudiante sig, nodo_estudiante ant){
		this.numero_estudiante = numero;
		this.siguiente = sig;
		this.anterior = ant;
	}

	public int getNumero_estudiante() {
		return numero_estudiante;
	}

	public void setNumero_estudiante(int numero_estudiante) {
		this.numero_estudiante = numero_estudiante;
	}

	public nodo_estudiante getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(nodo_estudiante siguiente) {
		this.siguiente = siguiente;
	}

	public nodo_estudiante getAnterior() {
		return anterior;
	}

	public void setAnterior(nodo_estudiante anterior) {
		this.anterior = anterior;
	}
	
	public String toString() {
		return Integer.toString(this.numero_estudiante);
	}
}

class rivales{
	nodo_estudiante actual;
	int size;
	
	rivales(){
		this.actual = null;
		size = 0;
	}
	
	void add_final(int estudiante){
		if(this.actual == null) {
			this.actual = new nodo_estudiante();
		}
		else {
			nodo_estudiante nuevo = new nodo_estudiante(estudiante, this.actual, this.actual.getAnterior());
			this.actual.getAnterior().setSiguiente(nuevo);
			this.actual.setAnterior(nuevo);
		}
		this.size++;
	}
	
	void eliminar_mas_debil(){
		if(this.actual == this.actual.getSiguiente()){
			this.actual = null;
		}
		else{
			this.actual = this.actual.getSiguiente();
			this.actual.setAnterior(this.actual.getAnterior().getAnterior());
			this.actual.getAnterior().setSiguiente(this.actual);;
		}
		this.size--;
	}
	
	void mover(long faciles) {
		faciles = faciles % this.size;
		if(2 * faciles < this.size) {
			for(int i=0; i<faciles; i++) {
				this.actual = this.actual.getSiguiente();
			}
		}
		else {
			faciles = size - faciles;
			for(int i=0; i<faciles; i++) {
				this.actual = this.actual.getAnterior();
			}
		}
	}
	
	public String toString(){
		return this.actual.toString();
	}
	
}
