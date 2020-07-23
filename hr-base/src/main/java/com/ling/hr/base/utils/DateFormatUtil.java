package com.ling.hr.base.utils;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

public class DateFormatUtil extends DateFormatUtils {
	public final static String YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	public final static String HH_MI_SS = "HH:mm:ss";
	public final static String YYYYMMDDHHMISS = "yyyyMMddHHmmss";
	public final static String YYYYMMDD = "yyyyMMdd";
	public final static String YYMM = "yyMM";
	public final static String HHMISS = "HHmmss";
	public final static String YYYY = "yyyy";
	public final static String MM = "MM";
	public final static String DD = "dd";

	public static String format(String pattern) {
		Date currentDate = DateUtil.getCurrentDate();
		return DateFormatUtil.format(currentDate, pattern);
	}
}
