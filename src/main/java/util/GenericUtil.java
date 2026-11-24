package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GenericUtil {

    public static String convertToMonarchFormat(String inputDate, String inputDateFormat) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputDateFormat);
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);

        return date.format(outFormatter);
    }

    public static String getOutputFilePath(String inputFilePath, String outputFileSuffix) throws IOException {
        String targetDirName = "transformed";
        Path path = Paths.get(inputFilePath);
        Path parentFolder = path.getParent();
        Path targetDir = Paths.get(parentFolder.toAbsolutePath().toString(), targetDirName);
        if (!Files.exists(targetDir)) {
            Files.createDirectories(targetDir);
        }

        return targetDir.toAbsolutePath().toString() + "\\" + outputFileSuffix + ".csv";
    }

    public static String stripOffBeginAndEndChars(String text) {
        return text.substring(1, text.length() - 1);
    }

    public static String invertAmount(String amount) {
        Double amt = new Double(amount);
        amt = amt * -1;

        return amt.toString();
    }

    public static String getRespectiveMerchant(String [] merchants, String desc) {
        for (String merchant : merchants) {
            if (desc.toLowerCase().contains(merchant.toLowerCase())) {
                if ("Giesecke".equalsIgnoreCase(merchant)) {
                    merchant = "Giesecke+Devrient";
                } else if ("Acct".equalsIgnoreCase(merchant)) {
                    merchant = "Account";
                } else if ("Regular Purchase".equalsIgnoreCase(merchant) || "Creditor Insurance".equalsIgnoreCase(merchant)) {
                    merchant = "Wayfair";
                } else if ("Univ of Sk".equalsIgnoreCase(merchant)) {
                    merchant = "University of Saskatchewan";
                } else if ("TRAVEL INSURANCE SPECI".equalsIgnoreCase(merchant)) {
                    merchant = "LIC Insurance";
                } else if ("UberTrip".equalsIgnoreCase(merchant)) {
                    merchant = "Uber Trip";
                }

                return merchant;
            }
        }

        return "";
    }

}
