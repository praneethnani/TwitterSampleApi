package twitter.exception;

public class RedisException extends RuntimeException  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6965449502640231819L;

	public RedisException(String message) {
		super(message);
	}
}

