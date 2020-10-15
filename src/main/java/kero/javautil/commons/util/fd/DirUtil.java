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
    } catch (Exception e) {
      throw new AppFileIOException(TARGET.DIR, KIND.DELETE_ERROR, Paths.get(directory), e);
    }
  }

  /**
   * 指定したディレクトリを削除する.
   *
   * 存在しないディレクトリを指定した場合は、例外にならなかったが、commons ioのJavaDocの説明と挙動が異なる。
   * バージョンによって挙動が変わる可能性があるので、引数は事前にチェックして上位側で対応すること。
   *
   * @param directory 削除するディレクトリ
   * @throws AppFileIOException 指定したのがファイルの場合、削除に失敗した場合
   */
  public static void deleteDirectory(String directory) throws AppFileIOException {
    try {
      FileUtils.deleteDirectory(new File(directory));
    } catch (IOException e) {
      throw new AppFileIOException(TARGET.DIR, KIND.DELETE_ERROR, Paths.get(directory), e);
    } catch (Exception e) {
      throw new AppFileIOException(TARGET.DIR, KIND.DELETE_ERROR, Paths.get(directory), e);
    }
  }

  /**
   * ディレクトリを別のディレクトリに移動します.
   *
   * 例) 「DirA」フォルダの下に「Sub」フォルダと「File1」ファイルが存在する場合に、移動するディレクトリを
   * 「DirA」、移動先を「DirB」を指定すると、「DirB/DirA/Sub」と「DirB/DirA/File1」ができます。
   *
   * @param srcDir 移動するディレクトリ
   * @param destDir 移動先のディレクトリ
   * @param createDestDir true:移動先のディレクトリを作成, false:作成しないで例外を返す
   * @throws AppFileIOException 移動元のディレクトリが存在しない
   */
  public static void moveDirectoryToDirectory(String srcDir, String destDir, boolean createDestDir)
      throws AppFileIOException {
    try {
      FileUtils.moveDirectoryToDirectory(new File(srcDir), new File(destDir), createDestDir);
    } catch (IOException e) {
      throw new AppFileIOException(TARGET.DIR, KIND.MOVE_ERROR, Paths.get(srcDir),
          Paths.get(destDir), e);
    } catch (Exception e) {
      throw new AppFileIOException(TARGET.DIR, KIND.MOVE_ERROR, Paths.get(srcDir),
          Paths.get(destDir), e);
    }
  }

  /**
   * ディレクトリ内のファイルorフォルダを別のディレクトリに移動します.
   *
   * 例) 「DirA」フォルダの下に「Sub」フォルダと「File1」ファイルが存在する場合に、移動するディレクトリを
   * 「DirA」、移動先を「DirB」を指定すると、「DirB/Sub」と「DirB/File1」ができます。「DirA」フォルダは残ります。
   *
   * @param srcDir 移動するディレクトリ
   * @param destDir 移動先のディレクトリ
   * @param createDestDir true:移動先のディレクトリを作成, false:作成しないで例外を返す
   * @throws AppFileIOException 移動元のディレクトリが存在しない
   */
  public static void moveFilesToDirectory(String srcDir, String destDir, boolean createDestDir)
      throws AppFileIOException {
    try {
      File[] list = new File(srcDir).listFiles();
      if (list != null) {
        for (int i = 0; i < list.length; i++) {
          FileUtils.moveDirectoryToDirectory(list[i], new File(destDir), createDestDir);
        }
      }
    } catch (IOException e) {
      throw new AppFileIOException(TARGET.DIR, KIND.MOVE_ERROR, Paths.get(srcDir),
          Paths.get(destDir), e);
    } catch (Exception e) {
      throw new AppFileIOException(TARGET.DIR, KIND.MOVE_ERROR, Paths.get(srcDir),
          Paths.get(destDir), e);
    }
  }

  /**
   * 指定したフォルダ直下にあるファイルの数を数える。（サブフォルダはカウント対象外）
   *
   * @param dir
   * @return
   */
  public static int fileCount(String dir) {
    return 0;
  }
}
