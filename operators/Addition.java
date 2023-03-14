package operators;

public class Addition extends Operator{
	public int precedence(){
		return 1;
	}
	public int operate( int number1, int number2){
		return number1 + number2;
	}
	
	
	
	
}
