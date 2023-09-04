package operators;

public class Multiplication extends Operator {
	public int precedence() {
		return 2;
	}
	
	public int operate(int firstNumber , int secondNumber){
		return firstNumber * secondNumber;
	}
}