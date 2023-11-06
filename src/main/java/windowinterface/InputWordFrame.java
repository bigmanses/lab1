package windowinterface;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class InputWordFrame extends JFrame {

    private final String REPLACEABLE_TEXT = "Заменяемое слово";

    private final String SUBSTITUTE_TEXT = "Заменяющее слово";

    private final JLabel replaceableLabel = new JLabel(REPLACEABLE_TEXT);

    private final JLabel substituteLabel = new JLabel(SUBSTITUTE_TEXT);

    private final JTextField inputReplaceableText = new JTextField(15);

    private final JTextField inputSubstituteText = new JTextField(15);

    private final JButton okey = new JButton("Ввод");

    private String filePath;

    private boolean wordPresence = false;

    public InputWordFrame(String filePath) {
        super("Замена");
        this.filePath = filePath;
        createGUI();
        addAction();
    }

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

    private void addAction() {
        okey.addActionListener(action -> {
            changeFile();
            if (checkCorrectTextFile()) {
                okey.setBackground(Color.GREEN);
            } else {
                okey.setBackground(Color.RED);
            }
        });
    }

    private void changeFile() {

        wordPresence = false;

        File f = new File(filePath);
        try
        {
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

    private boolean checkCorrectTextFile() {
        if (!wordPresence) {
            inputReplaceableText.setText("Слова не существует");
            inputReplaceableText.setBackground(Color.RED);
            inputReplaceableText.addMouseListener(new MouseClick());
        }
        return wordPresence;
    }
}
