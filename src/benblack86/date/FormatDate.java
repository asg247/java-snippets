package benblack86.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class FormatDate {

	public static void main(String args[]) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
		sdf.setCalendar(Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		
		System.out.println("NOW: "+sdf.format(System.currentTimeMillis()));
	}
}
