package kero.javautil.commons.validate;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import kero.javautil.commons.validate.ValidateCore;


public class ValidateTest {

  @Test
  void 必須検証_単一() {
    // * 例) Listは「AA,AB,ABC」の場合に、必須キーの値とリターンを記載する。
    // * ・「ab」→true。(大文字・小文字は区別しない)
    // * ・「A」→false。（部分一致はNG）
    // * ・「AC」→false。（部分一致はNG）
    List<String> list = new ArrayList<String>(Arrays.asList("AA", "AB", "ABC"));

    assertTrue(ValidateCore.requiredCheck("ab", list));
    assertFalse(ValidateCore.requiredCheck("A", list));
    assertFalse(ValidateCore.requiredCheck("AC", list));
  }

  @Test
  void 必須検証_複数() {
    List<String> list = new ArrayList<String>(Arrays.asList("A", "B", "C", "D"));

    assertTrue(ValidateCore.requiredCheck(new ArrayList<String>(Arrays.asList("A", "B")), list));
    assertTrue(ValidateCore.requiredCheck(new ArrayList<String>(Arrays.asList("c")), list));
    assertFalse(ValidateCore.requiredCheck(new ArrayList<String>(Arrays.asList("A", "E")), list));
  }

  @Test
  void 任意検証() {
    List<String> list = new ArrayList<String>(Arrays.asList("AA", "AB", "ABC"));

    // assertTrue(ValidateCore.requiredCheck("ab", list));
    // assertFalse(ValidateCore.requiredCheck("A", list));
    // assertFalse(ValidateCore.requiredCheck("AC", list));
  }

  // 必須キー：A、B。オプションキー：C、D

}
