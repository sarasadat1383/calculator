package operators;
public class OperatorFactory{
	public Operator generateOperator(String operator ) throws UnsupportedOperatorException {
		switch(	operator){
			case"-":
			return new Subtraction();
			case"+":
			return new Addition();
			case"*":
			return new Multiplication();
			case"/":
			return new Division();
			default:
			throw(new UnsupportedOperatorException("unsupported operator:" , operator));
		}
	}
}