package com.thoughtriott.metaplay.utilities;

import java.util.Calendar;
import java.util.Date;

public class DateFormatter {

	
	public DateFormatter() {
		
	}
	
	
public Date getDateFromString(String date) {
	String[] dateArray = null;
	if(date.contains("/")) {
		dateArray = date.split("/");
	} 
	if (date.contains("-")) {
		 dateArray = date.split("-");
	}
	
	Calendar cal = Calendar.getInstance();

	int month = Integer.parseInt(dateArray[0]);
	int day = Integer.parseInt(dateArray[1]);
	int year = Integer.parseInt(dateArray[2]);

	cal.set(year, month, day);

	Date actualDate = cal.getTime();
	return actualDate;
}

public Date getDateFromIntMDY(int month, int day, int year) {
	Calendar cal = Calendar.getInstance();

	cal.set(year, month, day);
	
	Date actualDate = cal.getTime();
	return actualDate;
}

	
	
	
	
}
