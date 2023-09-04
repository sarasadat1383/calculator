package operators;

public abstract class Operator {
	public abstract int precedence();
	
    public abstract int operate(int firstNumber, int secondNumber);	
}