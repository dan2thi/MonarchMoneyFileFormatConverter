package transformers.creditcards;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import transformers.AbstractFileTransformer;
import util.GenericUtil;

import java.io.IOException;

public class RogersCcTransformer extends AbstractFileTransformer {

    public RogersCcTransformer(String accountName) {
        this.setAccountName(accountName);
        this.setMerchants(new String[]{"Dollarama", "Dollar Tree", "Temu", "Walmart", "UberTrip", "Costco", "Home Depot", "Rona", "Uber Eats", "Rona", "Shaw", "AliExpress"});
    }

    @Override
    protected void skipTheReaderNTimes(CSVReader reader) throws IOException, CsvValidationException {
        reader.readNext();
    }

    @Override
    protected String getDateValue(String[] readingRow) {
        return GenericUtil.convertToMonarchFormat(readingRow[0], "yyyy-MM-dd");
    }

    @Override
    protected String getAmountValue(String[] readingRow) {
        return GenericUtil.invertAmount(readingRow[12].replace("$", ""));
    }

    @Override
    protected String getDescriptionValue(String[] readingRow) {
        return (readingRow[6] + " " + readingRow[7]).replace("*", "").replace("-", "");
    }
}
