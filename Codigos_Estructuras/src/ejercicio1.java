import java.io.*;
import java.util.StringTokenizer;

class estudiante{
	long cred_cursados;
	long cred_vistos;
	long suma_ponderada;
	String username;
	
	estudiante(){
		this.cred_cursados = this.cred_vistos = this.suma_ponderada = 0;
		this.username = null;
	}
	
	estudiante(String name){
		this.cred_cursados = this.cred_vistos = this.suma_ponderada = 0;
		this.username = name;
	}
	
	estudiante(long cursados, long vistos, long suma, String name){
		this.cred_cursados = cursados;
		this.cred_vistos = vistos;
		this.suma_ponderada = suma;
		this.username = name;
	}
	
	int compareTo(estudiante otro) { //funcion para comparar cual estudiante va primero, <0 significa que va primero
		if(this.suma_ponderada * otro.cred_cursados < otro.suma_ponderada * this.cred_cursados)return 1;
		if(this.suma_ponderada * otro.cred_cursados > otro.suma_ponderada * this.cred_cursados)return -1;
		if(this.cred_vistos < otro.cred_vistos)return 1;
		if(this.cred_vistos > otro.cred_vistos)return -1;
		return this.username.compareTo(otro.username);
	}
}

public class ejercicio1 {
	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt (reader.readLine());
		estudiante inscripciones[] = new estudiante[size];
		for(int i = 0 ; i < size ; i++) {
			StringTokenizer st = new StringTokenizer(reader.readLine());
			inscripciones[i] = new estudiante(st.nextElement().toString());
			int m = Integer.parseInt(st.nextElement().toString());
			for(int j = 0 ; j < m ; j++) {
				st = new StringTokenizer(reader.readLine());
				int creditos = Integer.parseInt(st.nextElement().toString());
				inscripciones[i].cred_cursados += creditos;
				String nota = st.nextElement().toString(); 
				if(!nota.equals("CANCELADO")) {
					inscripciones[i].cred_vistos += creditos;
					inscripciones[i].suma_ponderada += (int) (Double.parseDouble(nota) * 10) * creditos;;
				}
			}
		}
		for(int i = 0; i < size; i++) {
			for(int j = i+1; j < size; j++) {
				if(inscripciones[j].compareTo(inscripciones[i]) < 0){
					estudiante aux = inscripciones[i];
					inscripciones[i] = inscripciones[j];
					inscripciones[j] = aux;
				}
			}
		}
		for(int i = 0 ; i < size; i++) {
			System.out.println(inscripciones[i].username);
		}
	}
}
