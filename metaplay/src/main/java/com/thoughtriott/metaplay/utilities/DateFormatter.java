package com.thoughtriott.metaplay.utilities;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DateFormatter {

	// --------------------------Constructors--------------------------	
	public DateFormatter() {
		
	}
	
	
	public Date getDateFromString(String date) {
		String[] dateArray = null;
		Calendar cal = Calendar.getInstance();
		if(date.contains("/")) {
			dateArray = date.split("/");
		} else if (date.contains("-")) {
			 dateArray = date.split("-");
		}
		
		//-1 on the month because months are zero-based.
		assert dateArray != null;
		int month = Integer.parseInt(dateArray[0])-1;
		int day = Integer.parseInt(dateArray[1]);
		int year = Integer.parseInt(dateArray[2]);
	
		//if the user didn't follow the instructions, trying to compensate by reversing
		// the day and month fieldsbut this will not work if the day is <= 11
		if(month>11) {
			//reversing the day and the month in cal.set()...
			cal.set(year, day, month);
		} else {
			//set the cal how it is supposed to be set..
			cal.set(year, month, day);
		}
	
		return cal.getTime();
	}

	public Date getDateFromIntMDY(int month, int day, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		return cal.getTime();
	}

}
