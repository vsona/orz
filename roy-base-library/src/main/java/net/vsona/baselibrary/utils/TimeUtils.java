package net.vsona.baselibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static String format(long time, String format) {
        return new SimpleDateFormat(format).format(time);
    }

    public static int time2Day(long time) {
        return (int) (time / 1000 / 60 / 60 / 24);
    }

    public static String convertData2DataString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String convertMillisecond2DataString(long time) {
        return convertData2DataString(new Date(time));
    }


    private static int convertMillisecond2CalendarWeekInt(long time) {
        Date date = new Date(time);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week = c.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    /**
     public static String ConvertMillisecond2Week(long time) {
     int weekInt = convertMillisecond2CalendarWeekInt(time);
     return convertCalendarWeekInt2WeekString(weekInt);
     }

     private static String convertCalendarWeekInt2WeekString(int week) {
     switch (week) {
     case Calendar.MONDAY:
     return BaseStringUtils.MONDAY;
     case Calendar.TUESDAY:
     return BaseStringUtils.TUESDAY;
     case Calendar.WEDNESDAY:
     return BaseStringUtils.WEDNESDAY;
     case Calendar.THURSDAY:
     return BaseStringUtils.THURSDAY;
     case Calendar.FRIDAY:
     return BaseStringUtils.FRIDAY;
     case Calendar.SATURDAY:
     return BaseStringUtils.SATURDAY;
     case Calendar.SUNDAY:
     return BaseStringUtils.SUNDAY;
     }
     return BaseStringUtils.UNKNOW;
     }

     public static final String UNKNOW = "UNKNOW";

     public static final String MONDAY = "星期一";
     public static final String TUESDAY = "星期二";
     public static final String WEDNESDAY = "星期三";
     public static final String THURSDAY = "星期四";
     public static final String FRIDAY = "星期五";
     public static final String SATURDAY = "星期六";
     public static final String SUNDAY = "星期天";
     */
}
