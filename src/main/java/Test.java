import util.GenericUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        String inputDate = "12 AUG 2025";
        String inputDateFormat = "dd MMM yyyy";

//        DateTimeFormatter inputFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern(inputDateFormat).toFormatter();
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputDateFormat);
//        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
//
//        System.out.println(date.format(outFormatter));
        String month = inputDate.substring(4, 6);

        System.out.println(inputDate.replace(month, month.toLowerCase()));

//        System.out.println(GenericUtil.stripOffBeginAndEndChars(in));
    }
}
