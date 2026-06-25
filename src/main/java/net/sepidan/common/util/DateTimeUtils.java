package net.sepidan.common.util;

import static net.sepidan.common.constant.DateTimeConstants.TEHRAN_ZONE_ID;
import static net.sepidan.common.constant.DateTimeConstants.UTC_ZONE_ID;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import net.sepidan.persiandate.converter.PersianDateConverter;
import net.sepidan.persiandate.converter.PersianDateConverter.PersianDateInfo;
import net.sepidan.persiandate.format.PersianDateFormatter;
import net.sepidan.persiandate.util.PersianDateUtils;

public class DateTimeUtils {

    public static DateTimeFormatter gregorianLongFormat = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter gregorianTimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static ZoneId defaultTimeZone = ZoneId.of(TEHRAN_ZONE_ID);


    public static String getCurrentShamsiDate() {
        return PersianDateUtils.today();
    }

    public static String getCurrentShamsiTime() {
        ZonedDateTime nowDateTime = ZonedDateTime.now().withZoneSameInstant(defaultTimeZone);
        return nowDateTime.format(gregorianTimeFormat);
    }

    public static String getShamsiDate(ZonedDateTime zonedDateTime, boolean appendTime) {

        ZonedDateTime argDate = zonedDateTime.withZoneSameInstant(defaultTimeZone);

        return appendTime ? PersianDateConverter.toPersian(argDate, "yyyy/MM/dd | HH:mm:ss")
            : PersianDateConverter.toPersian(argDate, "yyyy/MM/dd");
    }

    public static String getShamsiDate(ZonedDateTime zonedDateTime) {
        return DateTimeUtils.getShamsiDate(zonedDateTime, false);
    }

    public static String getShamsiTime(ZonedDateTime zonedDateTime) {
        ZonedDateTime argDate = zonedDateTime.withZoneSameInstant(defaultTimeZone);

        return PersianDateConverter.toPersian(argDate);
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

        PersianDateInfo persianDateInfo = PersianDateConverter.getPersianDateInfo(
            LocalDate.from(argDate));

        LocalDate firstDayOfMonth = PersianDateConverter.toGregorian(persianDateInfo.getYear(),
            persianDateInfo.getMonth(), 1);

        return PersianDateFormatter.formatStandard(firstDayOfMonth);
    }
}
