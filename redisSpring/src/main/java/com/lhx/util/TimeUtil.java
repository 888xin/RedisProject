package com.lhx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by lhx on 15-9-2 下午2:50
 *
 * @Description
 */
public class TimeUtil {

    /**
     * 一分钟的毫秒数
     */
    private static long ONEMIN_MS = 60 * 1000L;

    /**
     * 获取系统当前时间，加上三位随机数用于区分统一个时间点的内容
     *
     * @return
     */
    public static Long getCurrentTimeRandom() {
        return System.currentTimeMillis() * 1000 + (int) (Math.random() * 1000);
    }

    /**
     * 将Date类型转化为String类型
     *
     * @return yyyy-MM-dd HH:mm:ss 2015-05-17 16:17:29
     */
    public static String getStandardDateTime(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = simpleDateFormat.format(date).toString();

        return nowTime;
    }

    public static Date getFormatDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 转换UTC时间
     *
     * @param time
     * @return
     */
    public static String getUtcTimeForTime(String time) {
        if (time == null)
            return null;
        Date data = getDateByFormatDate(time);

        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss ZZZZ yyyy", Locale.ENGLISH);
        return formatter.format(data);
    }

    /**
     * 转换UTC时间
     *
     * @param time
     * @return
     */
    public static String getUtcTimeForDate(Date time) {
        if (time == null)
            return null;

        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss ZZZZ yyyy", Locale.ENGLISH);
        return formatter.format(time);
    }

    /**
     * 根据UTC时间字符串生成Date对象 
     *
     * @param timeStr
     *            UTC时间字符串，例如 Thu Jan 31 13:47:20 +0800 2013
     * @return 在传入参数有误时，将返回当前Date对象
     */
    public static Date getDateByUtcFormattedDate(String timeStr) {
        if (timeStr == null || timeStr.equalsIgnoreCase(""))
            return new Date();

        try {
            return new SimpleDateFormat("E MMM dd HH:mm:ss ZZZZ yyyy", Locale.ENGLISH).parse(timeStr);
        } catch (ParseException e) {

        }

        return new Date();
    }

    /**
     * 转换UTC时间 from yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String getUtcTimeForDate(String time) {
        if (time == null)
            return null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            return getUtcTimeForDate(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取系统当前格式化时间
     *
     * @return
     */
    public static String getCurrTime() {
        return getStrTime(new Date());
    }

    /**
     * 获取某个日期的格式化时间
     *
     * @return
     */
    public static String getStrTime(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 获取生日的格式化时间
     *
     * @return
     */
    public static String getBirthdayStrTime(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 获取当前日期值
     *
     * @param difference
     *            以天位单位的差值
     * @return yyyy-MM-dd
     */
    public static String getCurrDate(int difference) {
        if (difference == 0)
            return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, difference);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 获取系统当前的unix时间戳
     */
    public static long getCurrentUnixTimeStamp() {
        return new Date().getTime() / 1000;
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurrYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getCurrMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static Integer getCurrDate() {
        return Calendar.getInstance().get(Calendar.DATE);
    }

    /**
     * 获取当前是一年中的第几天
     *
     * @return
     */
    public static int getCurrDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取前几天
     *
     * @param day
     * @return
     */
    public static int getBeforeDay(int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - day);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取当前是一天中的第几个小时
     *
     * @return
     */
    public static int getCurrHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前是小时中的第几分钟
     *
     * @return
     */
    public static int getCurrMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    /**
     * 获得本周星期一的日期
     *
     * @return
     */
    public static String getCurrentMonday() {
        weeks = 0;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 通过Unix时间戳来获取时间对象
     *
     * @param timestamp
     *            unix时间戳
     * @return 时间对象
     */
    public static Date getDateByUnixTimeStamp(long timestamp) {
        return new Date(timestamp * 1000);
    }

    /**
     * 通过Unix时间戳来获取格式化的时间(yyyy-MM-dd HH:mm:ss)
     *
     * @param timestamp
     *            unix时间戳
     * @return 格式化后的时间
     */
    public static String getFormatDateByUnixTimeStamp(long timestamp) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getDateByUnixTimeStamp(timestamp));
    }

    /**
     * 通过格式化的日期来获取日期对象
     *
     * @param timeStr
     *            日期字符串 (yyyy-MM-dd HH:mm:ss)
     * @return 日期对象
     */
    public static Date getDateByFormatDate(String timeStr) {
        if (timeStr == null) {
            return new Date();
        }
        if (timeStr.length() > 19) {
            timeStr = timeStr.substring(0, 19);
        }
        boolean isDate = false;
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr);
        } catch (ParseException e) {
            isDate = true;
        }

        if (isDate) {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return sdf.parse(timeStr);
            } catch (ParseException e) {
                return new Date();
            }
        }
        return new Date();
    }

    /**
     * 通过日期对象获取unix时间戳
     *
     * @param date
     *            日期对象
     * @return unix时间戳
     */
    public static long getUnixTimeStampByDate(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * 通过格式化的日期字符串来获取unix时间戳
     *
     * @param timeStr
     *            格式化的日期字符串
     * @return unix时间戳
     */
    public static long getUnixTimeStampByFormatDate(String timeStr) {
        return getUnixTimeStampByDate(getDateByFormatDate(timeStr));
    }

    /**
     * 获取n天前当天日期(不含时间)的时间戳
     *
     * @param days
     *            天数
     * @return 时间戳
     */
    public static long getTimeStampBeforeByDay(int days) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) - days);
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime().getTime() / 1000;
    }

    /**
     * 获取n天前的时间戳
     *
     * @param day
     *            天数
     * @return 时间戳
     */
    public static long getTimeStampBefore(int day) {
        long dayTime = day * 24l * 3600l;
        return getCurrentUnixTimeStamp() - dayTime;
    }

    /**
     * 获取n天后的时间戳
     *
     * @param day
     *            天数
     * @return 时间戳
     */
    public static long getTimeStampAfter(int day) {
        long dayTime = day * 24l * 3600l;
        return getCurrentUnixTimeStamp() + dayTime;
    }

    /**
     * 获取n天后当天日期(不含时间)的时间戳
     *
     * @param days
     *            天数
     * @return 时间戳
     */
    public static long getTimeStampAfterByDay(int days) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime().getTime() / 1000;
    }

    /**
     * 获得几分钟后的时间
     *
     * @param minutes
     * @return
     */
    public static Date getDateAfterByMinute(Date date, int minutes) {
        if (date == null) {
            date = new Date();
        }
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minutes);
        return now.getTime();
    }

    /**
     * 得到N天前时间
     *
     * @param day
     *            天数
     * @return 时间字符串
     */
    public static String getTimeBefore(int day) {
        String time = "";
        long dayTime = 1 * 24 * 3600 * 1000l;
        time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime() - day * dayTime);
        return time;
    }

    /**
     * 得到N天后时间
     *
     * @param day
     *            天数
     * @return 时间字符串
     */
    public static String getTimeAfter(int day) {
        String time = "";
        long dayTime = 1 * 24 * 3600 * 1000l;
        time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime() + day * dayTime);
        return time;
    }

    /**
     * 判断一个时间是否是过去
     *
     * @param inDate
     *            比较的日期
     * @return true 过去 false 大于或等于今天
     */
    public static boolean afterNow(Date inDate) {
        if (inDate == null)
            return false;
        Calendar calendar = Calendar.getInstance();
        Calendar inCalendar = Calendar.getInstance();
        inCalendar.setTime(inDate);
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH);
        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        int inYear = inCalendar.get(Calendar.YEAR);
        int inMonth = inCalendar.get(Calendar.MONTH);
        int inDay = inCalendar.get(Calendar.DAY_OF_MONTH);
        if (nowYear > inYear)
            return true;
        if (nowYear == inYear && nowMonth > inMonth)
            return true;
        if (nowYear == inYear && nowMonth == inMonth && nowDay > inDay)
            return true;
        return false;
    }

    /**
     * 判断一个时间是否是今天
     *
     * @param inDate
     * @return
     */
    public static boolean inNow(Date inDate) {
        if (inDate == null)
            return false;
        Calendar calendar = Calendar.getInstance();
        Calendar inCalendar = Calendar.getInstance();
        inCalendar.setTime(inDate);
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH);
        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        int inYear = inCalendar.get(Calendar.YEAR);
        int inMonth = inCalendar.get(Calendar.MONTH);
        int inDay = inCalendar.get(Calendar.DAY_OF_MONTH);
        if (nowYear == inYear && nowMonth == inMonth && nowDay == inDay)
            return true;
        return false;
    }

    private static int weeks = 0;

    /**
     * 获得当前日期与本周一相差的天数
     *
     * @return
     */
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * 获得相应周的周日的日期
     *
     * @return
     */
    public static String getSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 判断时间是否在当天
     *
     * @param time
     * @return true 是 false 不在当天
     */
    public static boolean isCurrDay(String time) {
        String currDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).replaceAll("-", "");
        String tempTime = time.substring(0, 10).replaceAll("-", "");
        if (currDay.equals(tempTime))
            return true;
        else
            return false;
    }

    /**
     * 判断时间是否在当天
     *
     * @param time
     * @return true 是 false 不在当天
     */
    public static boolean isCurrDay(long time) {
        Date date = new Date(time * 1000);
        String loginTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return isCurrDay(loginTime);
    }

    /**
     * 计算年龄
     *
     * @param time
     * @return
     * @throws Exception
     */
    public static int getAge(String time) throws Exception {
        if (time == null)
            return 20;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay = format.parse(time);
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            return 20;
            // throw new
            // IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);// 给时间赋值
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                } else {
                    // do nothing
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        } else {
            // monthNow<monthBirth
            // donothing
        }
        return age;
    }

    public static int getAge(Date birthDay) throws Exception {
        if (birthDay == null) {
            return 20;
        }
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            // throw new
            // IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
            return 20;
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                } else {
                    // do nothing
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        } else {
            // monthNow<monthBirth
            // donothing
        }

        return age;
    }

    /**
     * 计算生日！
     *
     * @param time
     * @return
     */
    public static String getBrithday(String time) {
        try {
            Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(time);
            Calendar currCalendar = Calendar.getInstance();
            currCalendar.set(Calendar.YEAR, 1970);
            Calendar birthdayCalendar = Calendar.getInstance();
            birthdayCalendar.setTime(birthday);
            birthdayCalendar.set(Calendar.YEAR, 1970);
            String birthdayFormat = new SimpleDateFormat("MM-dd").format(birthday);
            int currMonth = currCalendar.get(Calendar.MONTH);
            int birthdayMonth = birthdayCalendar.get(Calendar.MONTH);
            int currDay = currCalendar.get(Calendar.DAY_OF_MONTH);
            int birthdayDay = birthdayCalendar.get(Calendar.DAY_OF_MONTH);
            int currDays = currCalendar.get(Calendar.DAY_OF_YEAR);
            int birthdayDays = birthdayCalendar.get(Calendar.DAY_OF_YEAR);
            int difDays = birthdayDays - currDays;
            if (currMonth == birthdayMonth) {
                switch (birthdayDay - currDay) {
                    case -1:
                        return "昨天(" + birthdayFormat + ")";
                    case 0:
                        return "今天(" + birthdayFormat + ")";
                    case 1:
                        return "明天(" + birthdayFormat + ")";
                    case 2:
                        return "后天(" + birthdayFormat + ")";
                    case 3:
                        return "三天后(" + birthdayFormat + ")";
                    default:
                        return null;
                }
            } else if (currMonth > birthdayMonth && difDays == -1) {
                return "昨天(" + birthdayFormat + ")";
            } else if (currMonth < birthdayMonth && difDays > 0 && difDays < 4) {
                switch (birthdayDay - currDay) {
                    case 1:
                        return "明天(" + birthdayFormat + ")";
                    case 2:
                        return "后天(" + birthdayFormat + ")";
                    case 3:
                        return "三天后(" + birthdayFormat + ")";
                    default:
                        return null;
                }
            } else if (currDays > 362 && birthdayDays < 4) {
                switch (birthdayDays + 365 - currDays) {
                    case 1:
                        return "明天(" + birthdayFormat + ")";
                    case 2:
                        return "后天(" + birthdayFormat + ")";
                    case 3:
                        return "三天后(" + birthdayFormat + ")";
                    default:
                        return null;
                }
            } else if (currDays == 1 && birthdayDays == 365) {
                return "昨天(" + birthdayFormat + ")";
            } else {
                return null;
            }
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将一个时间延后n天
     *
     * @param days
     * @return
     * @throws ParseException
     */
    public static Date delayTime(String time, int days) throws ParseException {
        if (time == null)
            return new Date();
        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * 获得今天是一年的第几天
     *
     * @return
     */
    public static int getDaysOfYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取从现在至凌晨剩余多少秒
     *
     * @return
     */
    public static long getLastSeconds(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return (cal.getTimeInMillis() - d.getTime()) / 1000;
    }

    /**
     * 通过时间戳来获取两个时间之间的差值（单位秒）
     *
     * @param currTime
     *            当前时间戳/秒
     * @param createTime
     *            创建时间戳/秒
     * @return 时间差值字符串
     */
    public static String getTimeDifferenceByUnixTimestamp(long currTime, long createTime) {
        int hours = (int) ((currTime - createTime) / 3600);
        int minutes = (int) (((currTime - createTime) - hours * 3600) / 60);
        int second = (int) (((currTime - createTime)));
        if (second == 0)
            second = 1;
        if (hours == 0 && minutes == 0) {
            if (second < 0) {
                second = 0;
            }
            return second + "秒前";
        }
        if (hours >= 24) {
            return getFormatDateByUnixTimeStamp(createTime).substring(0, 10);
        }
        if (hours <= 0) {
            if (minutes < 0) {
                minutes = 0;
            }
            return minutes + "分钟前";
        }
        if (hours < 0) {
            hours = 0;
        }
        return hours + "小时前";
    }

    /**
     * 计算vip到期时间
     *
     * @param target
     * @return
     * @throws ParseException
     */
    public static String getVipExpiredTime(long target) {
        long now = System.currentTimeMillis();
        if (now > target) {
            return "VIP已过期";
        }
        long diff = target - now;
        long mins = diff / ONEMIN_MS;
        if (mins < 60) {
            return String.format("VIP离到期还剩%d分钟", mins);
        }
        long hours = mins / 60L;
        mins = mins % 60L;
        if (hours < 24) {
            if (mins == 0) {
                return String.format("VIP离到期还剩%d小时", hours);
            } else {
                return String.format("VIP离到期还剩%d小时%d分钟", hours, mins);
            }
        }
        long days = hours / 24L;
        hours = hours % 24L;
        // 大于3000天的算作永久vip
        if (days > 3000) {
            return "VIP永久有效";
        }
        if (hours == 0) {
            return String.format("VIP离到期还剩%d天", days);
        } else {
            return String.format("VIP离到期还剩%d天%d小时", days, hours);
        }
    }

    /**
     * 获取两个日期之间相差的天数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getDaysDiffer(Date d1, Date d2) {
        Long days = (Math.abs(d1.getTime() / 1000 - d2.getTime() / 1000)) / (24 * 60 * 60L);
        return days.intValue();
    }

    /**
     * 获取两个毫秒数之间相差的天数
     *
     * @param ms1
     * @param ms2
     * @return
     */
    public static int getDaysDifferInMillis(Long ms1, Long ms2) {
        Long days = Math.abs(ms1 - ms2) / (24 * 60 * ONEMIN_MS);
        return days.intValue();
    }

    /**
     * 获取两个日期之间相差的年份数,d1>d2
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getYearDiffer(Date d1, Date d2) {
        if (d1.before(d2)) {
            return 0;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        c2.add(Calendar.YEAR, 1);
        int year = 0;
        while (c1.after(c2)) {
            year++;
            c2.add(Calendar.YEAR, 1);
        }
        return year;
    }

    public static void main(String[] args) throws Exception {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(getDaysDifferInMillis(now.getTimeInMillis(), sdf.parse("2015-08-11 00:00:01").getTime()));

        String d1 = "2015-8-20 12:00:00";
        String d2 = "2017-8-1 13:00:00";
        System.out.println(getYearDiffer(sdf.parse(d2), sdf.parse(d1)));
    }
}
