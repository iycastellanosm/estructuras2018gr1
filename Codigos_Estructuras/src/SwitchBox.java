import java.util.*;

public class SwitchBox {
    public static boolean checkBox(int [] net){
        ArrayStack<Integer> s = new ArrayStack<>();
        for(int i=0; i<net.length; i++)
            if(!s.isEmpty())
                if(net[i] == net[s.peek()])
                    s.pop();
                else
                    s.push(i);
            else
                s.push(i);
        if(s.isEmpty()) {
            System.out.println("Switch box is routable");
            return true;
        }
        System.out.println("Switch box is not routable");
        return false;
    }

    public static void main(String[] args){
    	Scanner s = new Scanner(System.in);
        while(true){
	        System.out.println("Type number of pins in switch box");
	        int n = s.nextInt();
	        int [] net = new int[n];
	        System.out.println("Type net numbers for pins 1 through " + n);
	        for(int i=0;i<n;i++)
	            net[i] = s.nextInt();
	        checkBox(net);
    	}
    }
}
