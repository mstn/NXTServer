package nxt.server;


public class Response {

	public final static int OK = 0;
	public final static int NOT_FOUND = 1;
	public final static int BAD_REQUEST = 2;
	public final static int INT_ERROR = 3;
	
	private String content;
	private String message;
	private int status;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	/**
	 * per messaggio di errore
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}



}
