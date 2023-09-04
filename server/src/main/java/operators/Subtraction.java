package operators;

public class Subtraction extends Operator {
	public int precedence() {
		return 1;
	}
	
	public int operate(int firstNumber, int secondNumber) {
		return firstNumber - secondNumber;
	}
}