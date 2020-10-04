package kero.javautil.commons.exception;

import java.nio.file.Path;

/**
 * アプリケーション例外（ファイルIO）.
 *
 */
public class AppFileIOException extends AppException {

  public static enum TARGET {
    FILE, DIR, FILE_OR_DIR, NONE
  }

  public static enum KIND {
    CREATE_ERROR, READ_ERROR, WRITE_ERROR, DELETE_ERROR, COPY_ERROR, MOVE_ERROR,
  }

  private TARGET target;
  private KIND kind;
  private Path path1;
  private Path path2;
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
    buff.append("TARGET:").append(target).append(",");
    buff.append("KIND:").append(kind).append(System.lineSeparator());
    if (path1 != null) {
      buff.append("PATH1:").append(path1).append(System.lineSeparator());
    }
    if (path2 != null) {
      buff.append("PATH2:").append(path2).append(System.lineSeparator());
    }

    return buff.toString();
  }

  /**
   * コンストラクタ.
   *
   * @param target ターゲット。何が発生したのか
   * @param kind 種別。なぜ発生したのか
   * @param path ファイルパス
   * @param e 内部例外
   */
  public AppFileIOException(TARGET target, KIND kind, Path path, Exception e) {
    this.target = target;
    this.kind = kind;
    this.path1 = path;
    this.e = e;
  }

  /**
   * コンストラクタ.
   *
   * @param target ターゲット。何が発生したのか
   * @param kind 種別。なぜ発生したのか
   * @param path ファイルパス
   * @param e 内部例外
   */
  public AppFileIOException(TARGET target, KIND kind, Path path1, Path path2, Exception e) {
    this.target = target;
    this.kind = kind;
    this.path1 = path1;
    this.path2 = path2;
    this.e = e;
  }
}
