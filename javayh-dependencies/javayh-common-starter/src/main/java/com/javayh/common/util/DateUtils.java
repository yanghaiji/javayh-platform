package com.javayh.common.util;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *      时间工具
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-22
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String yyyy = "yyyy";
    public static final String MMyyyy = "MM/yyyy";
    public static final String MMyy = "MM/yy";
    public static final String MMddyyyy = "MM/dd/yyyy";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddhhmmssSSS";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static String[] parsePatterns = new String[]{"MM/dd/yyyy", "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy HH:mm", "MM/yyyy", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
    public static final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};

    public DateUtils() {
    }

    public static Date parseDate(Object str) {
        if (str != null && !"".equals(str)) {
            try {
                return parseDate(str.toString(), parsePatterns);
            } catch (ParseException var2) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return date == null ? "" : (new SimpleDateFormat(format)).format(date);
    }

    public static String format(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        } else {
            return null;
        }
    }

    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        } else {
            DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
            return fmt.parseLocalDateTime(strDate).toDate();
        }
    }

    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));
        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    public static List<Integer> getYearListOfYears(Date date, int before, int behind) {
        if (before >= 0 && behind >= 0) {
            List<Integer> list = new ArrayList();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int currYear = c.get(1);
            int startYear = currYear - before;
            int endYear = currYear + behind;

            for(int i = startYear; i <= endYear; ++i) {
                list.add(i);
            }

            return list;
        } else {
            return null;
        }
    }

    public static List<Integer> getYearListOfYears(Integer year, int before, int behind) {
        if (before >= 0 && behind >= 0) {
            List<Integer> list = new ArrayList();
            Calendar c = Calendar.getInstance();
            c.set(year, 1, 1);
            int currYear = c.get(1);
            int startYear = currYear - before;
            int endYear = currYear + behind;

            for(int i = startYear; i <= endYear; ++i) {
                list.add(i);
            }

            return list;
        } else {
            return null;
        }
    }

    public static List<Integer> getYearListOfYears(Date date) {
        return getYearListOfYears((Date)date, 4, 13);
    }

    public static List<Integer> getYearListOfYears(int year) {
        Calendar c = Calendar.getInstance();
        c.set(year, 1, 1);
        return getYearListOfYears(c.getTime());
    }

    public static List<Integer> getYearListOfYears(int before, int behind) {
        if (before >= 0 && behind >= 0) {
            List<Integer> list = new ArrayList();
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            int currYear = c.get(1);
            int startYear = currYear - before;
            int endYear = currYear + behind;

            for(int i = startYear; i <= endYear; ++i) {
                list.add(i);
            }

            return list;
        } else {
            return null;
        }
    }

    public static List<String> getRangeDate(Date startDate, Date endDate) {
        List<String> list = new ArrayList();
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        int currYear = c.get(1);
        c.setTime(endDate);
        int endYear = c.get(1);

        for(int i = currYear; i <= endYear; ++i) {
            list.add(String.valueOf(i));
        }

        return list;
    }

    public static List<Date> getMonth4year(Date date) {
        List<Date> months = new ArrayList();
        Calendar min = Calendar.getInstance();
        min.setTime(date);
        Calendar max = Calendar.getInstance();
        max.setTime(date);
        min.set(min.get(1), 0, 1);
        max.set(max.get(1), 11, 2);

        while(min.before(max)) {
            months.add(min.getTime());
            min.add(2, 1);
        }

        return months;
    }

    private static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(sdf.parse(minDate));
        min.set(min.get(1), min.get(2), 1);
        max.setTime(sdf.parse(maxDate));
        max.set(max.get(1), max.get(2), 2);
        Calendar curr = min;

        while(curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(2, 1);
        }

        return result;
    }

    public static int getYear(Date date) {
        Calendar curr = Calendar.getInstance();
        if (date != null) {
            curr.setTime(date);
        }

        return curr.get(1);
    }

    public static int getQuarter(Date date) {
        int month = date.getMonth();
        return month / 3 + 1;
    }

    public static int getMonth(Date date) {
        Calendar curr = Calendar.getInstance();
        if (date != null) {
            curr.setTime(date);
        }

        return curr.get(2) + 1;
    }

    public static boolean isAfterNextMonth(Date test) {
        java.time.LocalDate nextFirst = java.time.LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
        java.time.LocalDate toTest = test.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return toTest.compareTo(nextFirst) > 0;
    }

    public static boolean isAfterCurrentYear(Date test) {
        java.time.LocalDate nextFirst = java.time.LocalDate.now().with(TemporalAdjusters.firstDayOfNextYear());
        java.time.LocalDate toTest = test.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return toTest.compareTo(nextFirst) > 0;
    }
}
