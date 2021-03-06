package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;


public class DateParser {

    private static final List<DateTimeFormatter> dformaters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            //DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy MM dd") );

    /**
     * Transfers a given string following one of the accepted format into date-time format
     * @param in String input to be transferred
     * @return time in the form of LocalDateTime or null if no String in is found
     */
    public static String parseDate(String in) {

        for(DateTimeFormatter df : dformaters) {
            try {
                DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return df2.format(LocalDate.parse(in, df));
            } catch (DateTimeParseException e) {

            }
        }

        return in;
    }
}
