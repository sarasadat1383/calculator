package operators;
public class InvalidExpressionLength extends Exception {
	private int InvalidLength;
	public InvalidExpressionLength ( String message , int length ){
		super(message);  // calls the parent constructor.
		this.InvalidLength = length;
	}
	public int getInvalidLength(){
		return InvalidLength;
	}
	public void setInvalidLength(int length){
		this.InvalidLength = length;
	}
}
