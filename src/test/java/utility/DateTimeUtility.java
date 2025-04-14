package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtility {
    public static String getCurrentDateTimeInFormat(String format) {
        String formattedTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime today = LocalDateTime .now();
        formattedTime = today.format(formatter);
        return formattedTime;
    }
}
