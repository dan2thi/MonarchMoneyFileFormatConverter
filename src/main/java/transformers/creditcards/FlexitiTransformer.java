package transformers.creditcards;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import transformers.AbstractFileTransformer;
import util.GenericUtil;

import java.io.IOException;

public class FlexitiTransformer extends AbstractFileTransformer {

    public FlexitiTransformer(String accountName) {
        this.setAccountName(accountName);
        this.setMerchants(new String[]{"Regular Purchase", "Payment", "Refund", "Wayfair", "Creditor Insurance"});
    }

    @Override
    protected void skipTheReaderNTimes(CSVReader reader) throws IOException, CsvValidationException {
        reader.readNext();
    }

    @Override
    protected String getDateValue(String[] readingRow) {
        String dateVal = readingRow[0].trim();

        return GenericUtil.convertToMonarchFormat(dateVal.substring(2, dateVal.length() - 1), "MMM dd, yyyy");
    }

    @Override
    protected String getAmountValue(String[] readingRow) {
        String debitVal = readingRow[2].replace("$", "").replace("=", "").replace("\"", "").trim();
        if (debitVal.isEmpty()) {
            String creditVal = readingRow[3].replace("$", "").replace("=", "").replace("\"", "").trim();
            return creditVal;
        }

        return GenericUtil.invertAmount(debitVal);
    }

    @Override
    protected String getDescriptionValue(String[] readingRow) {
        return GenericUtil.stripOffBeginAndEndChars(readingRow[1]);
    }
}
