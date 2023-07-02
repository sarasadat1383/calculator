package operators;

public class Multiplication extends Operator{
	public int precedence(){
		return 2;
	}
	public int operate( int number1, int number2){
		return	number1 * number2;
	}
}