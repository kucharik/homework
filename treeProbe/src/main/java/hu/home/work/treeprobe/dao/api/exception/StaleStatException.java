package hu.home.work.treeprobe.dao.api.exception;

/**
 * Exception for marking staled/invalid state of data...
 * Prepare of availability of checking dataintegrity
 * @author qci
 *
 */
public class StaleStatException extends Exception {
	
	private static final long serialVersionUID = 767048706418785873L;

	private String code;
	
	public StaleStatException() {
		super();
	}
	
	public StaleStatException(String message) {
		super(message);
	}
	
	public StaleStatException(String message, String code) {
		super(message);
		this.code = code;
	}

	public StaleStatException(Throwable cause) {
		super(cause);
	}
	
	public StaleStatException(String message, Throwable cause) {
        super(message, cause);
    }

	public String getCode() {
		return code;
	}

}
