package gov.utah.dts.det.util;

import java.text.DecimalFormat;

/** Contains utility methods for working with Strings. Among it's capabilities are the
 ** following: converts Strings to decimals with a specified precision, trims leading zeros from
 ** Strings, unpacks Strings representing numbers (packed numbers are used in COBOL), converts
 ** newline characters to the proper character(s) for the current operating system, strips
 ** formatting characters from Strings representing phone numbers, formats Strings as currency,
 ** phone numbers, or decimal numbers.
 ** <p>
 ** <b>NOTE:</b> The formatting methods of this class are not locale specific. The Strings returned
 ** by the formatting methods are appropriate for the 'eng_us' locale only.
 **
 ** @author Jerod Wilkerson - The Object Center
 ** @author Hung Nguyen - ITS
 **/
public class StringUtilities {
    /** The format pattern used to format Strings as currency Strings.
     **/
    private static final String currencyPattern = "$#,##0.00;(#)";
    
    /** The format pattern used to format Strings as decimal Strings.
     **/
    private static final String decimalPattern = "#,###.#";
    
    /** Trims leading zeros and trailing spaces from the specified String.
     **
     ** @param str the String to be trimmed.
     ** @param minimumSize specifies the minimum number of characters that will be in the String
     ** returned, as long as the 'str' parameter has at least that many characters.
     ** @return a String with no leading zeros or trailing spaces.
     ** @throws NullPointerException if str is null
     **/
    public static String zeroTrim(String str, int minimumSize) {
        str = str.trim();
        
        while(str.length() > minimumSize && str.startsWith("0")) {
            str = str.substring(1, str.length());
        }
        
        return str;
    }
    
    /** Performs the same functions as convertToDecimalString(...) except the value is returned
     ** as a double.
     ** @param str the String to be converted to a decimal number
     ** @param numbDecimals the number of decimals assumed to exist in the
     ** String.
     ** @param shouldUnpack specifies whether the value should be unpacked by
     ** calling the unpack(...) method.
     ** @see #unpack(String, int)
     ** @see #convertToDecimal(String, int, boolean)
     ** @return the decimal value created from the String
     ** @throws NumberFormatException when the String cannot be converted to
     ** a decimal number
     **/
    public static double convertToDecimal(String str, int numbDecimals,
    boolean shouldUnpack)
    throws NumberFormatException {
        return Double.parseDouble(convertToDecimalString(str, numbDecimals,
        shouldUnpack));
    }
    
    /** Converts 'str' to a String representation of a double with the
     ** specified number of decimals. This method is useful for converting
     ** Strings with an assumed decimal point to a String that can be used to
     ** construct a BigDecimal object. This is safer than converting the String
     ** to a primitive double before constructing a BigDecimal because of the
     ** possibility of floating point rounding errors. The conversion is
     ** performed according to the following rules:
     ** <p>
     **    - If 'str' is empty, a String with the value of "0.0" is returned.
     **    - If 'str' already contains a decimal point the String is
     **      assumed to have the decimal point in the correct position
     **      and the String is returned as is (numbDecimals is ignored).
     **    - If the length of 'str' is less than 'numbDecimals', the decimal
     **      point is inserted after the sign indicator (if it exists) and
     **      before the first non-sign character of 'str', followed by the
     **      remaining characters of 'str' ('numbDecimals' is ignored).
     **    - Otherwise, the decimal point is inserted into the String before
     **      the 'numbDecimals' position in the String.
     **
     ** @param str the String to be converted to a decimal number
     ** @param numbDecimals the number of decimals assumed to exist in the
     ** String.
     ** @param shouldUnpack specifies whether the value should be unpacked by
     ** calling the unpack(...) method.
     ** @see #unpack(String, int)
     ** @return the decimal value (as a String) created from the String
     **/
    public static String convertToDecimalString(String str, int numbDecimals,
    boolean shouldUnpack) {
        String value = "0.0";
        
        if(shouldUnpack) {
            str = unpack(str, numbDecimals);
        }
        
        // Trim leading zeros and trailing spaces before getting the length of
        // the String
        str = StringUtilities.zeroTrim(str, numbDecimals);
        int length = str.length();
        
        char firstCharacter = '0';
        if(length > 0) {
            firstCharacter = str.charAt(0);
        }
        
        if(length == 0) {
            // The String was empty, return a zero value.
            value = "0.0";
        }
        else if(str.indexOf('.') > -1) {
            // The String already has a decimal, convert it to a double
            // without inserting a decimal
            value = str;
        }
        else if(length < numbDecimals || (length == numbDecimals &&
        (firstCharacter == '+' || firstCharacter == '-'))) {
            // The String has less characters than the desired number of decimals
            // return the entire String as a decimal.
            StringBuffer buffer = new StringBuffer(str.length() + 1);
            if(firstCharacter == '+' || firstCharacter == '-') {
                buffer.append(firstCharacter);
                buffer.append('.');
                buffer.append(str.substring(1, str.length()));
            }
            else {
                buffer.append('.');
                buffer.append(str.substring(1, str.length()));
            }
            value = buffer.toString();
        }
        else {
            // The String needs to have a decimal point inserted.
            value = str.substring(0, str.length() - numbDecimals) +
            "." + str.substring(str.length() - numbDecimals);
        }
        
        return value;
    }
    
    /** Unpacks 'str' if the last character indicates that the String may
     ** represent a packed number. Packed numbers are commonly used in COBOL
     ** programs. The String is unpacked according to the following rules:
     ** <p>
     **   - If the value of the last character is NOT '<b>{</b>', '<b>}</b>', or
     **     an alphabetic character between '<b>A</b>' and '<b>R</b>', the String
     **     does not represent a packed number so it is simply returned.
     **   - If the last character is one of the above characters, leading zeros
     **     are removed from 'str' so if a negative sign is prepended, it will
     **     still represent a valid number.
     **   - If the last character is '}' or is between 'J' and 'R', the packed
     **     number is negative, so the '-' sign is prepended.
     **   - If the last character is '{' or '}' it is changed to '0'.
     **   - If the last character is 'A' it is changed to '1', if it is 'B' it
     **     is changed to '2' and so on through 'I'.
     **   - If the last character is 'J' it is changed to '1' if it is 'K' it
     **     is changed to '2' and so on through 'R'.
     **
     ** @param str the String to be unpacked
     ** @param numbDecimals the number of implied decimal positions in the
     **  str parameter
     ** @return the unpacked String
     **/
    public static String unpack(String str, int numbDecimals) {
        if(str.length() > 0) {
            char lastChar = str.charAt(str.length() - 1);
            
            if(lastChar == '{' || lastChar == '}' ||
            (lastChar >= 'A' && lastChar < 'S')) {
                // Zero trim the value so if it has leading zeros, and we have to
                // prepend a sign, it will still represent a valid number.
                str = StringUtilities.zeroTrim(str, numbDecimals);
                
                StringBuffer buffer = new StringBuffer(str.length() + 1);
                
                // Determine the sign of the value represented by the String and
                // the value of the last character.
                if(lastChar == '{') {
                    // The sign is positive and the last character is 0.
                    lastChar = '0';
                }
                else if(lastChar == '}') {
                    // The sign is negative and the last character is 0.
                    buffer.append('-');
                    lastChar = '0';
                }
                else {
                    for(int positive = 'A', negative = 'J', i = '1'; positive < 'J';
                    positive++, negative++, i++) {
                        if(lastChar == positive) {
                            // The sign is positive
                            lastChar = (char) i;
                        }
                        else if(lastChar == negative) {
                            // The sign is negative
                            buffer.append('-');
                            lastChar = (char) i;
                        }
                    }
                }
                
                // If the sign is negative, it will already by prepended.  Append
                // the existing String with the last character replaced.
                buffer.append(str.substring(0, str.length() - 1));
                buffer.append(lastChar);
                return buffer.toString();
            }
            else {
                // The String is not a packed number
                return str;
            }
        }
        else {
            return str;
        }
    }
    
    /** Converts any newline characters (specified as either \n or \r\n) in the specified String
     ** to the appropriate newline character(s) for the current operating system.
     **
     ** @param the String to be converted
     ** @return the String with converted newline characters
     **/
    public static String convertNewlines(String str) {
        StringBuffer buffer = new StringBuffer();
        
        char currentChar;
        for(int i =0; i < str.length(); i++) {
            currentChar = str.charAt(i);
            
            if(currentChar == '\n') {
                // Found a linefeed not preceded by a cariage return, replace with the
                // system separator
                buffer.append(System.getProperty("line.separator"));
            }
            else if(currentChar == '\r') {
                // If the current character is a cariage return, see if the next one is a linefeed.
                // If so, replace both with the system line separator, otherwise, write the
                // carriage return
                if(str.charAt(i + 1) == '\n') {
                    buffer.append(System.getProperty("line.separator"));
                    i++;
                }
                else {
                    buffer.append(currentChar);
                }
            }
            else {
                buffer.append(currentChar);
            }
        }
        
        return buffer.toString();
    }
    
    /** Formats the specified value as a currency String using the format '$#,##0.00;(#)'. The
     ** format used is only appropriate for the eng_us locale.
     **
     ** @param value the String to be formatted
     ** @return the formatted String
     **/
    public static String formatCurrency(String value) {
        return formatDecimal(value, currencyPattern);
    }
    
    /** Formats the specified value as a phone number String using the format
     * '000-0000;(000)000-0000 #'. The format used is only appropriate for the eng_us locale.
     * Modified to accept alpha numeric characters: 800-DMV-UTAH (10/03/05 Hung Nguyen).
     *
     * @return the formatted String
     * @param value the String to be formatted */
    public static String formatPhoneNumber(String value) {
        
        if (value == null) return null;
        
        String formattedNumber = null;
        
        // See if the phone number contains any non-numeric characters
        /*for(int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            
            if(!Character.isDigit(c)) {
                //throw new IllegalArgumentException("The phone number contains non-numeric characters '" + value + "'");
                return value;
            }
        }*/
        
        int length = value.length();
        
        // Format the phone number based on the number of characters in the String
        if(length == 7) {
            formattedNumber = value.substring(0, 3) + "-" + value.substring(3);
        }
        else if(length > 9) {
        /* // format to (801)538-3518
          formattedNumber = "(" + value.substring(0, 3) + ")" + value.substring(3, 6) +
            "-" + value.substring(6, 10) + (length > 10 ? " " + value.substring(10) : "");*/
            // format to 801-538-3440
            formattedNumber = value.substring(0, 3) + "-" + value.substring(3, 6) +
            "-" + value.substring(6, 10) + (length > 10 ? " " + value.substring(10) : "");
        }
        else {
            //throw new IllegalArgumentException("Invalid phone number '" + value + "'");
            return value;
        }
        
        return formattedNumber;
    }
    
    /** Formats the specified value as a zip code string using the format
     * '00000;00000-0000 #'. The format used is only appropriate for the eng_us locale.
     *
     * @return the formatted String
     * @param value the String to be formatted */
    public static String formatZipCode(String value) {
        // check for null zip code.
        if (value == null) return "";
        
        value = value.trim();
        
        String formattedNumber = null;
        // See if the zipcode number contains any non-numeric characters
        for(int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            
            if(!Character.isDigit(c)) {
                throw new IllegalArgumentException("The zip code contains non-numeric characters '" +
                value + "'");
            }
        }
        
        int length = value.length();
        
        // Format the phone number based on the number of characters in the String
        if(length == 5) {
            // format 84114
            formattedNumber = value;
        }
        else if(length == 9) {
            // format 84114-1234
            formattedNumber = value.substring(0, 5) + "-" + value.substring(5);
        }
        else {
            throw new IllegalArgumentException("Invalid zip code '" + value + "'");
        }
        
        return formattedNumber;
    }
    
    /** Strips any formatting characters out of the specified phone number String.
     **
     ** @param phoneNumber the String to be stripped
     ** @return the stripped String
     **/
    public static String stripPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.trim();
        
        String stripCharacters = "()- ";
        String validCharacters = "0123456789";
        
        boolean canStrip = true;
        StringBuffer strippedBuffer = new StringBuffer();
        for(int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            
            if(stripCharacters.indexOf(c) > -1) {
                // The character is one that should be stripped, so don't add it to the StringBuffer
            }
            else if(validCharacters.indexOf(c) == -1) {
                // This is an invalid character, don't strip the phone number, return it as is
                canStrip = false;
                break;
            }
            else {
                // This is a valid character, add it to the buffer
                strippedBuffer.append(c);
            }
        }
        
        return canStrip ? strippedBuffer.toString() : phoneNumber;
    }
    
    /** Strips any formatting characters out of the specified zip code String.
     **
     ** @param zipCode the String to be stripped
     ** @return the stripped String
     **/
    public static String stripZipCode(String zipCode) {
        // check for null zip code.
        if (zipCode == null) return "";
        
        zipCode = zipCode.trim();
        String stripCharacters = "- ";
        String validCharacters = "0123456789";
        
        boolean canStrip = true;
        StringBuffer strippedBuffer = new StringBuffer();
        for(int i = 0; i < zipCode.length(); i++) {
            char c = zipCode.charAt(i);
            
            if(stripCharacters.indexOf(c) > -1) {
                // The character is one that should be stripped, so don't add it to the StringBuffer
            }
            else if(validCharacters.indexOf(c) == -1) {
                // This is an invalid character, don't strip the phone number, return it as is
                canStrip = false;
                break;
            }
            else {
                // This is a valid character, add it to the buffer
                strippedBuffer.append(c);
            }
        }
        
        return canStrip ? strippedBuffer.toString() : zipCode;
    }
    
    /** Formats the specified value as a decimal String using the format '#,###.#'. The
     ** format used is only appropriate for the eng_us locale.
     **
     ** @param value the String to be formatted
     ** @return the formatted String
     **/
    public static String formatDecimal(String str) {
        return formatDecimal(str, decimalPattern);
    }
    
    /** Formats the specified value using the specified format pattern. For more information on
     ** format patterns, refer to the documentation for the DecimalFormat class.
     **
     ** @param value the String to be formatted
     ** @param pattern the format pattern to be used to format the String
     ** @return the formatted String
     ** @see java.text.DecimalFormat
     **/
    public static String formatDecimal(String str, String pattern) {
        double d = Double.parseDouble(str);
        
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(d);
    }
    
    /** Replaces the specified String that has a single quote with a two single quotes.
     ** This allows the database to parse strings with quotes successfully.
     **
     ** @param sQuote the String to be parsed
     ** @return the parsed String
     **/
    public static String dbQuote(String sQuote) {
        int iQuoteIndex;
        
        iQuoteIndex = sQuote.indexOf("'");
        
        while (iQuoteIndex > -1) {
            sQuote = sQuote.substring(0, iQuoteIndex) + "'" + sQuote.substring(iQuoteIndex);
            iQuoteIndex = sQuote.indexOf("'", iQuoteIndex + 2);
        }
        
        return sQuote;
    }
    
    /**
     * Removes from specified string everything that is not alphanumeric (a-z in any case, 0-9 or _), separator (whitespace, linebreak etc...) !@#%"'()=*+-$?:/., or ; 
     * @param str
     * @return
     */
    public static String removeSpecialChars(String str) {
    	String pattern = "[^\\w\\p{Z}\\.\\?\\$\\-\\+\\*\\=\\(\\)\\'\\\"\\%\\!\\@\\#\\n\\r,;/:]";
    	if (str != null) {
    		return str.replaceAll(pattern, "");
    	} else {
    		return "";
    	}
    }
}