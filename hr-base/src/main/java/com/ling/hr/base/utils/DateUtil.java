package com.ling.hr.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class DateUtil extends DateUtils {

	public final static String YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	public final static String HH_MI_SS = "HH:mm:ss";
	public final static String YYYYMMDDHHMISS = "yyyyMMddHHmmss";
	public final static String YYYYMMDD = "yyyyMMdd";
	public final static String YYMM = "yyMM";
	public final static String HHMISS = "HHmmss";

	/**
	 * 获取当前时间
	 *
	 * @return 当前时间
	 */
	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	public static String getUserDate(String format) {
		return DateFormatUtil.format(getCurrentDate(), format);
	}

	public static String getCurrentTime() {
		return getUserDate(DateFormatUtil.YYYYMMDDHHMISS);
	}

	public static String getDayFirstTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return DateFormatUtil.format(calendar, DateFormatUtil.YYYYMMDDHHMISS);
	}

	public static String getDayLastTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return DateFormatUtil.format(calendar, DateFormatUtil.YYYYMMDDHHMISS);
	}

	public static String getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return DateFormatUtil.format(calendar, DateFormatUtil.YYYYMMDD);
	}

	public static String getDateShort() {
		return getUserDate(DateFormatUtil.YYYYMMDD);
	}

	public static String getTimeShort() {
		return getUserDate(DateFormatUtil.HHMISS);
	}

	public static Date parse(String date, String pattern) {
		if (pattern == null || pattern.equals("")) {
			pattern = DateFormatUtil.YYYYMMDDHHMISS;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date retDate = null;
		try {
			retDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retDate;
	}

	public static Date parse(String date) {
		return parse(date);
	}

	/**
	 * 由出生日期获得年龄
	 *
	 * @param birthday
	 *            出生年月日
	 * @return
	 * @throws Exception
	 */
	public static Integer getAge(String birthday) {
		Integer age = null;
		try {
			Calendar calendar = Calendar.getInstance();
			Date birthDay = DateUtil.parseDate(birthday, new String[] { DateFormatUtil.YYYYMMDD });

			if (calendar.getTime().after(birthDay)) {
				int yearNow = calendar.get(Calendar.YEAR);
				int monthNow = calendar.get(Calendar.MONTH);
				int dayOfMonthNow = calendar.get(Calendar.DAY_OF_MONTH);

				calendar.setTime(birthDay);
				int yearBirth = calendar.get(Calendar.YEAR);
				int monthBirth = calendar.get(Calendar.MONTH);
				int dayOfMonthBirth = calendar.get(Calendar.DAY_OF_MONTH);

				age = yearNow - yearBirth;

				if (monthNow <= monthBirth) {
					if (monthNow == monthBirth) {
						if (dayOfMonthNow < dayOfMonthBirth) {
							age--;
						}
					} else {
						age--;
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return age;
	}
}
