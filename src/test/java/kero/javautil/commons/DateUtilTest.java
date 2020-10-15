package kero.javautil.commons;

import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import kero.javautil.commons.util.DateUtil;

public class DateUtilTest {

  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

  @Test
  void 日付チェック() throws ParseException {
    Date d1, d2;
    d1 = sdf.parse("2020/01/01 10:12:34");
    d2 = sdf.parse("2020/01/01 10:12:35");
    assertEquals(DateUtil.compareDateOnly(d1, d2), 0);

    d1 = sdf.parse("2020/01/01 10:12:34");
    d2 = sdf.parse("2020/01/01 10:12:33");
    assertEquals(DateUtil.compareDateOnly(d1, d2), 0);

  }
}
