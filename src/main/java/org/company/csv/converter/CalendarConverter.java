package org.company.csv.converter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.company.util.UtilDate;
import org.jsefa.common.converter.SimpleTypeConverter;

public class CalendarConverter implements SimpleTypeConverter {

  private static final CalendarConverter INSTANCE = new CalendarConverter();

  public static CalendarConverter create() {
    return INSTANCE;
  }

  private CalendarConverter() {
  }

  @Override
  public Object fromString(String s) {
    if (s != null) {
      return UtilDate.stringToCalendar(s," ");
    } else {
      return null;
    }
  }

  @Override
  public String toString(Object d) {
    if (d != null) {
      Calendar cal = (Calendar) d;
      String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
      String date = sdf.format(cal.getTime());
      return date;
    } else {
      return null;
    }
  }

}