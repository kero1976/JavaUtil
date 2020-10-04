package kero.javautil.commons.util.fd;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import kero.javautil.commons.TestBase;
import kero.javautil.commons.exception.AppFileIOException;
import kero.javautil.commons.exception.TestException;

public class DirUtilTest extends TestBase {

  @Override
  public String setTestDir() {

    return "DirUtil";
  }


  @Test
  @Tag("cleanDirectory")
  void cleanDirectoryテスト_正常系_フォルダ以下空() throws TestException {

    // 事前準備。testフォルダをコピー。テストフォルダ以下は空
    copy("data1");

    try {
      // 実行
      DirUtil.cleanDirectory("test");

      // 削除するものが無くてもエラーにならない
      assertThat(new File("./test")).exists();
    } catch (AppFileIOException e) {
      fail();
    }
  }

  @Test
  @Tag("cleanDirectory")
  void cleanDirectoryテスト_正常系_フォルダ以下にファイルとフォルダが存在() throws TestException {

    // 事前準備。testフォルダをコピー。テストフォルダ以下にはファイルとサブフォルダが存在する。
    copy("data2");
    assertThat(new File("./test/test2")).exists();
    assertThat(new File("./test/a")).exists();

    try {
      // 実行
      DirUtil.cleanDirectory("test");

      // 実行後はファイルとフォルダが存在しない
      assertThat(new File("./test/test2")).doesNotExist();
      assertThat(new File("./test/a")).doesNotExist();
      assertThat(new File("./test")).exists();
    } catch (AppFileIOException e) {
      fail();
    }
  }

  @Test
  @Tag("cleanDirectory")
  void cleanDirectoryテスト_異常系_指定したパスがファイル() throws TestException {

    // 事前準備。testファイルをコピー
    copy("data3");

    try {
      // 実行
      DirUtil.cleanDirectory("test");

      // ファイルを指定しているので例外が発生するはず
      fail();
    } catch (AppFileIOException e) {
      assertThat(e.getUserMessage()).startsWith("TARGET:DIR,KIND:DELETE_ERROR");
    }
  }

  @Test
  @Tag("cleanDirectory")
  void cleanDirectoryテスト_異常系_指定したパスが見つからない() throws TestException {

    // 事前準備。なし。

    try {
      // 実行
      DirUtil.cleanDirectory("test");

      // 存在しないパスを指定する
      fail();
    } catch (AppFileIOException e) {
      assertThat(e.getUserMessage()).startsWith("TARGET:DIR,KIND:DELETE_ERROR");
    }
  }

  @Test
  @Tag("cleanDirectory")
  void cleanDirectoryテスト_異常系_ファイルロック() throws TestException {

    // 事前準備。testフォルダをコピー。テストフォルダ以下にはファイルとサブフォルダが存在する。
    copy("data2");
    assertThat(new File("./test/test2")).exists();
    assertThat(new File("./test/a")).exists();

    // File lockFile = new File("./test/test2/b");
    // try (FileChannel fc = FileChannel.open(lockFile.toPath(), StandardOpenOption.READ,
    // StandardOpenOption.WRITE);
    // FileLock lock = fc.lock()) {
    // try {
    // // 実行
    // DirUtil.cleanDirectory("test");
    //
    // // 例外が発生する
    // fail();
    // } catch (AppFileIOException e) {
    // assertThat(e.getUserMessage()).startsWith("TARGET:DIR,KIND:DELETE_ERROR");
    // }
    // }catch(Exception e) {
    // fail();
    // }

    File file = new File("./test/test2/b");
    try {
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String text;
        while ((text = br.readLine()) != null) {
          try {
            DirUtil.cleanDirectory("test");
            fail();
          } catch (AppFileIOException e) {
            assertThat(e.getUserMessage()).startsWith("TARGET:DIR,KIND:DELETE_ERROR");
          }
        }
      }
    } catch (Exception e) {
      fail();
    }

  }

}
