/**
 * @author Charles Brown III
 * converts infix expressions to postfix notations
 * (example: "2 + 3 * 4" --> "2 3 4 * +")
 */
import java.util.Stack;

public class Postfix {
	public static void toPostfix(String infix) {
		String result = "";
		Stack<Character> operators = new Stack<>();
		
		for (int i = 0; i < infix.length(); i++) {
			char c = infix.charAt(i);
			
			if (isOperand(c)) {
				result += c;
			} else if (c == '(') {
				operators.push(c);
			} else if (c == ')') {
				while (!operators.isEmpty() && operators.peek() != '(') {
					result += operators.pop();
				}
				operators.pop(); // pop '('
			} else {
				while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)
						&& associativity(c) == 'L') {
					result += operators.pop();
				}
				operators.push(c);
			}
		}
		
		while (!operators.isEmpty()) {
			result += operators.pop();	
		}
		
		
		System.out.println(result);
	}
	
	public static boolean isOperand(char c) {
		return Character.isLetterOrDigit(c);
	}
	
	// method returns precedence of operators (exp having highest precedence)
	public static int precedence(char operator) {
		switch (operator) {
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		case '^': 
			return 3;
		default:
			return -1;
		}
	}
	
	// method returns associativity of operators
	public static char associativity(char c) {
		if (c == '^') {
			return 'R';
		}
		return 'L'; // default: left-associative
	}
	
	public static void main(String[] args) {
		toPostfix("2+3*4");
		toPostfix("a+b*(c^d-e)^(f+g*h)-i");
	}
}
