package com.example.webtest.Utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lenovo on 2016/2/22.
 */
public class DateUtils {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    public static String format(Date date) {
        if (date == null) return "";
        long delta = new Date().getTime() - date.getTime();
//        if (delta < 1L * ONE_MINUTE) {
//            long seconds = toSeconds(delta);
//            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
//        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            if (days < 1) {
                return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
            } else {
                String str = dateTimeToString(date, "yyyy-MM-dd");
                return str;
            }
        }
//        if (delta > 30L * ONE_DAY * 3) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String strTime = sdf.format(new Date(delta));
//            return strTime;
//        }
        if (delta < 12L * 4L * ONE_WEEK) {
//            long months = toMonths(delta);
//            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
            String str = dateTimeToString(date, "yyyy-MM-dd");
            return str;
        } else {
//            long years = toYears(delta);
//            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
            String str = dateTimeToString(date, "yyyy-MM-dd");
            return str;
        }
    }

    public static String formatDate(Date date) {
        if (date == null) return "";
        long delta = new Date().getTime() - date.getTime();
        long days = toDays(delta);
        String str = dateTimeToString(date, "yyyy-MM-dd");
        return str;
//        if (delta > 30L * ONE_DAY * 3) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String strTime = sdf.format(new Date(delta));
//            return strTime;
//        }
    }

    public static String getToDay() {
        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        String str = dateTimeToString(date, "yyyy-MM-dd");
        return str;
    }

    public static String formatDate(long date) {
        if (date == 0) return "";

        String str = dateTimeToString(new Date(date), "yyyy-MM-dd HH:mm:ss");
        return str;
//        if (delta > 30L * ONE_DAY * 3) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String strTime = sdf.format(new Date(delta));
//            return strTime;
//        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    public static Date strToDate(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date longTimeToDate(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }
        Date date = new Date(Long.valueOf(time + "000"));
        return date;
    }

    public static String longTimeToString(String time, String outFormat) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df2 = new SimpleDateFormat(outFormat);
        Date date = null;
        try {
            date = df.parse(longTimeToString(time));
        } catch (ParseException e) {
            return time;
        }
        return df2.format(date);
    }

    public static String dateTimeToString(Date time, String outFormat) {
        if (time == null) {
            return null;
        }
        SimpleDateFormat df2 = new SimpleDateFormat(outFormat);
        return df2.format(time);
    }

    public static String longTimeToString(long time, String outFormat) {
        SimpleDateFormat df2 = new SimpleDateFormat(outFormat);
        Date date = new Date(time);
        return df2.format(date);
    }

    /**
     * 获取指定日期是星期几
     * 参数为null时表示获取当前日期是星期几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }

    public static String longTimeToString(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = sdf.format(new Date(Long.valueOf(time)));
        return strTime;
    }


    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getHour() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getDate2() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return formatter.format(date);
    }

    public static int compare_date(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static boolean compare_date(long DATE1, long DATE2) {
        if (DATE1 > DATE2) {
            System.out.println("dt1 在dt2前");
            return false;
        } else {
            return true;
        }
    }

    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static int compare_date2(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    //1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400
    public void printDifference(Date startDate, Date endDate) {

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf("%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);
    }

    public static long[] diff(Date date1, Date date2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long[] time = new long[3];
        try {

            Long diff = date1.getTime() - date2.getTime();   //两时间差，精确到毫秒

            Long day = diff / (1000 * 60 * 60 * 24);          //以天数为单位取整
            Long hour = (diff / (60 * 60 * 1000) - day * 24);             //以小时为单位取整
            Long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);    //以分钟为单位取整
            Long secone = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            Long mHour = day * 24 + hour;
            time[0] = mHour < 0 ? 0 : mHour;
            time[1] = min < 0 ? 0 : min;
            time[2] = secone < 0 ? 0 : secone;
            System.out.println("---diff的值---->" + diff);
            System.out.println("---days的值---->" + day);
            System.out.println("---hour的值---->" + hour);
            System.out.println("---min的值---->" + min);
            System.out.println("---secone的值---->" + secone);



        } catch (Exception e) {

            e.printStackTrace();
        }
        return time;
    }

    public static long toLongSecond(long time) {
        return time;
    }


    public static boolean isInRange(int beginhour,int beginminute,int endhour,int endminute) {

        boolean IS_RANGE = false;
        Calendar cal = Calendar.getInstance();// 当前日期
        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
        int minute = cal.get(Calendar.MINUTE);// 获取分钟
        int minuteOfDay = hour * 60 + minute;// 从0:00分开是到目前为止的分钟数
        final int start = beginhour * 60 + beginminute;// 起始时间 17:20的分钟数
        final int end = endhour * 60 + endminute;// 结束时间 19:00的分钟数
        if (minuteOfDay >= start && minuteOfDay <= end) {
            IS_RANGE = true;
        } else {
            IS_RANGE = false;
//            System.out.println("在外围外");
        }

        return IS_RANGE;
    }
}
