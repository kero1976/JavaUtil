package kero.javautil.commons.util.fd;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

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
  void sampleTest() {
	  DirUtil dir = new DirUtil();
	  dir.sample(1);
  }

}
