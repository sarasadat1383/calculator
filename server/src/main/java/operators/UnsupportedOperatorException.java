package operators;
public class UnsupportedOperatorException extends Exception {
	private String InvalidOperator;
	public UnsupportedOperatorException ( String message , String operator ){
		super(message);  // calls the parent constructor.
		this.InvalidOperator = operator;
	}
	public String getInvalidOperator(){
		return InvalidOperator;
	}
	public void setInvalidOperator(String operator){
		this.InvalidOperator = operator;
	}
}