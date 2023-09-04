package operators;

public class UnsupportedOperatorException extends Exception {
	private String invalidOperator;
	
	public UnsupportedOperatorException(String message , String operator) {
		super (message);  // calls the parent constructor.
		this.invalidOperator = operator;
	}
	
	public String getInvalidOperator() {
		return invalidOperator;
	}
	
	public void setInvalidOperator(String operator) {
		this.invalidOperator = operator;
	}
}