package transformers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import util.GenericUtil;
import vo.ResultVO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileTransformer implements FileTransformer {

    private String accountName;
    private String[] merchants;

    @Override
    public ResultVO transform(String... filePaths) {
        ResultVO resultVO = new ResultVO();
        CSVWriter writer = null;
        List<String[]> writingRows = new ArrayList<>();
        writingRows.add(new String[]{"Date", "Merchant", "Category", "Account", "Original Statement", "Notes", "Amount", "Tags"});
        try{
            for (String filePath : filePaths) {
                CSVReader reader = new CSVReader(new FileReader(filePath));
                String[] readingRow;
                skipTheReaderNTimes(reader);
                String date, merchant, amount, description;
                while ((readingRow = reader.readNext()) != null) {
                    date = getDateValue(readingRow);
                    merchant = GenericUtil.getRespectiveMerchant(this.getMerchants(), getDescriptionValue(readingRow));
                    amount = getAmountValue(readingRow);
                    description = getDescriptionValue(readingRow);
                    writingRows.add(new String[]{date, merchant, "Uncategorized", getAccountName(), description, "", amount, ""});
                }
            }
            String outputFilePath = GenericUtil.getOutputFilePath(filePaths[0], "transformed-" + getAccountName());
            writer = new CSVWriter(new FileWriter(outputFilePath));
            writer.writeAll(writingRows);
            writer.flush();
            resultVO.setResult(true);
            resultVO.setOutputfilePath(outputFilePath);
        } catch (IOException | CsvValidationException | RuntimeException e ) {
            resultVO.setResult(false);
            resultVO.setMessage(e.getMessage());
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ignored) {
                }
            }
        }

        return resultVO;
    }

    protected abstract void skipTheReaderNTimes(CSVReader reader) throws IOException, CsvValidationException;

    protected abstract String getDateValue(String[] readingRow);

    protected abstract String getAmountValue(String[] readingRow);

    protected abstract String getDescriptionValue(String[] readingRow);

    protected String getAccountName() {
        return this.accountName;
    }

    protected void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String[] getMerchants() { return merchants; }

    public void setMerchants(String[] merchants) { this.merchants = merchants; }
}
