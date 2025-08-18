package factory;

import transformers.*;
import transformers.bankaccounts.BmoBankAccountTransformer;
import transformers.bankaccounts.EqTransformer;
import transformers.bankaccounts.TangerineTransformer;
import transformers.creditcards.BmoCcTransformer;
import transformers.creditcards.FlexitiTransformer;
import transformers.creditcards.MbnaTransformer;

import javax.swing.*;
import java.util.List;

public class FileTransformFactory {

    public static FileTransformer getFileTransformer(List<JTextField> textFields, JTextField textField) {
        FileTransformer fileTransformer;
        int index = textFields.indexOf(textField);
        switch (index) {
            case 0:
                fileTransformer = new BmoBankAccountTransformer("Danush - BMO - Chequing");
                break;
            case 1:
                fileTransformer = new EqTransformer("DANUSH - EQ - Savings");
                break;
            case 2:
                fileTransformer = new EqTransformer("DANUSH - EQ - Debit Card");
                break;
            case 3:
                fileTransformer = new EqTransformer("VARUNI - EQ - Savings");
                break;
            case 4:
                fileTransformer = new TangerineTransformer("DANUSH - Tangerine - Chequing");
                break;
            case 5:
                fileTransformer = new TangerineTransformer("DANUSH - Tangerine - Savings");
                break;
            case 6:
                fileTransformer = new TangerineTransformer("VARUNI - Tangerine - Chequing");
                break;
            case 7:
                fileTransformer = new TangerineTransformer("VARUNI - Tangerine - Savings");
                break;
            case 8:
                fileTransformer = new BmoCcTransformer("DANUSH - BMO - MasterCard CC");
                break;
            case 9:
                fileTransformer = new FlexitiTransformer("Wayfair - Flexiti CC");
                break;
            case 10:
                fileTransformer = new MbnaTransformer("DANUSH - MBNA - Amazon CC");
                break;
            default:
                fileTransformer = null;
                break;
        }

        return fileTransformer;
    }
}
