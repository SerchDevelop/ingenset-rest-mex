package mx.indra.ingenset.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateGMTBean {

	public static String DateGMTString(Date date) {
		return DateGMTString(date, "yyyy-MM-dd");
	}

	public static String DateGMTString(Date date, String dateFormat) {
		if (date == null)
			return null;

		SimpleDateFormat dateFormatGmt = new SimpleDateFormat(dateFormat);
		dateFormatGmt.setLenient(false);
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		return dateFormatGmt.format(date);
	}
	
}
