package windowinterface;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URLDecoder;
import java.util.Objects;

public class InputFrame extends JFrame {

    private final String TEXT_INPUT_FILE = "Введите имя файла";

    private final JLabel text = new JLabel(TEXT_INPUT_FILE);

    private final JTextField inputText = new JTextField(15);

    private final JButton okey = new JButton("Ввод");

    private String path;

    private String fileName;

    private InputWordFrame frame;


    public InputFrame() {
        super();
        createPath();
        createGUI();
        addAction();
    }

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

    private void addAction() {
        okey.addActionListener(action -> {
            fileName = inputText.getText();
            if (checkEmptyText(fileName) && checkCorrectTextFile(fileName)) {
                frame = new InputWordFrame(path + fileName + ".txt");
                this.setVisible(false);

            }

        });
    }

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

    private boolean checkCorrectTextFile(String text) {
        if (!new File(path + text + ".txt").exists()) {
            inputText.setText("Файл не существует");
            inputText.setBackground(Color.RED);
            inputText.addMouseListener(new MouseClick());
        }
        return new File(path + text + ".txt").exists();
    }
}
