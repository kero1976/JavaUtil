package kero.javautil.commons;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import kero.javautil.commons.exception.TestException;
import kero.javautil.commons.exception.TestException.KIND;
import kero.javautil.commons.exception.TestException.TARGET;

public abstract class TestBase {

  private static final String TEST_FILE_BASE_DIR = "../src/test/resources/テストデータ/";

  @BeforeEach
  void setUp() throws Exception {
    File workDir = new File("./");
    FileUtils.cleanDirectory(workDir);
  }

  protected abstract String setTestDir();

  private String getTestDir() {
    return TEST_FILE_BASE_DIR + setTestDir() + "/";
  }

  protected void copy(String dir) throws TestException {
    try {
      File f = new File(getTestDir() + dir);
      FileUtils.copyDirectory(f, new File("./"));
    } catch (Exception e) {
      throw new TestException(TARGET.COPY, KIND.IO_ERROR, dir, e);
    }

  }
}
