package kero.javautil.commons.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil {

  public static int compareDateOnly(Date d1, Date d2) {
    ZonedDateTime zdt1 = d1.toInstant().atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.DAYS);
    ZonedDateTime zdt2 = d2.toInstant().atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.DAYS);
    return zdt1.compareTo(zdt2);
  }
}
