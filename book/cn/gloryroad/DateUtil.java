package cn.gloryroad;

/**
 * @author Administrator
 *��ʽ���������
 *@return �����ַ�������
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
	 *�������
	 @return �������
	 */
	
	public static int getYear(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.YEAR);
	}
	
	/**
	 * @author Administrator
	 *�����·�
	 */
	
	public static int getMonth(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MONTH) + 1;
	}
	
	/**
	 * @author Administrator
	 *�����·��еĵڼ���
	 */
	
	public static int getDay(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @author Administrator
	 *����Сʱ
	 */
	
	public static int getHour(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * @author Administrator
	 *���ط���
	 */
	
	public static int getMinute(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.MINUTE);
	}
	
	/**
	 * @author Administrator
	 *������
	 */
	
	public static int getSecond(java.util.Date date){
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.SECOND);
	}
}
