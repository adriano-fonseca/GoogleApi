package org.company.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class UtilDate {
  
  public static String calendarToString(Calendar cal){
    String DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    String date = sdf.format(cal.getTime());
    return date;
  }
  
  public static Calendar stringToCalendar(String dateTime, String separator){
	  String[] stringParts = dateTime.split(separator);
      String date = stringParts[0];
      String time = stringParts[1];
      String[] dateParts=date.split("-");
      String[] timeParts=time.split(":");
      Calendar cal = null;
      if(separator.equalsIgnoreCase("T")){
    	  //To write in output file does use any time zone
    	  cal = Calendar.getInstance();
      }else{
    	  //To calculate set GMT
    	  cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      }
      cal.set(new Integer(dateParts[0]), (new Integer(dateParts[1])-1), new Integer(dateParts[2]), new Integer(timeParts[0]),new Integer(timeParts[1]),new Integer(timeParts[2]));
      return cal;
  }

}
