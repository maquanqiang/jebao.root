package com.jebao.common.utils.date;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/10/19.
 */

public class DateUtil_UnitTest {
    @Test
    public void example()
    {
        System.out.println(DateUtil.stringToTimestamp("2016-05-05T15:22:09"));
        System.out.println(DateUtil.stringToTimestamp("2016-05-05 15:22:09"));
        System.out.println(DateUtil.stringToTimestamp("2016-05-05"));
        System.out.println(DateUtil.getMinusMonth(1));
        System.out.println(LocalDateTime.ofEpochSecond(DateUtil.getMinusMonth(2), 0, ZoneOffset.ofHours(8)));

        System.out.println(LocalDateTime.ofEpochSecond(DateUtil.getMinusDay(0), 0, ZoneOffset.ofHours(8)));
        System.out.println(LocalDateTime.ofEpochSecond(DateUtil.getMinusDay(1), 0, ZoneOffset.ofHours(8)));

        LocalDate now = LocalDate.now();
        System.out.println(DateUtil.getDayOfWeek(now));

        LocalDate ld = DateUtil.stringToLocalDate("2015-05-29", DateUtil.DatePattern.DATE_ONLY);
        System.out.println(ld);

        System.out.println("default zone Id: " + TimeZone.getDefault().toZoneId());
        System.out.println("timestamp to LocalDate: " + DateUtil.timestampToLocalDate(1462432929));
        System.out.println("timestamp to LocalDateTime: " + DateUtil.timestampToLocalDateTime(1462432929));
    }
}
