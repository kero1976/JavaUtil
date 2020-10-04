package kero.javautil.commons.exception;

public class TestException extends AppException {

  public static enum TARGET {
    COPY
  }

  public static enum KIND {
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
    StringBuilder buff = new StringBuilder();
    buff.append("TARGET:").append(target).append(System.lineSeparator());
    buff.append("KIND:").append(kind).append(System.lineSeparator());
    buff.append("PATH:").append(path).append(System.lineSeparator());

    return buff.toString();
  }

  /**
   * コンストラクタ.
   *
   * @param target ターゲット。何が発生したのか
   * @param kind 種別。なぜ発生したのか
   * @param e 内部例外
   */
  public TestException(TARGET target, KIND kind, String dir, Exception e) {
    this.target = target;
    this.kind = kind;
    this.path = dir;
    this.e = e;
  }

}
