package kero.javautil.commons.exception;

import java.nio.file.Path;

public class AppFileIOException extends AppException{

	public static enum TARGET{
		FILE,
		DIR,
		FILE_OR_DIR,
		NONE
	}

	public static enum KIND{
		CREATE_ERROR,
		READ_ERROR,
		WRITE_ERROR,
		DELETE_ERROR,
		COPY_ERROR,
		MOVE_ERROR,
	}

	private TARGET target;
	private KIND kind;
	private Path path;
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

	public AppFileIOException(TARGET target, KIND kind, Path path, Exception e) {
		this.target = target;
		this.kind = kind;
		this.path = path;
		this.e = e;
	}
}
