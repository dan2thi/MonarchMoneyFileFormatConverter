package transformers.bankaccounts;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import transformers.AbstractFileTransformer;
import util.GenericUtil;

import java.io.IOException;

public class EqTransformer extends AbstractFileTransformer {

    public EqTransformer(String accountName) {
        this.setAccountName(accountName);
        this.setMerchants(new String[]{"Interac", "Account Credited", "Interest", "Giesecke", "MBNA", "CIBC", "Triangle", "Flexiti", "Scotia", "American Express", "Univ of Sk"});
    }

    @Override
    protected void skipTheReaderNTimes(CSVReader reader) throws IOException, CsvValidationException {
        reader.readNext();
    }

    @Override
    protected String getDateValue(String[] readingRow) {
        String inputDate = readingRow[0];
        String mm = inputDate.substring(4, 6);

        return GenericUtil.convertToMonarchFormat(inputDate.replace(mm, mm.toLowerCase()), "yyyy-MM-dd");
    }

    @Override
    protected String getAmountValue(String[] readingRow) {
        return readingRow[2].replace("$", "");
    }

    @Override
    protected String getDescriptionValue(String[] readingRow) {
        return readingRow[1];
    }
}
