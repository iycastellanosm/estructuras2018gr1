import java.util.*;

public class ParenthesisMatching {
    public static String MatchedPairs(String expr){
        LinkedStack<Integer> s = new LinkedStack<>();
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i<expr.length(); i++){
            if(expr.charAt(i) == '(')
                s.push(i);
            else if (expr.charAt(i) == ')'){
            	try{
            		answer.append("[" + s.pop() + ", " + i + "]\n");
            	}
            	catch (Exception e){
            		return ("No match for right parenthesis at " + i);
            	}
            }
        }
        if(!s.isEmpty())
            return ("No match for left parenthesis at " + s.pop());
        return answer.toString();
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        while(true){
            System.out.println("Type an expression with no spaces");
            String expression = s.nextLine();
            System.out.println(MatchedPairs(expression));
        }
    }
}
