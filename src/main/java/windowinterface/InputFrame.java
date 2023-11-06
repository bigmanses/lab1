package windowinterface;

import listener.MouseClick;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URLDecoder;
import java.util.Objects;

/**
 * Окно ввода имени файла
 */
public class InputFrame extends JFrame {

    /** Текст для лейбла ввода имени файла */
    private final String TEXT_INPUT_FILE = "Введите имя файла";
    /** Лэйбл ввода текст */
    private final JLabel text = new JLabel(TEXT_INPUT_FILE);
    /** Поле, для ввода имени файла */
    private final JTextField inputText = new JTextField(15);
    /** Кнопка для начала обработки имени файла */
    private final JButton okey = new JButton("Ввод");
    /** Путь к файлу */
    private String path;
    /** Имя файла */
    private String fileName;
    /** Окно ввода слова для замены */
    private InputWordFrame frame;


    public InputFrame() {
        super("Ввод файла");
        createPath();
        createGUI();
        addAction();
    }

    /**
     * Создать интерфейс окна
     */
    private void createGUI() {
        this.setSize(400, 200);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.add(text, new GridBagConstraints(0, 6, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(30,15 ,2 ,2), 0, 0));
        this.add(inputText, new GridBagConstraints(0, 8, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(10,2 ,2 ,2), 0, 0));
        this.add(okey, new GridBagConstraints(0, 10, 1, 1, 0, 0.2, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(10,2 ,2 ,2), 0, 0));
        this.setVisible(true);
    }

    /**
     * Добавить действие кнопкам
     */
    private void addAction() {
        okey.addActionListener(action -> {
            fileName = inputText.getText();
            if (checkEmptyText(fileName) && checkCorrectTextFile(fileName)) {
                frame = new InputWordFrame(path + fileName + ".txt");
                this.setVisible(false);

            }

        });
    }

    /**
     * Проверить поле на пустоту текста
     * @param text - текст вводимые в поле
     * @return - true, если поле непустое
     */
    private boolean checkEmptyText (String text) {
        if (Objects.equals(text, "")) {
            inputText.setText("Введите имя файла");
            inputText.setBackground(Color.RED);
            inputText.addMouseListener(new MouseClick());
        }

        return !Objects.equals(text, "");
    }

    private void createPath() {
        path = URLDecoder.decode(InputFrame.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath());
    }

    /**
     * Првоерить корректность ввода имени файла
     * @param name - имя файла
     * @return true, если файл существует
     */
    private boolean checkCorrectTextFile(String name) {
        if (!new File(path + name + ".txt").exists()) {
            inputText.setText("Файл не существует");
            inputText.setBackground(Color.RED);
            inputText.addMouseListener(new MouseClick());
        }
        return new File(path + name + ".txt").exists();
    }
}
