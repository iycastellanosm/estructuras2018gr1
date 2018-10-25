import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ejercicio10 {
	static long ciudad[][];
	static Nodo_1D seg_tree[];
	static Nodo_2D seg_tree2d[];
			
	public static Nodo_1D consultar(int nodo, int ini, int fin) {
		if(ini == seg_tree[nodo].getI1() && fin == seg_tree[nodo].getI2())return seg_tree[nodo];
		if(fin <= seg_tree[2 * nodo].getI2())return consultar(2 * nodo, ini, fin);
		if(ini >= seg_tree[2 * nodo + 1].getI1())return consultar(2 * nodo + 1, ini, fin);
		Nodo_1D izq = consultar(2 * nodo, ini, seg_tree[2 * nodo].getI2());
		Nodo_1D der = consultar(2 * nodo + 1, seg_tree[2 * nodo + 1].getI1(), fin);
		Nodo_1D res = new Nodo_1D(ini, fin, izq.getImax(), izq.getJmax(), izq.getSum() + der.getSum());
		if(ciudad[der.getImax()][der.getJmax()] > ciudad[izq.getImax()][izq.getJmax()]) {
			res.setImax(der.getImax());
			res.setJmax(der.getJmax());
		}
		return res;
	}
	
	public static Nodo_1D consultar(int nodo, int i1, int i2, int j1, int j2) {
		if(j1 == seg_tree2d[nodo].getJ1() && j2 == seg_tree2d[nodo].getJ2()) {
			return seg_tree2d[nodo].consultar(1, i1, i2);
		}
		if(j2 <= seg_tree2d[2 * nodo].getJ2())return consultar(2 * nodo, i1, i2, j1, j2);
		if(j1 >= seg_tree2d[2 * nodo + 1].getJ1())return consultar(2 * nodo + 1, i1, i2, j1, j2);
		Nodo_1D izq = consultar(2 * nodo, i1, i2, j1, seg_tree2d[2 * nodo].getJ2());
		Nodo_1D der = consultar(2 * nodo + 1, i1, i2, seg_tree2d[2 * nodo + 1].getJ1(), j2);
		Nodo_1D res = new Nodo_1D(i1, i2, izq.getImax(), izq.getJmax(), izq.getSum() + der.getSum());
		if(ciudad[der.getImax()][der.getJmax()] > ciudad[izq.getImax()][izq.getJmax()] || (ciudad[der.getImax()][der.getJmax()] == ciudad[izq.getImax()][izq.getJmax()] && der.getImax() < izq.getImax())) {
			res.setImax(der.getImax());
			res.setJmax(der.getJmax());
		}
		return res;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder salida = new StringBuilder();
		String comando = reader.readLine();
		if(comando.equals("AVENIDA")) {
			int n = Integer.parseInt(reader.readLine());
			StringTokenizer st = new StringTokenizer(reader.readLine());
			long f1 = Long.parseLong(st.nextToken());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());	
			int k = 1;
			while(k<n)k=2*k;
			seg_tree = new Nodo_1D[2*k];
			ciudad = new long[k+1][2];
			ciudad[1][1] = f1;
			seg_tree[k] = new Nodo_1D(1, 1, 1, 1, ciudad[1][1]);
			for(int i = 2; i <= n; i++) {
				ciudad[i][1] = (a * ciudad[i-1][1] + b) % 1000000;
				seg_tree[k+i-1] = new Nodo_1D(i, i, i, 1, ciudad[i][1]);
			}
			for(int i = n+1; i <= k; i++) {
				ciudad[i][1] = Long.MIN_VALUE;
				seg_tree[k+i-1] = new Nodo_1D(i, i, i, 1, 0);
			}
			for(int i = k-1; i>0; i--){
				seg_tree[i] = new Nodo_1D(seg_tree[2*i].getI1(), seg_tree[2*i + 1].getI2(), seg_tree[2*i].getImax(), 1, seg_tree[2*i].getSum() + seg_tree[2*i + 1].getSum());
				if(ciudad[seg_tree[2*i + 1].getImax()][1] > ciudad[seg_tree[i].getImax()][1]) {
					seg_tree[i].setImax(seg_tree[2*i + 1].getImax());
				}
			}
			String com;
			while(true) {
				com = reader.readLine();
				if(com == null)break;
				st = new StringTokenizer(com);
				if(st.nextToken().equals("MARCHA")) {
					int i1, i2, ini, fin;
					i1 = Integer.parseInt(st.nextToken());
					i2 = Integer.parseInt(st.nextToken());
					if(i1 <= i2) {
						ini = i1;
						fin = i2;
					}
					else {
						ini = i2;
						fin = i1;
					}
					salida.append(consultar(1, ini, fin).toString());
					salida.append(System.getProperty("line.separator"));
				}
				else {
					int i1;
					long x;
					i1 = Integer.parseInt(st.nextToken());
					x = Long.parseLong(st.nextToken());
					ciudad[i1][1] = x;
					int curr = k + i1 - 1;
					seg_tree[curr].setSum(x);
					while(curr>1) {
						curr /= 2;
						seg_tree[curr].setSum(seg_tree[2*curr].getSum() + seg_tree[2*curr + 1].getSum()); 
						seg_tree[curr].setImax(seg_tree[2*curr].getImax());
						if(ciudad[seg_tree[2*curr + 1].getImax()][1] > ciudad[seg_tree[curr].getImax()][1]) {
							seg_tree[curr].setImax(seg_tree[2*curr + 1].getImax());
						}
					}
				}
				
			}
		}
		else {
			StringTokenizer st = new StringTokenizer(reader.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = 1;
			while(k<n) k = 2*k;
			int m = Integer.parseInt(st.nextToken());
			int l = 1;
			while(l<m) l = 2*l;		
			st = new StringTokenizer(reader.readLine());
			long f1 = Long.parseLong(st.nextToken());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			ciudad = new long[k+1][l+1];
			seg_tree2d = new Nodo_2D[2 * l];
			ciudad[1][1] = f1;
			for(int i = 1; i <= n; i++) {
				if(i>1) ciudad[i][1] = (a * ciudad[i-1][m] + b) % 1000000;
				for(int j = 2; j <= m ;j++) {
					ciudad[i][j] = (a * ciudad[i][j-1] + b) % 1000000;
				}
			}
			for(int i = n+1; i <= k; i++) {
				for(int j = 1; j <= m ;j++) {
					ciudad[i][j] = Long.MIN_VALUE;
				}
			}
			for(int i = 1; i <= k; i++) {
				for(int j = m+1; j <= l ;j++) {
					ciudad[i][j] = Long.MIN_VALUE;
				}
			}
			for(int j = 1; j <= l ;j++) {
				seg_tree2d[l + j - 1] = new Nodo_2D(k, j, j);
				for(int i = 1; i <= k; i++) {
					if(ciudad[i][j] == Long.MIN_VALUE)seg_tree2d[l + j - 1].seg_tree[k + i -1] = new Nodo_1D(i, i, i, j, 0);
					else seg_tree2d[l + j - 1].seg_tree[k + i -1] = new Nodo_1D(i, i, i, j, ciudad[i][j]);
				}
				for(int i = k-1; i > 0; i--) {
					seg_tree2d[l + j - 1].seg_tree[i] = new Nodo_1D(seg_tree2d[l + j - 1].seg_tree[2*i].getI1(), seg_tree2d[l + j - 1].seg_tree[2*i + 1].getI2(), seg_tree2d[l + j - 1].seg_tree[2*i].getImax(), j, seg_tree2d[l + j - 1].seg_tree[2*i].getSum() + seg_tree2d[l + j - 1].seg_tree[2*i + 1].getSum());
					if(ciudad[seg_tree2d[l + j - 1].seg_tree[2*i + 1].getImax()][j] > ciudad[seg_tree2d[l + j - 1].seg_tree[i].getImax()][j]) {
						seg_tree2d[l + j - 1].seg_tree[i].setImax(seg_tree2d[l + j - 1].seg_tree[2*i + 1].getImax());
					}
				}
			}
			for(int j = l-1; j > 0 ;j--) {
				seg_tree2d[j] = new Nodo_2D(k, seg_tree2d[2 * j].getJ1(), seg_tree2d[2 * j + 1].getJ2());
				for(int i = 1; i < 2 * k; i++) {
					seg_tree2d[j].seg_tree[i] = new Nodo_1D(seg_tree2d[2*j].seg_tree[i].getI1(), seg_tree2d[2*j].seg_tree[i].getI2(), seg_tree2d[2 * j].seg_tree[i].getImax(), seg_tree2d[2 * j].seg_tree[i].getJmax(), seg_tree2d[2*j].seg_tree[i].getSum() + seg_tree2d[2*j + 1].seg_tree[i].getSum());
					if(ciudad[seg_tree2d[2*j+1].seg_tree[i].getImax()][seg_tree2d[2*j+1].seg_tree[i].getJmax()] > ciudad[seg_tree2d[j].seg_tree[i].getImax()][seg_tree2d[j].seg_tree[i].getJmax()] || (ciudad[seg_tree2d[2*j+1].seg_tree[i].getImax()][seg_tree2d[2*j+1].seg_tree[i].getJmax()] == ciudad[seg_tree2d[j].seg_tree[i].getImax()][seg_tree2d[j].seg_tree[i].getJmax()]) && seg_tree2d[2*j+1].seg_tree[i].getImax() < seg_tree2d[j].seg_tree[i].getImax()) {
						seg_tree2d[j].seg_tree[i].setImax(seg_tree2d[2*j + 1].seg_tree[i].getImax());
						seg_tree2d[j].seg_tree[i].setJmax(seg_tree2d[2*j + 1].seg_tree[i].getJmax());
					}
				}
			}
			String com;
			while(true) {
				com = reader.readLine();
				if(com == null)break;
				st = new StringTokenizer(com);
				if(st.nextToken().equals("MARCHA")) {
					int i1, j1, i2, j2, aux;
					i1 = Integer.parseInt(st.nextToken());
					j1 = Integer.parseInt(st.nextToken());
					i2 = Integer.parseInt(st.nextToken());
					j2 = Integer.parseInt(st.nextToken());
					if(i2 < i1) {
						aux = i1;
						i1 = i2;
						i2 = aux;
					}
					if(j2 < j1) {
						aux = j1;
						j1 = j2;
						j2 = aux;
					}
					salida.append(consultar(1, i1, i2, j1, j2).toString2());
					salida.append(System.getProperty("line.separator"));
				}
				else {
					int i1, j1;
					long x;
					i1 = Integer.parseInt(st.nextToken());
					j1 = Integer.parseInt(st.nextToken());
					x = Long.parseLong(st.nextToken());
					ciudad[i1][j1] = x;
					int currj = l + j1 - 1;
					int curri = k + i1 - 1;
					seg_tree2d[currj].seg_tree[curri].setSum(x);
					while(curri > 1) {
						curri /= 2;
						seg_tree2d[currj].seg_tree[curri].setSum(seg_tree2d[currj].seg_tree[2*curri].getSum() + seg_tree2d[currj].seg_tree[2*curri+1].getSum());
						seg_tree2d[currj].seg_tree[curri].setImax(seg_tree2d[currj].seg_tree[2*curri].getImax());
						if(ciudad[seg_tree2d[currj].seg_tree[2*curri + 1].getImax()][j1] > ciudad[seg_tree2d[currj].seg_tree[curri].getImax()][j1]) {
							seg_tree2d[currj].seg_tree[curri].setImax(seg_tree2d[currj].seg_tree[2*curri + 1].getImax());
						}
					}
					while(currj > 1) {
						curri = k + i1 - 1;
						currj /= 2;
						while(curri >= 1) {
							seg_tree2d[currj].seg_tree[curri].setSum(seg_tree2d[2*currj].seg_tree[curri].getSum() + seg_tree2d[2*currj+1].seg_tree[curri].getSum());
							seg_tree2d[currj].seg_tree[curri].setImax(seg_tree2d[2*currj].seg_tree[curri].getImax());
							seg_tree2d[currj].seg_tree[curri].setJmax(seg_tree2d[2*currj].seg_tree[curri].getJmax());
							if(ciudad[seg_tree2d[2*currj+1].seg_tree[curri].getImax()][seg_tree2d[2*currj+1].seg_tree[curri].getJmax()] > ciudad[seg_tree2d[currj].seg_tree[curri].getImax()][seg_tree2d[currj].seg_tree[curri].getJmax()] || (ciudad[seg_tree2d[2*currj+1].seg_tree[curri].getImax()][seg_tree2d[2*currj+1].seg_tree[curri].getJmax()] == ciudad[seg_tree2d[currj].seg_tree[curri].getImax()][seg_tree2d[currj].seg_tree[curri].getJmax()] && seg_tree2d[2*currj+1].seg_tree[curri].getImax() < seg_tree2d[currj].seg_tree[curri].getImax())) {
								seg_tree2d[currj].seg_tree[curri].setImax(seg_tree2d[2*currj+1].seg_tree[curri].getImax());
								seg_tree2d[currj].seg_tree[curri].setJmax(seg_tree2d[2*currj+1].seg_tree[curri].getJmax());
							}
							curri /= 2;
						}
					}
				}
			}
		}
		System.out.println(salida.toString());
	}
	
	static class Nodo_1D{
		int i1, i2;
		int imax, jmax;
		long sum;
		
		public int getJmax() {
			return jmax;
		}

		public void setJmax(int jmax) {
			this.jmax = jmax;
		}

		public Nodo_1D(int i1, int i2) {
			this.i1 = i1;
			this.i2 = i2;
		}
		
		public Nodo_1D(int i1, int i2, int imax, int jmax, long sum) {
			this.i1 = i1;
			this.i2 = i2;
			this.imax = imax;
			this.jmax = jmax;
			this.sum = sum;
		}
		
		public int getImax() {
			return imax;
		}
		
		public void setImax(int imax) {
			this.imax = imax;
		}
		
		public long getSum() {
			return sum;
		}
		
		public void setSum(long sum) {
			this.sum = sum;
		}

		public int getI1() {
			return i1;
		}

		public void setI1(int i1) {
			this.i1 = i1;
		}

		public int getI2() {
			return i2;
		}

		public void setI2(int i2) {
			this.i2 = i2;
		}
		
		public String toString() {
			return Integer.toString(imax) + " " + Long.toString(sum);
			//return "[" + Integer.toString(i1) + ", " + Integer.toString(i2) + "] = (" + Integer.toString(imax) + ") " + Long.toString(sum); 
		}
		
		public String toString2() {
			return Integer.toString(imax) + " " + Integer.toString(jmax) + " " + Long.toString(sum);
			//return "[" + Integer.toString(i1) + ", " + Integer.toString(i2) + "] = (" + Integer.toString(imax) + ") " + Long.toString(sum); 
		}
	}
	
	static class Nodo_2D{
		Nodo_1D seg_tree[];
		int size;
		int j1, j2;
		
		public Nodo_2D(int size, int j1, int j2) {
			this.size = size;
			this.seg_tree = new Nodo_1D[2 * size];
			this.j1 = j1;
			this.j2 = j2;
		}

		public Nodo_1D consultar(int nodo, int ini, int fin) {
			if(ini == seg_tree[nodo].getI1() && fin == seg_tree[nodo].getI2())return seg_tree[nodo];
			if(fin <= seg_tree[2 * nodo].getI2())return consultar(2 * nodo, ini, fin);
			if(ini >= seg_tree[2 * nodo + 1].getI1())return consultar(2 * nodo + 1, ini, fin);
			Nodo_1D izq = consultar(2 * nodo, ini, seg_tree[2 * nodo].getI2());
			Nodo_1D der = consultar(2 * nodo + 1, seg_tree[2 * nodo + 1].getI1(), fin);
			Nodo_1D res = new Nodo_1D(ini, fin, izq.getImax(), izq.getJmax(), izq.getSum() + der.getSum());
			if(ciudad[der.getImax()][der.getJmax()] > ciudad[izq.getImax()][izq.getJmax()]) {
				res.setImax(der.getImax());
				res.setJmax(der.getJmax());
			}
			return res;
		}

		public Nodo_1D[] getSeg_tree() {
			return seg_tree;
		}

		public void setSeg_tree(Nodo_1D[] seg_tree) {
			this.seg_tree = seg_tree;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getJ1() {
			return j1;
		}

		public void setJ1(int j1) {
			this.j1 = j1;
		}

		public int getJ2() {
			return j2;
		}

		public void setJ2(int j2) {
			this.j2 = j2;
		}
	}
}
