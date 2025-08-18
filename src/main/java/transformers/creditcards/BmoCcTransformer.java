package transformers.creditcards;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import transformers.AbstractFileTransformer;
import util.GenericUtil;

import java.io.IOException;

public class BmoCcTransformer extends AbstractFileTransformer {

    public BmoCcTransformer(String accountName) {
        this.setAccountName(accountName);
        this.setMerchants(new String[]{"Apple", "Paypal", "Fizz", "Acct"});
    }

    @Override
    protected void skipTheReaderNTimes(CSVReader reader) throws IOException, CsvValidationException {
        for (int i = 0; i < 3; i++) {
            reader.readNext();
        }
    }

    @Override
    protected String getDateValue(String[] readingRow) {
        return GenericUtil.convertToMonarchFormat(readingRow[2], "yyyyMMdd");
    }

    @Override
    protected String getAmountValue(String[] readingRow) {
        return GenericUtil.invertAmount(readingRow[4]);
    }

    @Override
    protected String getDescriptionValue(String[] readingRow) {
        return readingRow[5];
    }

}
