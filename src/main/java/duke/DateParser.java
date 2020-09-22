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

    public static LocalDateTime parseDate(String in) throws DateTimeParseException {

        for(DateTimeFormatter df : dformaters) {
            try {
                return LocalDate.parse(in, df).atStartOfDay();
            } catch (DateTimeParseException e) {
            }
        }

        return null;
    }
}
