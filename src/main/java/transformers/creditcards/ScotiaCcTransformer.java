package transformers.creditcards;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import transformers.AbstractFileTransformer;
import util.GenericUtil;

import java.io.IOException;

public class ScotiaCcTransformer extends AbstractFileTransformer {

    public ScotiaCcTransformer(String accountName) {
        this.setAccountName(accountName);
        this.setMerchants(new String[]{"Fizz", "Thank you", "Square One Insurance", "Adobe", "OpenAI", "TRAVEL INSURANCE SPECI", "Chexy", "Shaw"});
    }

    @Override
    protected void skipTheReaderNTimes(CSVReader reader) throws IOException, CsvValidationException {
        reader.readNext();
    }

    @Override
    protected String getDateValue(String[] readingRow) {
        return GenericUtil.convertToMonarchFormat(readingRow[1], "yyyy-MM-dd");
    }

    @Override
    protected String getAmountValue(String[] readingRow) {
        return GenericUtil.invertAmount(readingRow[6]);
    }

    @Override
    protected String getDescriptionValue(String[] readingRow) {
        return readingRow[2] + " " + readingRow[3];
    }
}
