package gov.utah.dts.det.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtilities
{
   /** Formats the specified Date using the specified format pattern. For more information on
    ** format patterns, refer to the documentation for the SimpleDateFormat class.
    **
    ** @param dt the Date to be formatted
	 ** @param pattern the format pattern to be used to format the Date
	 ** @return the formatted String representation of the Date
	 ** @see java.text.SimpleDateFormat
    **/
   public static String formatDate(Date dt, String pattern)
   {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(dt);
	}

	/** Validates the specified ints, returning true if they represent a valid date and false it they don't.
	 ** @param year the int representing the year
	 ** @param month the int representing the month
	 ** @param day the int representing the day
	 ** @return true if the ints represent a valid date; otherwise, false
	 **/
   public static boolean isValid(int year, int month, int day)
   {
		boolean isValid = true;

      try
      {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setLenient(false);
			calendar.set(year, (month - 1), day);
			calendar.get(Calendar.DATE); // The exception doesn't get thrown unless you do a get
		}
		catch(IllegalArgumentException ex)
		{
			isValid = false;
		}

		return isValid;
	}

   /** Returns the number of milliseconds between two Dates.
	 ** @param beforeDate the earliest Date
	 ** @param afterDate the latest Date
	 ** @return the number of milliseconds between the two dates
	 ** @throws IllegalArgumentException when either Date is null
    **/
   public static long getDifferenceInMillis(Date beforeDate, Date afterDate)
   {
		if(beforeDate == null || afterDate == null)
		{
			throw new IllegalArgumentException("Null date value");
		}

		return afterDate.getTime() - beforeDate.getTime();
	}

   /** Returns the number of milliseconds between two Dates as a formatted String in the following format:
	 ** <p>
	 ** <i>x hour(s) x minute(s) and x.xxx seconds</i>
	 ** <p>
    ** If hours is zero the String "hour(s)" does not appear. If minutes is zero the String "minute(s)" does not
    ** appear.
    **
    ** @param beforeDate the earliest Date
	 ** @param afterDate the latest Date
	 ** @return the description of the number of milliseconds between the two dates
	 ** @throws IllegalArgumentException when either Date is null
    **/
	public static String getDifferenceAsString(Date beforeDate, Date afterDate)
	{
		long diff = DateUtilities.getDifferenceInMillis(beforeDate, afterDate);

      int hours = (int) (diff / (1000*60*60));
      int remainder = (int) (diff - (1000*60*60*hours));
      int minutes = remainder / (1000*60);
      remainder = remainder - (1000*60*minutes);
		int seconds = remainder / 1000;
      int milliseconds = remainder - (1000*seconds);

      return ((hours > 0) ? (hours + " hour(s) ") : "") + 
         ((minutes > 0) ? (minutes + " minute(s) and ") : "") + 
         seconds + "." + milliseconds + " seconds";
	}

   /** Converts 'month' to the name of the month it represents. For example,
    ** if the value '1' is passed in, the String "January" is returned.
    **
    ** @param month the number of the month to be converted
	 ** @return a String representation of the month
	 ** @throws IllegalArgumentException if month is not between 1 and 12
    **/
	public static String getMonthString(int month)
      throws IllegalArgumentException
   {
      if(month < 1 || month > 12)
      {
			throw new IllegalArgumentException("Month not between 1 and 12");
		}

      String [] months = {"", "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December"};

		return months[month];
	}

	/**
    * Convert a date string with specified pattern to date object. Sample of pattern:
    * yyyy/MM/dd HH:mm:ss
    * MM/dd/yyyy
    * ...
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static Date stringToDate(String strDate, String pattern) throws Exception {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(strDate);
	}

	public static boolean isDateStrEqual(String strDate1, String strDate2, String pattern) throws Exception {

		boolean isStrDate1Empty = (strDate1 == null || strDate1.isEmpty());
		boolean isStrDate2Empty = (strDate2 == null || strDate2.isEmpty());
		boolean isDatesEqual = false;

		if (!isStrDate1Empty && !isStrDate2Empty) {
			Date date1 = stringToDate(strDate1, pattern);
			Date date2 = stringToDate(strDate2, pattern);
			isDatesEqual = date1.getTime() == date2.getTime();
		}
		return (isDatesEqual || (isStrDate1Empty && isStrDate2Empty));
	}
}
