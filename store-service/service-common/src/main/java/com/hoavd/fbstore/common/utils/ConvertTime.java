package com.hoavd.fbstore.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ConvertTime {
  //Date format
  private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

  //Date format
  private final static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  public static String longToString(long milliseconds) {
    Date date = new Date(milliseconds);
    return DATE_FORMAT.format(date);
  }

  public static long getTime(String stringDate) {
    try {
      Date date = ConvertTime.DATE_FORMAT.parse(stringDate);
      return date.getTime();
    } catch (ParseException e) {
      return 0;
    }
  }

  public static long startTimeStampByTimezone(String timezone) {
    Calendar time = new GregorianCalendar(TimeZone.getTimeZone(timezone));
    time.set(Calendar.HOUR_OF_DAY, 0);
    time.set(Calendar.MINUTE, 0);
    time.set(Calendar.SECOND, 0);
    time.set(Calendar.MILLISECOND, 0);
    return time.getTimeInMillis();
  }

  public static long endTimeStampInDayByTimeZone(String timeZone, int dueDate) {
    Calendar time = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
    time.add(Calendar.DATE, dueDate);
    time.set(Calendar.HOUR_OF_DAY, 23);
    time.set(Calendar.MINUTE, 59);
    time.set(Calendar.SECOND, 59);
    time.set(Calendar.MILLISECOND, 999);
    return time.getTimeInMillis();
  }
}
