package operators;

public class Division extends Operator {
	public int precedence() {
		return 2;
	}
	
	public int operate(int firstNumber , int secondNumber) {
		return firstNumber / secondNumber;
	}
}