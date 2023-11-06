package windowinterface;

import listener.MouseClick;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Окно ввода слова для замены
 */
public class InputWordFrame extends JFrame {
    /** Текст для лейбла ввода заменяемого слова */
    private final String REPLACEABLE_TEXT = "Заменяемое слово";
    /** Текст для лейбла ввода заменяющего слова */
    private final String SUBSTITUTE_TEXT = "Заменяющее слово";
    /** Лэйбл ввода текст */
    private final JLabel replaceableLabel = new JLabel(REPLACEABLE_TEXT);
    /** Лэйбл ввода текст */
    private final JLabel substituteLabel = new JLabel(SUBSTITUTE_TEXT);
    /** Поле, для ввода заменяемого слова */
    private final JTextField inputReplaceableText = new JTextField(15);
    /** Поле, для ввода заменяющего слова */
    private final JTextField inputSubstituteText = new JTextField(15);
    /** Кнопка для начала обработки замены */
    private final JButton okey = new JButton("Ввод");
    /** Путь к файлу */
    private String filePath;
    /** Флаг наличия слова в файле */
    private boolean wordPresence = false;

    public InputWordFrame(String filePath) {
        super("Замена");
        this.filePath = filePath;
        createGUI();
        addAction();
    }

    /**
     * Создать интерфейс окна
     */
    private void createGUI() {
        this.setSize(400, 250);
        setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        this.add(replaceableLabel, new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(20,20 ,2 ,2), 0, 0));
        this.add(inputReplaceableText, new GridBagConstraints(0, 7, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,2 ,2 ,2), 0, 0));
        this.add(substituteLabel, new GridBagConstraints(0, 8, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,15 ,2 ,2), 0, 0));
        this.add(inputSubstituteText, new GridBagConstraints(0, 9, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,2 ,2 ,2), 0, 0));
        this.add(okey, new GridBagConstraints(0, 12, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,2 ,2 ,2), 0, 0));
        this.setVisible(true);
    }

    /**
     * Добавить действие кнопкам
     */
    private void addAction() {
        okey.addActionListener(action -> {
            changeFile();
            if (checkCorrectTextInFile()) {
                okey.setBackground(Color.GREEN);
            } else {
                okey.setBackground(Color.RED);
            }
        });
    }

    /**
     * Изменить содержимое файла
     */
    private void changeFile() {

        wordPresence = false;
        File f = new File(filePath);
        try {
            String ENDL = System.getProperty("line.separator");
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(f));
            String ln;
            while((ln = br.readLine()) != null) {
                sb.append(ln.replace(inputReplaceableText.getText(), inputSubstituteText.getText())).append(ENDL);
                wordPresence = wordPresence || !ln.equals(ln.replace(inputReplaceableText.getText(), inputSubstituteText.getText()));
            }
            br.close();
            if (!wordPresence) {
                return;
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(sb.toString());
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Проверить файл на существование изменяемого слова
     * @return - true, если слово существует
     */
    private boolean checkCorrectTextInFile() {
        if (!wordPresence) {
            inputReplaceableText.setText("Слова не существует");
            inputReplaceableText.setBackground(Color.RED);
            inputReplaceableText.addMouseListener(new MouseClick());
        }
        return wordPresence;
    }
}
