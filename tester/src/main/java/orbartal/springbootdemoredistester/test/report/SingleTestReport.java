package orbartal.springbootdemoredistester.test.report;

//Record?
public class SingleTestReport {

	private String method;
	private TestStatusEnum status;
	private String exception;
	private String message;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public TestStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TestStatusEnum status) {
		this.status = status;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SingleTestReport [method=" + method + ", status=" + status + ", exception=" + exception + ", message=" + message + "]";
	}

}
