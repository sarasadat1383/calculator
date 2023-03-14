package operators;
public class UnsupportedOperatorException extends Exception {
	private String myInvalidOperator;
	public UnsupportedOperatorException ( String message , String operator ){
		super(message);  // calls the parent constructor.
		this.myInvalidOperator = operator;
	}
	public String getMyInvalidOperator(){
		return myInvalidOperator;
	}
	public void setMyInvalidOperator(String operator){
		this.myInvalidOperator = operator;
	}
}