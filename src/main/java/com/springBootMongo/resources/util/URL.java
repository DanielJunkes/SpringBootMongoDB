package com.springBootMongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class URL {
	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static LocalDate convertDate(String date, LocalDate defaultValue){
		try {
			DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return LocalDate.parse(date, sdf);
		}catch(DateTimeParseException e) {
			return defaultValue;
		}
	}
}
