import factory.FileTransformFactory;
import vo.ResultVO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends JFrame {
    private JLabel jlDanBmoAcChk;
    private JLabel jlDanEqSav;
    private JLabel jlDanTanChk;
    private JLabel jlDanMbnaCc;
    private JLabel jlDanBmoCc;
    private JTextField tfDanBmoAcChk;
    private JButton btnDanBmoAcChk;
    private JButton btnDanEqSav;
    private JTextField tfDanEqSav;
    private JButton btnDanTanChk;
    private JTextField tfDanTanChk;
    private JPanel mainPanel;
    private JButton btnConvert;
    private JTextField tfDanBmoCc;
    private JButton btnDanBmoCc;
    private JTextField tfVarTanChk;
    private JTextField tfVarTanSav;
    private JLabel jlDanEqDeb;
    private JLabel jlVarEqSav;
    private JTextField tfDanEqDeb;
    private JTextField tfVarEqSav;
    private JButton btnDanEqDeb;
    private JButton btnVarEqSav;
    private JButton btnVarTanChk;
    private JTextField tfDanTanSav;
    private JButton btnDanTanSav;
    private JButton btnVarTanSav;
    private JTextField tfVarFlexitiCc;
    private JButton btnVarFlexitiCc;
    private JLabel jlDanTanSav;
    private JLabel jlVarTanChk;
    private JLabel jlVarTanSav;
    private JLabel jlVarFlexitiCc;
    private JButton btnDanMbnaStmt;
    private JTextField tfDanMbnaStmt;
    private JTextField tfDanMbnaCaptured;
    private JButton btnDanMbnaCaptured;
    private JLabel jlDanRogersCc;
    private JTextField tfDanRogersCc;
    private JButton btnDanRogersCc;
    private JLabel jlDanScotiaCc;
    private JTextField tfDanScotiaCc;
    private JButton btnDanScotiaCc;

    public static List<JButton> buttons;
    public static List<JTextField> textFields;

    public static void main(String[] args) {
        new MainApp();
    }

    public MainApp() throws HeadlessException {
        populateComponentLists();
        initMainPanel();
        addActionListenersToBankButtons();
        addActionListenerToConvertButton();
//        addActionListenerToMbnaMergeButton();
    }

    private void addActionListenerToConvertButton() {
        btnConvert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               List<ResultVO> resultVOs = transformFiles(textFields);
                StringBuilder messages = new StringBuilder();
                for (ResultVO vo : resultVOs) {
                    messages.append((vo.isResult()) ? "<html><font color='green'>✅</font> Success - " : "<html><font color='red'>❌</font> Error - ");
                    messages.append((vo.isResult()) ? vo.getOutputfilePath() + "\n" : vo.getMessage() + "\n");
                }
                if (!messages.toString().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, messages);
                }
            }
        });
    }

    private List<ResultVO> transformFiles(List<JTextField> textFields) {
        List<ResultVO> results = new ArrayList();
        JTextField textField;
        for (int i = 0; i < textFields.size(); i++) {
            textField = textFields.get(i);
            if (textField.getText().isEmpty()) {
                continue;
            }
            results.add(FileTransformFactory.getFileTransformer(textFields, textField).transform(textField.getText()));
        }

        return results;
    }

    private void populateComponentLists() {
        buttons = new ArrayList<JButton>();
        textFields = new ArrayList<JTextField>();
        buttons.add(btnDanBmoAcChk); textFields.add(tfDanBmoAcChk); // case 0
        buttons.add(btnDanEqSav); textFields.add(tfDanEqSav); // case 1
        buttons.add(btnDanEqDeb); textFields.add(tfDanEqDeb); // case 2
        buttons.add(btnVarEqSav); textFields.add(tfVarEqSav); // case 3
        buttons.add(btnDanTanChk); textFields.add(tfDanTanChk); // case 4
        buttons.add(btnDanTanSav); textFields.add(tfDanTanSav); // case 5
        buttons.add(btnVarTanChk); textFields.add(tfVarTanChk); // case 6
        buttons.add(btnVarTanSav); textFields.add(tfVarTanSav); // case 7
        buttons.add(btnDanBmoCc); textFields.add(tfDanBmoCc); // case 8
        buttons.add(btnVarFlexitiCc); textFields.add(tfVarFlexitiCc); // case 9
        buttons.add(btnDanMbnaCaptured); textFields.add(tfDanMbnaCaptured); // case 10
        buttons.add(btnDanRogersCc); textFields.add(tfDanRogersCc); // case 11
        buttons.add(btnDanScotiaCc); textFields.add(tfDanScotiaCc); // case 12
    }

    private void initMainPanel() {
        //mainPanel = new JPanel();
        setContentPane(mainPanel);
        setTitle("Monarch Money File Format Converter");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1711, 811);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addActionListenersToBankButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).addActionListener(getActionListener(i));
        }
    }

    private ActionListener getActionListener(final int txtFldIndex) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFileChooser(textFields.get(txtFldIndex));
            }
        };
    }

    private void openFileChooser(JTextField textField) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files only", "csv");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File("C:\\DANUSH\\MonarchMoney"));
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }
}
