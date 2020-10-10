package kero.javautil.commons.util.fd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SampleTest {

  @Tag("A")
  @Tag("B")
  @Test
  void test1() {
    fail();
  }

  @Tag("A")
  @Test
  void test2() {
    fail();
  }

  @Tag("B")
  @Test
  void test3() {
    fail();
  }

  @Test
  void test4() {
    fail();
  }

}
