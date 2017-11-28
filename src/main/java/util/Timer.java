package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Timer {
	
	public enum DayFilter {
		Yesterday, Today, TodayTimeFixed, Tomorrow, TomorrowBefore4, Tomorrow4AM, TomorrowTimeFixed, WeekDay, WeekDayTimeFixed, Future, FutureTimeFixed,
		TodayPlus1, TodayPlus2, TodayPlus3, TodayPlus4, TodayPlus5, TodayPlus6, Forever
	}
	
	public static void sleep(long duration, TimeUnit unit) {
		try {
			Thread.sleep(TimeUnit.MILLISECONDS.convert(duration, unit));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Given a {@link util.Timer.DayFilter}, the method calculates and returns a date in the
	 * following format: 'yyyy-MM-dd HH:mm:ss'
	 * @param dayFilter
	 * @return a String representing the date in format 'yyyy-MM-dd HH:mm:ss' (e.g. 2014-04-05 14:30:00)
	 */
	public static String getDate(DayFilter dayFilter) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		switch (dayFilter) {
		case Yesterday:
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			break;
		case Today:
			calendar.add(Calendar.HOUR_OF_DAY, 4);
			break;
		case TodayTimeFixed:
			calendar.add(Calendar.HOUR_OF_DAY, 4);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			break;
		case Tomorrow:
		default:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case TomorrowBefore4:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 2);
			break;
		case Tomorrow4AM:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 3);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			break;
		case TomorrowTimeFixed:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			break;
		case WeekDay:
			calendar.add(Calendar.DAY_OF_YEAR, 2);
			break;
		case WeekDayTimeFixed:
			calendar.add(Calendar.DAY_OF_YEAR, 2);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			break;
		case Future:
			calendar.add(Calendar.DAY_OF_YEAR, 7);
			break;
		case FutureTimeFixed:
			calendar.add(Calendar.DAY_OF_YEAR, 7);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			break;
		case TodayPlus1:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case TodayPlus2:
			calendar.add(Calendar.DAY_OF_YEAR, 2);
			break;
		case TodayPlus3:
			calendar.add(Calendar.DAY_OF_YEAR, 3);
			break;
		case TodayPlus4:
			calendar.add(Calendar.DAY_OF_YEAR, 4);
			break;
		case TodayPlus5:
			calendar.add(Calendar.DAY_OF_YEAR, 5);
			break;
		case TodayPlus6:
			calendar.add(Calendar.DAY_OF_YEAR, 6);
			break;
		case Forever:
			calendar.add(Calendar.YEAR, 3);
			break;
		}
		
		return dateFormat.format(calendar.getTime());
	}
	
	/**
	 * Given a date in the following format: 'yyyy-MM-dd HH:mm:ss',
	 * the methods returns back a String with the corresponding day
	 * of the week
	 * @param date in the format 'yyyy-MM-dd HH:mm:ss' (e.g. 2014-04-05 14:30:00)
	 * @return a String representing the day of the week (e.g. Wednesday)
	 */
	public static String getDayOfTheWeek(String date) {
		Date dateParsed = null;
		try {
			dateParsed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// return the day of the week
		return new SimpleDateFormat("EEEE").format(dateParsed);
		
	}
	
	/**
	 * Returns the current short day of the week.
	 * @return the short week day of the week: mon, tue, wed, thu, fri, sat, sun
	 */
	public static String getCurrentShortDayOfTheWeek() {
		return new SimpleDateFormat("E").format(new Date()).toLowerCase();
	}
	
	/**
	 * Returns the current hour in 24h format.
	 * @return the current hour in 24h format.
	 */
	public static String getCurrentHour() {
		return new SimpleDateFormat("HH").format(new Date());
	}
	
	/**
	 * Method to validate dates in the front-end.
	 * Given a date in the following format: 'yyyy-MM-dd HH:mm:ss',
	 * the method returns the date in the format 'dd MMM HH:mm' to validate
	 * the front-end.
	 * @param date with format 'yyyy-MM-dd HH:mm:ss' (e.g. 2014-04-05 14:30:00)
	 * @return a date with format 'dd MMM HH:mm' (e.g. 05 Apr 14:30)
	 */
	public static String getFrontEndDate(String date) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateParsed = null;
		
		try {
			dateParsed = dateFormat.parse(date);
			calendar.setTime(dateParsed);
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			dateParsed = calendar.getTime();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat("dd MMM HH:mm").format(dateParsed);
		
	}
	
	/**
	 * Method to validate dates in the front-end.
	 * Given a date in the following formats: 'yyyy-MM-dd HH:mm:ss', 'dd MMM HH:mm',
	 * '"yyyy-mm-dd'T'HH:mm:ssXXX"' the method returns a date object.
	 * @param date with format 'yyyy-MM-dd HH:mm:ss' (e.g. 2014-04-05 14:30:00)
	 * @return a date
	 */
	public static Date getDate(String date) {
		Date dateParsed = null;
		try {
			dateParsed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		} catch (ParseException e) {
			try {
				dateParsed = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ssXXX").parse(date);
			} catch (ParseException e1) {
				try {
					dateParsed = new SimpleDateFormat("dd MMM HH:mm").parse(date);
				} catch (ParseException e2) {
					e1.printStackTrace();
				}
			}
		}
		return dateParsed;

	}
	
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
	
	public static String getDayOfTheWeek(DayFilter dayFilter) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
		
		switch (dayFilter) {
		case Yesterday:
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			break;
		case Today:
			calendar.add(Calendar.HOUR_OF_DAY, 4);
			break;
		case TodayTimeFixed:
			calendar.add(Calendar.HOUR_OF_DAY, 4);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			break;
		case Tomorrow:
		default:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case TomorrowBefore4:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 2);
			break;
		case Tomorrow4AM:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 4);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			break;
		case TomorrowTimeFixed:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			break;
		case WeekDay:
			calendar.add(Calendar.DAY_OF_YEAR, 2);
			break;
		case WeekDayTimeFixed:
			calendar.add(Calendar.DAY_OF_YEAR, 2);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			break;
		case Future:
			calendar.add(Calendar.DAY_OF_YEAR, 7);
			break;
		case FutureTimeFixed:
			calendar.add(Calendar.DAY_OF_YEAR, 7);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			break;
		case TodayPlus1:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case TodayPlus2:
			calendar.add(Calendar.DAY_OF_YEAR, 2);
			break;
		case TodayPlus3:
			calendar.add(Calendar.DAY_OF_YEAR, 3);
			break;
		case TodayPlus4:
			calendar.add(Calendar.DAY_OF_YEAR, 4);
			break;
		case TodayPlus5:
			calendar.add(Calendar.DAY_OF_YEAR, 5);
			break;
		case TodayPlus6:
			calendar.add(Calendar.DAY_OF_YEAR, 6);
			break;
		}
		
		return dateFormat.format(calendar.getTime());
		
	}

}
