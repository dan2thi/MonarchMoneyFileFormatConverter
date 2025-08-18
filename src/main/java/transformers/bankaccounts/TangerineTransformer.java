package transformers.bankaccounts;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import transformers.AbstractFileTransformer;
import util.GenericUtil;

import java.io.IOException;

public class TangerineTransformer extends AbstractFileTransformer {

    public TangerineTransformer(String accountName) {
        this.setAccountName(accountName);
        this.setMerchants(new String[] {"Tangerine", "Interac", "Sun Life", "Simplii"});
    }

    @Override
    protected void skipTheReaderNTimes(CSVReader reader) throws IOException, CsvValidationException {
        reader.readNext();
    }

    @Override
    protected String getDateValue(String[] readingRow) {
        return GenericUtil.convertToMonarchFormat(readingRow[0], "MM/dd/yyyy");
    }

    @Override
    protected String getAmountValue(String[] readingRow) {
        return readingRow[4];
    }

    @Override
    protected String getDescriptionValue(String[] readingRow) {
        return readingRow[2];
    }
}
