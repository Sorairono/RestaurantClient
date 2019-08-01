package response;

public class ResponseModel {

	private int code;
	private String message;
	private boolean success;

	public ResponseModel() {

	}

	public ResponseModel(int code, String message, boolean success) {
		super();
		this.code = code;
		this.message = message;
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
