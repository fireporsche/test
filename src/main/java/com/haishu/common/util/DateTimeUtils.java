package com.haishu.common.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * DateTimeUtils
 * @author zhb
 * @date 2019/03/06
 */
@Slf4j
public class DateTimeUtils {
	// 常用到的一些格式常量定义
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYYMMDDHH = "yyyyMMddHH";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMM = "yyyyMM";
	public static final String YYYY = "yyyy";
	public static final String HH_MM = "HHmm";
	public static final String HH = "HH";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
	public static final String YYYY_MM_DD2 = "yyyy/MM/dd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS2 = "yyyy/MM/dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS3 = "yyyy_MM_dd_HH_mm_ss";
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DD = "dd";

	// array reprsents offset bewwen Monday and current date, start from Sunday
	private static final int[] OFFSETS = { -6, 0, -1, -2, -3, -4, -5 };

	// day of week string array
	public static final String[] DAYOFWK = { "Sun", "Mon", "Tue", "Wed", "Thu",
			"Fri", "Sat" };

	// 获取时间相关的函数
	/**
	 * 
	 * @Function: DateTimeNewUtils::getCurrentDate
	 * @Description: 该函数获取当前日期
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:15:06 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 
	 * @Function: DateTimeNewUtils::getCurrentTimestamp
	 * @Description: 该函数获取当前日期的timestamp格式数据
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:15:18 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 
	 * @Function: DateTimeNewUtils::getCurrentDate
	 * @Description: 该函数获取当前日期的字符串
	 * @param format
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:15:42 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static String getCurrentDate(String format) {
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat(format);
			return myFormatter.format(DateTimeUtils.getCurrentDate());
		} catch (Exception err) {
			return "";
		}
	}

	// 日期转换相关函数
	/**
	 * 
	 * @Function: DateTimeNewUtils::dateToString
	 * @Description: 该函数将Date转换成String
	 * @param date
	 * @param format
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:12:34 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static String dateToString(Date date, String format) {
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat(format);
			return myFormatter.format(date);
		} catch (Exception err) {
			return "";
		}
	}

	/**
	 * 
	 * @Function: DateTimeNewUtils::stringToDate
	 * @Description: 该函数用于将字符串转换成指定的格式
	 * @param datecontent
	 * @param format
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:16:25 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Date stringToDate(String datecontent, String format) {
		if (format == null || "".equals(format)) {
			format = YYYY_MM_DD;
		}
		try {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
			Date date = bartDateFormat.parse(datecontent);
			return date;
		} catch (Exception pe) {
//			log.debug("Exception occurs during the string converting to Date.", pe);
		}
		return null;
	}

	// 比较日期大小相关函数
	/**
	 * 
	 * @Function: DateTimeUtils::compareDay
	 * @Description: 该函数按天进行比较
	 * @param timeComparing
	 * @param timeCompared
	 * @param format
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:56:01 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static int compareDay(String timeComparing, String timeCompared,
			String format) {
		Date timeComparingDate = stringToDate(timeComparing, format);
		Date timeComparedDate = stringToDate(timeComparing, format);

		return compareDay(timeComparingDate, timeComparedDate);
	}

	public static int compareDay(Date first, Date second) {
		Calendar cf = Calendar.getInstance();
		cf.setTime(first);
		Calendar cs = Calendar.getInstance();
		cs.setTime(second);
		int offset = (cf.get(Calendar.YEAR) - cs.get(Calendar.YEAR)) * 12 * 30
				+ (cf.get(Calendar.MONTH) - cs.get(Calendar.MONTH)) * 30
				+ (cf.get(Calendar.DATE) - cs.get(Calendar.DATE));
		return offset;
	}

	/**
	 * 
	 * @Function: DateTimeUtils::compareMonth
	 * @Description: 该函数按月进行比较
	 * @param
	 * @param
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:56:30 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static int compareMonth(String timeComparing, String timeCompared,
			String format) {
		Date timeComparingDate = stringToDate(timeComparing, format);
		Date timeComparedDate = stringToDate(timeCompared, format);

		return compareMonth(timeComparingDate, timeComparedDate);
	}

	public static int compareMonth(Date first, Date second) {
		Calendar cf = Calendar.getInstance();
		cf.setTime(first);
		Calendar cs = Calendar.getInstance();
		cs.setTime(second);
		int offset = (cf.get(Calendar.YEAR) - cs.get(Calendar.YEAR)) * 12
				+ (cf.get(Calendar.MONTH) - cs.get(Calendar.MONTH));
		return offset;
	}

	/**
	 * 
	 * @Function: DateTimeUtils::compareDate
	 * @Description: 该函数用于比较两个时间前后
	 * @param first
	 * @param second
	 * @return 返回值：1：表示second在first之后,0表示日期相同,-1表示second在first之前
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:57:13 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static int compareDate(Date first, Date second) {
		Calendar cf = Calendar.getInstance();
		cf.setTime(first);
		Calendar cs = Calendar.getInstance();
		cs.setTime(second);
		if (cf.after(cs)) {
			return 1;
		} else {
			return (cf.before(cs)) ? -1 : 0;
		}
	}

	// 计算日期位置相关函数
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 
	 * @Function: DateTimeUtils::getCalendarByDate
	 * @Description: 该函数用于获取calendar
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:59:02 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Calendar getCalendarByDate() {
		Date date = new Date();
		return getCalendarByDate(date);
	}

	private static Calendar getCalendarByDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	public static Calendar getCalendar(int year, int month, int date,
			int hourOfDay, int minute, int second) {
		Calendar calendar = Calendar.getInstance();

		calendar.set(year, month, date, hourOfDay, minute, second);

		return calendar;
	}

	/**
	 * 
	 * @Function: DateTimeUtils::getCalendarDate
	 * @Description: 该函数的功能描述
	 * @param dateString
	 *            :该日期字符串按着年、月、日顺序进行排序，不能乱掉
	 * @param separator
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 11:00:52 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Calendar getCalendarDate(String dateString, String separator) {
		String[] sArr = dateString.split(separator);
		int year = Integer.parseInt(sArr[0]);
		int month = Integer.parseInt(sArr[1]);
		int day = Integer.parseInt(sArr[2]);
		return new GregorianCalendar(year - 1, month - 1, day);
	}

	public static int getCurYear() {
		Calendar calendar = Calendar.getInstance();

		return calendar.get(Calendar.YEAR);
	}

	public static int getCurMonth() {
		Calendar calendar = Calendar.getInstance();

		return calendar.get(Calendar.MONTH);
	}

	public static int getCurDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getCurHour() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getCurMinute() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE);
	}

	public static int getCurSecond() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 
	 * @Function: DateTimeUtils::calculateDays
	 * @Description: 该函数胜于计算两个日期之间相差的天数
	 * @param start
	 * @param end
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 11:03:19 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Long calculateDays(Date start, Date end) {
		SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD);
		Long days = null;
		try {
			Date ns = sf.parse(sf.format(start));
			Date ne = sf.parse(sf.format(end));
			Long m = ne.getTime() - ns.getTime();
			days = (Long) m / (1000 * 60 * 60 * 24) + 1;
		} catch (Exception e) {
//			 log.debug("calculateDays exception", e);
		}
		return days;
	}

	/**
	 * 
	 * @Function: DateTimeUtils::getLastDayOfMonth
	 * @Description: 该函数用于获取指定日期所在的月份最后一天的日期
	 * @param date
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 11:04:40 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(date);
		}
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DATE));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * 
	 * @Function: DateTimeUtils::getFirstDayOfMonth
	 * @Description: 该函数用于获取指定日期所在的月份第一天的日期
	 * @param date
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 11:05:20 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(date);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 
	 * @Function: DateTimeUtils::getThisWeek
	 * @Description: 该函数用于获取当前日期所在的周的起始日期和终止日期
	 * @return:The format is "yyyy/MM/dd-yyyy/MM/dd"
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 11:10:45 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static String getThisWeek() {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD2,
				new DateFormatSymbols(Locale.US));

		int day = now.get(Calendar.DAY_OF_WEEK);
		int offset = OFFSETS[day - Calendar.SUNDAY] - 1;
		now.add(Calendar.DAY_OF_YEAR, offset);
		String temp = null;

		now.add(Calendar.DAY_OF_YEAR, +1);
		temp = formatter.format(now.getTime());
		now.add(Calendar.DAY_OF_YEAR, +6);
		temp += "-" + formatter.format(now.getTime());

		return temp;
	}

	/**
	 * 
	 * @Function: DateTimeUtils::getPreWeeks
	 * @Description: 该函数用于获取当前日期之前几周的每周的起始日期和终止日期
	 * @param weekNum
	 * @return:
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 11:12:51 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static SortedMap<Integer,String> getPreWeeks(int weekNum) {
		SortedMap<Integer,String> m = new TreeMap<Integer,String>();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, -(7 * (weekNum - 1)));
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD2,
				new DateFormatSymbols(Locale.US));

		int day = now.get(Calendar.DAY_OF_WEEK);
		int offset = OFFSETS[day - Calendar.SUNDAY] - 1;
		now.add(Calendar.DAY_OF_YEAR, offset);
		String temp = null;

		for (int i = 0; i < weekNum; i++) {
			now.add(Calendar.DAY_OF_YEAR, +1);
			temp = formatter.format(now.getTime());
			now.add(Calendar.DAY_OF_YEAR, +6);
			temp += "-" + formatter.format(now.getTime());
			m.put(new Integer(i), temp);
		}

		return m;
	}

	/**
	 * 
	 * @Function: DateTimeUtils::getPreWeeks
	 * @Description: 该函数用于获取指定日期之前几周的每周的起始日期和终止日期
	 * @param weekNum
	 * @param baseDate
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 11:13:42 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static SortedMap<Integer,String> getPreWeeks(int weekNum, Date baseDate) {
		SortedMap<Integer,String> m = new TreeMap<Integer,String>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(Calendar.DAY_OF_YEAR, -(7 * (weekNum - 1) + 1));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd",
				new DateFormatSymbols(Locale.US));
		String temp = "";

		for (int i = 0; i < weekNum; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, +1);
			temp = formatter.format(calendar.getTime());
			calendar.add(Calendar.DAY_OF_YEAR, +6);
			temp += " - " + formatter.format(calendar.getTime());
			m.put(new Integer(i), temp);
		}

		return m;
	}

	/**
	 * 
	 * @Function: DateTimeUtils::getPreviousDate
	 * @Description: 该函数用于计算basedate指定的日期向前推days指定的天数
	 * @param baseDate
	 * @param days
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 11:16:10 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Date getPreviousDate(Date baseDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(Calendar.DAY_OF_YEAR, -days);
		return calendar.getTime();
	}
	
	public static Date getPreviousDate(String baseDateStr, int day,
			String partten) {

		Date baseDate = DateTimeUtils.stringToDate(baseDateStr, partten);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(Calendar.DATE, -day);
		Date resultDate = calendar.getTime();

		return resultDate;
	}


	public static String getPreviousMonth(String baseDateStr, int Month) {

		Date baseDate = DateTimeUtils.stringToDate(baseDateStr,
				DateTimeUtils.YYYYMM);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(Calendar.MONTH, -Month);
		Date resultDate = calendar.getTime();

		return DateTimeUtils.dateToString(resultDate, DateTimeUtils.YYYYMM);
	}

	public static String getPreviousMonth(Date baseDate, int Month) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(Calendar.MONTH, -Month);
		Date resultDate = calendar.getTime();

		return DateTimeUtils.dateToString(resultDate, DateTimeUtils.YYYYMM);
	}

	public static void getThisPreviousMonth(Date baseDate, int Month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(Calendar.MONTH, -Month);
		baseDate = calendar.getTime();
	}
	
	public static Date getPreviousRoundDate(String baseDateStr, String partten) {

		Date baseDate = DateTimeUtils.stringToDate(baseDateStr, partten);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		Date resultDate = calendar.getTime();

		return resultDate;
	}

	public static Date getPreviousRoundDate(Date baseDate) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		Date resultDate = calendar.getTime();

		return resultDate;
	}

	public static Date getPreviousRoundMonth(Date baseDate) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		Date resultDate = calendar.getTime();

		return resultDate;
	}

	public static Date getPreviousHour(String baseDateStr, int hour,
			String partten) {

		Date baseDate = DateTimeUtils.stringToDate(baseDateStr, partten);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(Calendar.HOUR, -hour);
		Date resultDate = calendar.getTime();
 
		return resultDate;
	}

	public static Date getPreviousRoundHour(String baseDateStr, String partten) {

		Date baseDate = DateTimeUtils.stringToDate(baseDateStr, partten);

		if (baseDate == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.set(Calendar.MINUTE, 0);
		Date resultDate = calendar.getTime();

		return resultDate;
	}

	/**
	 * Get the date after the base date passed in as first parameter and
	 * interval is specified by second parameter
	 * 
	 * @param baseDate
	 *            baseDate
	 * @param days days
	 * @return Date
	 */
	public static Date getNextDate(Date baseDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}
	
	public static Date getNextMonth(Date baseDate, int Month) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(Calendar.MONTH, Month);
		Date resultDate = calendar.getTime();

		return resultDate;
	}
	
	public static String getNextMonth(String datecontent, String formate, int n) {
		Date date = DateTimeUtils.stringToDate(datecontent, formate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		Date resultDate = cal.getTime();
		return DateTimeUtils.dateToString(resultDate, formate);
	}

	/**
	 * Get start date of weeks specified by parameter weekNum beginning from the
	 * week specified by parameter baseDate
	 * 
	 * @param baseDate
	 *            baseDate start point of calculation
	 * @param weekNum weekNum number of weeks
	 * @return List a list of start date of weeks
	 */
	public static List<Date> getStartDatesOfWeeks(Date baseDate, int weekNum) {
		List<Date> result = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		result.add(new Date(baseDate.getTime()));

		for (int i = 0; i < weekNum - 1; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, 7);
			result.add(calendar.getTime());
		}
		return result;
	}

	/**
	 * Get Monday of the week the day specified by parameter belongs to
	 * 
	 * @param date
	 *            date any date
	 * @return Date Monday of the week
	 */
	public static Date getStartDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		// get day of week
		int day = calendar.get(Calendar.DAY_OF_WEEK);

		// get offset between current date and Monday of the week
		int offset = OFFSETS[day - Calendar.SUNDAY];

		// adjust to Monday
		calendar.add(Calendar.DAY_OF_YEAR, offset);

		return calendar.getTime();
	}

	public static String getDayNameOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// get day of week
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return DAYOFWK[day - Calendar.SUNDAY];
	}
	
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
	}

	/**
	 * Gets the days of month.
	 * 
	 * @param date
	 *            the date
	 * @return the days of month
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DATE);
	}

	/**
	 * Gets the month by offset.
	 * 
	 * @param date
	 *            the date
	 * @param offset
	 *            the offset
	 * @return the month by offset
	 */
	public static Date getMonthByOffset(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		if (null != date) {
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		calendar.add(Calendar.MONTH, offset);
		return calendar.getTime();
	}

	/**
	 * Gets the days of month.
	 * 
	 * @param startMonth
	 *            the start month
	 * @param monthSeq
	 *            the month seq
	 * @return the days of month
	 */
	public static int getDaysOfMonth(Date startMonth, Integer monthSeq) {
		Date month = getMonthByOffset(startMonth, monthSeq.intValue() - 1);
		return getDaysOfMonth(month);
		// return 30;
	}
	
	public static int getDaysOfMonth(String datecontent, String formate) {
		Date date = DateTimeUtils.stringToDate(datecontent, formate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int rtValue = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return rtValue;
	}

	/**
	 * Gets the start time of date.
	 * 
	 * @param date
	 *            the date
	 * @return the start time of date
	 */
	public static Date getStartTimeOfDate(Date date) {
		Calendar calendar = getCalendarByDate(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static Date getStartTimeOfHour(Date date) {
		Calendar calendar = getCalendarByDate(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}
	public static Date getEndTimeOfHour(Date date) {
		Calendar calendar = getCalendarByDate(date);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar.getTime();
	}
	/**
	 * Gets the end time of date.
	 * 
	 * @param date
	 *            the date
	 * @return the end time of date
	 */
	public static Date getEndTimeOfDate(Date date) {
		Calendar calendar = getCalendarByDate(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar.getTime();
	}

	/**
	 * Gets the day.
	 * 
	 * @param date
	 *            the date
	 * @return the day
	 */
	public static int getDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	public static boolean isSunday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	public static boolean isMonday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
	}

	public static boolean isTuesday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY;
	}

	public static boolean isWednesday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY;
	}

	public static boolean isThursday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY;
	}

	public static boolean isFriday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
	}

	public static boolean isSaturday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}

	public static Date truncTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}


	

	// timestamp和Date之间转换相关函数
	/**
	 * 
	 * @Function: DateTimeNewUtils::dateToTimestamp
	 * @Description: 日期转换成unix时间戳
	 * @param date
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:09:30 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Timestamp dateToTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 
	 * @Function: DateTimeNewUtils::timestampToDate
	 * @Description: 该函数将时间戳转换成指定格式的Date对象
	 * @param timestamp
	 * @param format
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:10:26 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Date timestampToDate(Timestamp timestamp, String format) {
		if (timestamp == null || format == null) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(timestampToString(timestamp, format));
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 
	 * @Function: DateTimeNewUtils::timestampToDate
	 * @Description: 该函数将timestamp转换成Date对象
	 * @param timestamp
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:10:59 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static Date timestampToDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	/**
	 * 
	 * @Function: DateTimeNewUtils::timestampToString
	 * @Description: 该函数将timestamp转换成String
	 * @param timestamp
	 * @param format
	 * @return
	 * @version: v1.1.0
	 * @author: 
	 * @date: Oct 6, 2015 10:11:17 PM
	 * 
	 *        Modification History: Date Author Version Description
	 *        ------------------------------------------------------------- Oct
	 *        6, 2015  v1.1.0 修改原因
	 */
	public static String timestampToString(Timestamp timestamp, String format) {
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat(format);
			return myFormatter.format(DateTimeUtils.timestampToDate(timestamp));
		} catch (Exception err) {
			return "";
		}
	}

	public static void main(String[] args) {
		for (int i = 12; i > 0; i--) {
			System.out.println(DateTimeUtils.getPreviousMonth(new Date(), i));
		}
	}
}
