package com.hostmdy.movie.format;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class HourFormat {
	
	public static String formatToHour(int minutes) {
		String hour = "";
		if(minutes >= 60 && (minutes % 60) == 0) {
			hour = (minutes / 60) + " hr ";
		}else if(minutes > 60) {
			hour = (minutes / 60) + " hr  " + (minutes % 60) + " min";
		} else {
			hour = minutes+" min ";
		}
		return hour;
	}
	
	public static String formatBackCount(LocalDate createdDate, LocalTime createdTime) {
		String format = "";
		if(createdDate.equals(LocalDate.now())) {
			Long minutesL = Duration.between(createdTime, LocalTime.now()).toMinutes();
			int minutes = minutesL.intValue();
			if(minutes == 0) {
				format = "just now";
			}else {
				format = formatToHour(minutes) + " ago";
			}
		}else {
			Long days = ChronoUnit.DAYS.between(createdDate, LocalDate.now());
			if( days >= 365 ) {
				format = (days / 365) + " year ago";
			}else if( (days % 30) == 0) {
				format = (days / 30) + " month ago";
			}else if(days > 30 ) {
				format = (days / 30) + " month " + (days % 30) + " day ago";
			}else {
			format = days + " day ago";
			}
		}
		return format;
	}

}
