package transformers.creditcards;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import transformers.AbstractFileTransformer;
import util.GenericUtil;
import vo.ResultVO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MbnaTransformer extends AbstractFileTransformer {

    public MbnaTransformer(String accountName) {
        this.setAccountName(accountName);
        this.setMerchants(new String[]{"Amazon", "Payment"});
    }

   /* @Override
    public ResultVO transform(String... filePaths) {
        ResultVO resultVO = new ResultVO();
        String outputFilePath = null;
        CSVWriter writer = null;
        CSVReader reader = null;
        try {
            List<String[]> writingRows = new ArrayList<>();
            writingRows.add(new String[]{"Date", "Merchant", "Category", "Account", "Original Statement", "Notes", "Amount", "Tags"});
            for (String filePath : filePaths) {

            }
            String[] stmtReadingRow, capturedReadingRow;
            stmtReader = new CSVReader(new FileReader(filePath[0]));
            capturedReader = new CSVReader(new FileReader(filePath[1]));
            stmtReader.readNext(); capturedReader.readNext();
            addWritingRows(stmtReader, writingRows);
            addWritingRows(capturedReader, writingRows);

      *//*      String date, merchant, amount, description;
            while ((stmtReadingRow = stmtReader.readNext()) != null) {
                date = this.getDateValue(stmtReadingRow);
                description = stmtReadingRow[1];
                merchant = GenericUtil.getRespectiveMerchant(this.getMerchants(), description);
                amount = stmtReadingRow[3];
                writingRows.add(new String[]{date, merchant, "", this.getAccountName(), description, "", amount, ""});
            }
            while ((capturedReadingRow = capturedReader.readNext()) != null) {
                date = this.getDateValue(capturedReadingRow[0]);
                description = capturedReadingRow[1];
                merchant = GenericUtil.getRespectiveMerchant(this.getMerchants(), description);
                amount = GenericUtil.invertAmount(capturedReadingRow[3].replace("$", ""));
                writingRows.add(new String[]{date, merchant, "", this.getAccountName(), description, "", amount, ""});
            }*//*
            outputFilePath = GenericUtil.getOutputFilePath(filePath[0], "tranformed-" + this.getAccountName());
            writer = new CSVWriter(new FileWriter(outputFilePath));
            writer.writeAll(writingRows);
            writer.flush();
            resultVO.setResult(true);
            resultVO.setOutputfilePath(outputFilePath);
        } catch (IOException | CsvValidationException e) {
            resultVO.setResult(false);
            resultVO.setMessage(e.getMessage());
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    stmtReader.close();
                    capturedReader.close();
                    writer.close();
                } catch (IOException ignored) {
                }
            }
        }

        return resultVO;
    }*/

/*    private void addWritingRows(CSVReader csvReader, List<String[]> writingRows) throws IOException, CsvValidationException {
        String[] readingRow;
        String date, merchant, amount, description;
        while ((readingRow = csvReader.readNext()) != null) {
            date = this.getDateValue(readingRow);
            description = readingRow[1];
            merchant = GenericUtil.getRespectiveMerchant(this.getMerchants(), description);
            amount = readingRow[3];
            writingRows.add(new String[]{date, merchant, "", this.getAccountName(), description, "", amount, ""});
        }
    }*/

    @Override
    protected void skipTheReaderNTimes(CSVReader reader) throws IOException, CsvValidationException {
        reader.readNext();
    }

    @Override
    protected String getDateValue(String[] readingRow) {
        return GenericUtil.convertToMonarchFormat(readingRow[0], "MM/dd/yyyy");
    }

//    private String getDateValue(String date) {
//        return GenericUtil.convertToMonarchFormat(date, "MM/dd/yyyy");
//    }

    @Override
    protected String getAmountValue(String[] readingRow) {
        return GenericUtil.invertAmount(readingRow[3].replace("$", ""));
    }

    @Override
    protected String getDescriptionValue(String[] readingRow) { return readingRow[1]; }
}
