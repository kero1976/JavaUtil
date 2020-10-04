package kero.javautil.commons.util.fd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import kero.javautil.commons.exception.AppFileIOException;
import kero.javautil.commons.exception.AppFileIOException.KIND;
import kero.javautil.commons.exception.AppFileIOException.TARGET;

public class DirUtil {


  /**
   * 指定したディレクトリを空にする.
   *
   * @param directory 空にするディレクトリ
   * @throws AppFileIOException 指定したディレクトリが存在しない、削除に失敗した場合
   */
  public static void cleanDirectory(String directory) throws AppFileIOException {
    try {
      FileUtils.cleanDirectory(new File(directory));
    } catch (IOException e) {
      throw new AppFileIOException(TARGET.DIR, KIND.DELETE_ERROR, Paths.get(directory), e);
    } catch (IllegalArgumentException e) {
      throw new AppFileIOException(TARGET.DIR, KIND.DELETE_ERROR, Paths.get(directory), e);
    }
  }

  /**
   * 指定したディレクトリを削除する.
   *
   * @param directory 削除するディレクトリ
   * @throws AppFileIOException あとで書く
   */
  public static void deleteDirectory(String directory) throws AppFileIOException {
    try {
      FileUtils.cleanDirectory(new File(directory));
    } catch (IOException e) {
      throw new AppFileIOException(TARGET.DIR, KIND.DELETE_ERROR, Paths.get(directory), e);
    }
  }

}
