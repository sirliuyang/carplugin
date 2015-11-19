package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTransfer {
	
	public static Date parserToDate(String datestr){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
		Date date = null;
		try {
			java.util.Date utilDate = sdf.parse(datestr);
			date = new Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
}
