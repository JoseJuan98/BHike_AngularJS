package es.unex.pi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeHelper {
	
	public static String time2Date(long time){
    	Date date01 = new Date();
		date01.setTime(time);
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
		String dateFormatted = formatter.format(date01);
		return dateFormatted;
    }
	
	public static String date2Time(String date) {
	   /* SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    Date date1 = null;
	    try {
	        date1 = sdf.parse(date);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    String formattedTime = sdf.format(date1);*/
	    	    
	    char str [] = new char[5];
	    date.getChars(11, 16, str, 0);;
		
	    return new String(str);
	}
	
	public static String dateTime2Date(String date) {
	    char str [] = new char[10];
	    date.getChars(0, 10, str, 0);;
		
	    return new String(str);
	}
}
