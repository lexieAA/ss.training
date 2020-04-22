package com.ss.training.sort.time;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQuery;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.lang.Boolean;

/**Date-Time API
 * 1) You can use the LocalDateTime class
 * 2) You can use the the previous method of a TemporalAdjuster
 * 3) Both are Simplified date-time API but Zoned deals with various timezones
 * ZoneOffset only tracks the absolute offset from Greenwich/UTC
 * 4) Use the ZonedDateTime.ofInstant method.
 */
public class Time implements TemporalQuery<Boolean>{
	
	public void monthLenthInYear(int year) {
		year = Integer.parseInt(String.valueOf(year));
        for (Month month : Month.values()) {
            YearMonth yearMonth = YearMonth.of(year, month);
            System.out.println("For " + month + " the lenth is " + yearMonth.lengthOfMonth());
        }
	}

	public void mondaysInMonth(String myMouth) {
		 Month month = null;
	     month = Month.valueOf(myMouth.toUpperCase());
	    
	     LocalDate date = Year.now().atMonth(month).atDay(1).
	              with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
	        Month mi = date.getMonth();
	        while (mi == month) {
	            System.out.println(date);
	            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
	            mi = date.getMonth();
	        }
	}
		@Override
		public Boolean queryFrom(TemporalAccessor date) {
			return ((date.get(ChronoField.DAY_OF_MONTH) == 13) &&
	                (date.get(ChronoField.DAY_OF_WEEK) == 5));
		}

}
