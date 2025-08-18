package transformers.bankaccounts;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import transformers.AbstractFileTransformer;
import util.GenericUtil;

import java.io.IOException;

public class BmoBankAccountTransformer extends AbstractFileTransformer {


    public BmoBankAccountTransformer(String accountName) {
        this.setAccountName(accountName);
        this.setMerchants(new String[]{"BNC", "Manulife", "Interac", "Triangle", "Performance Plan", "Plan Fee Rebate"});
    }

    @Override
    protected void skipTheReaderNTimes(CSVReader reader) throws IOException, CsvValidationException{
        for (int i = 0; i < 6; i++) {
            reader.readNext();
        }
    }

    @Override
    protected String getDateValue(String[] readingRow) {
        return GenericUtil.convertToMonarchFormat(readingRow[2], "yyyyMMdd");
    }

    @Override
    protected String getAmountValue(String[] readingRow) {
        return readingRow[3];
    }

    @Override
    protected String getDescriptionValue(String[] readingRow) {
        return readingRow[4];
    }
}
