package kero.javautil.commons.exception;

public class TestException extends AppException {

	public static enum TARGET{
		COPY
	}

	public static enum KIND{
		IO_ERROR
	}

	private TARGET target;
	private KIND kind;
	private String path;
	private Exception e;
	private String optionMsg;

	public void setOptionMsg(String msg) {
		this.optionMsg = msg;
	}

	@Override
	protected Exception getOriginalException() {
		return e;
	}

	@Override
	protected String getOptionMessage() {
		return optionMsg;
	}

	@Override
	public String getUserMessage() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public TestException(TARGET target, KIND kind, String dir, Exception e) {
		this.target = target;
		this.kind = kind;
		this.path = dir;
		this.e = e;
	}

}
