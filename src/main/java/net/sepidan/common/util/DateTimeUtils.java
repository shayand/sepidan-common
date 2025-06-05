package net.sepidan.common.util;

import static net.sepidan.common.constant.DateTimeConstants.TEHRAN_ZONE_ID;
import static net.sepidan.common.constant.DateTimeConstants.UTC_ZONE_ID;

import com.github.eloyzone.jalalicalendar.DateConverter;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import com.github.eloyzone.jalalicalendar.JalaliDateFormatter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateTimeUtils {

  public static DateTimeFormatter gregorianLongFormat = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd HH:mm:ss");
  public static DateTimeFormatter gregorianTimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
  public static ZoneId defaultTimeZone = ZoneId.of(TEHRAN_ZONE_ID);


  public static String getCurrentShamsiDate() {

    ZonedDateTime nowDateTime = ZonedDateTime.now().withZoneSameInstant(defaultTimeZone);

    DateConverter dateConverter = new DateConverter();
    JalaliDate jalaliDate = dateConverter.gregorianToJalali(nowDateTime.getYear(),
        nowDateTime.getMonthValue(), nowDateTime.getDayOfMonth());

    return jalaliDate.format(
        new JalaliDateFormatter("yyyy/mm/dd", JalaliDateFormatter.FORMAT_IN_ENGLISH));
  }

  public static String getCurrentShamsiTime() {
    ZonedDateTime nowDateTime = ZonedDateTime.now().withZoneSameInstant(defaultTimeZone);
    return nowDateTime.format(gregorianTimeFormat);
  }

  public static String getShamsiDate(ZonedDateTime zonedDateTime, boolean appendTime) {

    ZonedDateTime argDate = zonedDateTime.withZoneSameInstant(defaultTimeZone);

    DateConverter dateConverter = new DateConverter();
    JalaliDate jalaliDate = dateConverter.gregorianToJalali(argDate.getYear(),
        argDate.getMonthValue(), argDate.getDayOfMonth());

    String jalaliDateStr = jalaliDate.format(
        new JalaliDateFormatter("yyyy/mm/dd", JalaliDateFormatter.FORMAT_IN_ENGLISH));
    if (!appendTime) {
      return jalaliDateStr;
    } else {
      StringBuilder sb = new StringBuilder(jalaliDateStr);
      sb.append(" | ").append(argDate.format(gregorianTimeFormat));
      return sb.toString();
    }
  }

  public static String getShamsiDate(ZonedDateTime zonedDateTime) {
    return DateTimeUtils.getShamsiDate(zonedDateTime, false);
  }

  public static String getShamsiTime(ZonedDateTime zonedDateTime) {
    ZonedDateTime argDate = zonedDateTime.withZoneSameInstant(defaultTimeZone);

    DateConverter dateConverter = new DateConverter();
    JalaliDate jalaliDate = dateConverter.gregorianToJalali(argDate.getYear(),
        argDate.getMonthValue(), argDate.getDayOfMonth());

    String jalaliDateStr = jalaliDate.format(
        new JalaliDateFormatter("yyyy/mm/dd", JalaliDateFormatter.FORMAT_IN_ENGLISH));

    return argDate.format(gregorianTimeFormat);
  }

  public static ZonedDateTime stringToZoneDate(String date) {
    return ZonedDateTime.parse(date + " 00:00:00", gregorianLongFormat);
  }

  public static List<ZonedDateTime> getDecoratedDates(List<String> listArray) {
    List<ZonedDateTime> finalList = new ArrayList<>();
    for (String s : listArray) {
      finalList.add(DateTimeUtils.stringToZoneDate(s));
    }
    return finalList;
  }

  public static String zoneDateToTzDate(ZonedDateTime date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        .withZone(ZoneOffset.UTC);
    return date.format(formatter) + "T" + date.format(gregorianTimeFormat);
  }

  public static ZonedDateTime stringTZtoZoneDate(String date) {
    return ZonedDateTime.parse(date);
  }

  public static ZonedDateTime zonedDateTimeAtStartOfDay(LocalDate localDate) {
    if (localDate == null) {
      return null;
    }

    return localDate.atStartOfDay().atZone(ZoneId.of(UTC_ZONE_ID));
  }

  public static String getFirstDayOfJalaliMonth(ZonedDateTime zonedDateTime) {
    ZonedDateTime argDate = zonedDateTime.withZoneSameInstant(defaultTimeZone);

    DateConverter dateConverter = new DateConverter();
    JalaliDate jalaliDate = dateConverter.gregorianToJalali(argDate.getYear(),
        argDate.getMonthValue(), argDate.getDayOfMonth());

    JalaliDate firstDayOfMonth = new JalaliDate(jalaliDate.getYear(),
        jalaliDate.getMonthPersian().getValue(), 1);

    return firstDayOfMonth.format(
        new JalaliDateFormatter("yyyy/mm/dd", JalaliDateFormatter.FORMAT_IN_ENGLISH));
  }
}
