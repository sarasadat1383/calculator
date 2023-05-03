package operators;
public class InvalidExpressionLength extends Exception {
	private int myInvalidLength;
	public InvalidExpressionLength ( String message , int length ){
		super(message);  // calls the parent constructor.
		this.myInvalidLength = length;
	}
	public int getMyInvalidLength(){
		return myInvalidLength;
	}
	public void setMyInvalidLength(int length){
		this.myInvalidLength = length;
	}
}
