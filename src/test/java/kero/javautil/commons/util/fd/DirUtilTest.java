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
      DirUtil.cleanDirectory("存在しないフォルダ");

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
    File file = new File("./test/test2/b");
    try {
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String text;
        while ((text = br.readLine()) != null) {
          try {
            // 開いているファイルがあるので例外が発生する
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



  @Test
  @Tag("deleteDirectory")
  void deleteDirectoryテスト_正常系_フォルダ以下空() throws TestException {

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
  @Tag("deleteDirectory")
  void deleteDirectoryテスト_正常系_フォルダ以下にファイルとフォルダが存在() throws TestException {

    // 事前準備。testフォルダをコピー。テストフォルダ以下にはファイルとサブフォルダが存在する。
    copy("data2");
    assertThat(new File("./test/test2")).exists();
    assertThat(new File("./test/a")).exists();

    try {
      // 実行
      DirUtil.deleteDirectory("test");

      // 実行後はファイルとフォルダが存在しない
      assertThat(new File("./test/test2")).doesNotExist();
      assertThat(new File("./test/a")).doesNotExist();
      // 指定したフォルダも削除される
      assertThat(new File("./test")).doesNotExist();
    } catch (AppFileIOException e) {
      fail();
    }
  }

  @Test
  @Tag("deleteDirectory")
  void deleteDirectoryテスト_異常系_指定したパスがファイル() throws TestException {

    // 事前準備。testファイルをコピー
    copy("data3");

    try {
      // 実行
      DirUtil.deleteDirectory("test");

      // ファイルを指定しているので例外が発生するはず
      fail();
    } catch (AppFileIOException e) {
      assertThat(e.getUserMessage()).startsWith("TARGET:DIR,KIND:DELETE_ERROR");
    }
  }

  @Test
  @Tag("deleteDirectory")
  void deleteDirectoryテスト_異常系_指定したパスが見つからない() throws TestException {

    // 事前準備。なし。

    try {
      // 実行
      DirUtil.deleteDirectory("存在しないフォルダ");

      // 指定したフォルダが存在しないので成功
    } catch (AppFileIOException e) {
      fail();
    }
  }

  @Test
  @Tag("deleteDirectory")
  void deleteDirectoryテスト_異常系_ファイルロック() throws TestException {

    // 事前準備。testフォルダをコピー。テストフォルダ以下にはファイルとサブフォルダが存在する。
    copy("data2");
    assertThat(new File("./test/test2")).exists();
    assertThat(new File("./test/a")).exists();
    File file = new File("./test/test2/b");
    try {
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String text;
        while ((text = br.readLine()) != null) {
          try {
            // 開いているファイルがあるので例外が発生する
            DirUtil.deleteDirectory("test");
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



  @Test
  @Tag("moveDirectoryToDirectory")
  void moveDirectoryToDirectoryテスト_正常系_既存フォルダにコピー() throws TestException {

    // 事前準備。testフォルダをコピー。テストフォルダ以下は空
    copy("data4");
    assertThat(new File("./foo/A")).exists();
    assertThat(new File("./foo/B")).exists();

    try {
      // 実行
      DirUtil.moveDirectoryToDirectory("foo", "既存フォルダ", false);

      // fooフォルダがなくなり、「foo/A」、「foo/B」は「既存フォルダ/foo/A」、「既存フォルダ/foo/B」に移動’
      assertThat(new File("./foo/A")).doesNotExist();
      assertThat(new File("./foo/B")).doesNotExist();
      assertThat(new File("./既存フォルダ/foo/A")).exists();
      assertThat(new File("./既存フォルダ/foo/B")).exists();
    } catch (AppFileIOException e) {
      fail();
    }
  }

  @Test
  @Tag("moveDirectoryToDirectory")
  void moveDirectoryToDirectoryテスト_異常系_指定したパスがファイル() throws TestException {

    // 事前準備。testファイルをコピー
    copy("data3");

    try {
      // 実行
      DirUtil.moveDirectoryToDirectory("test", "新規フォルダ", true);

      // ファイルを指定しているので例外が発生するはず
      fail();
    } catch (AppFileIOException e) {
      assertThat(e.getUserMessage()).startsWith("TARGET:DIR,KIND:MOVE_ERROR");
    }
  }

  @Test
  @Tag("moveDirectoryToDirectory")
  void moveDirectoryToDirectoryテスト_異常系_指定したパスが見つからない() throws TestException {

    // 事前準備。なし。

    try {
      // 実行
      DirUtil.moveDirectoryToDirectory("存在しないフォルダ", "新規フォルダ", true);
      // 存在しないパスを指定する
      fail();
    } catch (AppFileIOException e) {
      assertThat(e.getUserMessage()).startsWith("TARGET:DIR,KIND:MOVE_ERROR");
    }
  }

  @Test
  @Tag("moveDirectoryToDirectory")
  void moveDirectoryToDirectoryテスト_異常系_ファイルロック() throws TestException {

    // 事前準備。testフォルダをコピー。テストフォルダ以下にはファイルとサブフォルダが存在する。
    copy("data4");

    File file = new File("./foo/A/A1");
    try {
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String text;
        while ((text = br.readLine()) != null) {
          try {
            // 開いているファイルがあるので例外が発生する
            DirUtil.moveDirectoryToDirectory("foo", "新規フォルダ", true);
            fail();
          } catch (AppFileIOException e) {
            assertThat(e.getUserMessage()).startsWith("TARGET:DIR,KIND:MOVE_ERROR");
          }
        }
      }
    } catch (Exception e) {
      fail();
    }
  }


  @Test
  @Tag("moveFilesToDirectory")
  void moveFilesToDirectoryテスト_正常系_既存フォルダにコピー() throws TestException {

    // 事前準備。testフォルダをコピー。テストフォルダ以下は空
    copy("data4");
    assertThat(new File("./foo/A")).exists();
    assertThat(new File("./foo/B")).exists();

    try {
      // 実行
      DirUtil.moveFilesToDirectory("foo", "既存フォルダ", false);

      // fooフォルダがなくなり、「foo/A」、「foo/B」は「既存フォルダ/A」、「既存フォルダ/B」に移動’
      assertThat(new File("./foo/A")).doesNotExist();
      assertThat(new File("./foo/B")).doesNotExist();
      assertThat(new File("./既存フォルダ/A")).exists();
      assertThat(new File("./既存フォルダ/B")).exists();
    } catch (AppFileIOException e) {
      fail();
    }
  }


}
