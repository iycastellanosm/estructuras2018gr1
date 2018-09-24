
public class quiz4 {
	long misterio(long n) {
		if(n==0 || n==1)return n+1;
		if(n%2==0)return misterio(n/2) * 2;
		if(n%3==1)return misterio(n-4) + n; 
		return misterio(n-3) - 1;
	}
	
	void pila_misteriosa(int n){
		ArrayStack<Integer> pila = new ArrayStack<Integer> (5);
		for(int i = 0; i < n; i++) {
			pila.push(i+3);
		}
		for(int i = 0; i < n; i+=2) {
			pila.pop();
		}
		System.out.println(pila.peek());
	}
	
	public static void main(String args[]) {
		
		
	}
}
