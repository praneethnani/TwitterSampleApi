package twitter.exception;

public class DataNotFoundException extends RuntimeException  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8591434847957385326L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
