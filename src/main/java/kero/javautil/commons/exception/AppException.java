package kero.javautil.commons.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AppException extends Exception {

  protected abstract Exception getOriginalException();

  protected abstract String getOptionMessage();

  public abstract String getUserMessage();

  public String getDeveloperMessage() {
    StringBuilder buff = new StringBuilder();
    buff.append("ユーザーメッセージ：").append(getUserMessage()).append(System.lineSeparator());
    String optionMessage = getOptionMessage();
    if (optionMessage != null) {
      buff.append("オプションメッセージ：").append(optionMessage).append(System.lineSeparator());
    }
    buff.append("スタックトレース：").append(getStackTraceString());
    return buff.toString();
  }

  /**
   * スタックトレース出力 最初に発生した例外(getOriginalException)があればそのスタックトレースを出力 なければ自身のスタックトレースを出力
   * 
   * @return
   */
  private String getStackTraceString() {
    Exception e = getOriginalException();
    if (e == null) {
      try (StringWriter sw = new StringWriter()) {
        PrintWriter pw = new PrintWriter(sw);
        this.printStackTrace(pw);
        pw.flush();
        return sw.toString();
      } catch (Exception e2) {
        return e2.toString();
      }
    } else {
      try (StringWriter sw = new StringWriter()) {
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        return sw.toString();
      } catch (Exception e2) {
        return e2.toString();
      }
    }
  }
}
