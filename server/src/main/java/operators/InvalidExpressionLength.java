package operators;

public class InvalidExpressionLength extends Exception {
	private int invalidLength;
	
	public InvalidExpressionLength(String message , int length) {
		super (message);  // calls the parent constructor.
		this.invalidLength = length;
	}
	
	public int getInvalidLength() {
		return invalidLength;
	}
	
	public void setInvalidLength(int length) {
		this.invalidLength = length;
	}
}
