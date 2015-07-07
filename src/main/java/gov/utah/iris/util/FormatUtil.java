package gov.utah.iris.util;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

/**
 * Date: Nov 15, 2005
 */
public class FormatUtil {

    /**
     * Formats the specified date (long) to a readable format (i.e. Thu, Oct 27, 2005)
     * @param dateStr
     * @return
     */
    public static String date( String dateStr, String type ) {
        StringBuffer result = new StringBuffer();

        // early return if longStr is not good
        if (dateStr == null || "".equals(dateStr) || type == null || "".equals(type)) {
            return "";
        }

        long longValue = 0;
        Date date = null;

        if ( "long".equals(type) ) {

            // parse longStr to a long
            try {
                longValue = Long.parseLong(dateStr);
                date = new Date(longValue);
            }
            catch ( NumberFormatException e ) {
                // return empty of you cannot parse it
                return result.toString();
            }
        }
        else if ( "date".equals(type) ) {
            try {
                //longValue = new SimpleDateFormat().parse(dateStr).getTime();
                String dateParts[] = dateStr.split("-");
                Calendar calendar = new GregorianCalendar();

                if ( dateParts.length == 3 ) {
                    int year = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    int day = Integer.parseInt(dateParts[2]);
                    calendar.set(year, month, day);

                    date = calendar.getTime();
                }
            }
            catch ( NumberFormatException e) {
                return result.toString();
            }
        }

        // set up date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");

        // formatting result
        if ( date != null )
            result.append(dateFormat.format(date));

        return result.toString();
    }

}
