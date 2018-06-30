package cn.gloryroad;

/**
 * @author Administrator
 *格式化输出日期
 *@return 返回字符型日期
 */
public class DateUtil {
	public static String format(java.util.Date date, String format){
		String result = "";
		
		try {
			if (date != null){
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @author Administrator
	 *返回年份
	 @return 返回年份
	 */
	
	public static int getYear(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}
	
	/**
	 * @author Administrator
	 *返回月份
	 */
	
	public static int getMonth(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}
	
	/**
	 * @author Administrator
	 *返回月份中的第几天
	 */
	
	public static int getDay(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @author Administrator
	 *返回小时
	 */
	
	public static int getHour(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * @author Administrator
	 *返回分钟
	 */
	
	public static int getMinute(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}
	
	/**
	 * @author Administrator
	 *返回秒
	 */
	
	public static int getSecond(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}
}
