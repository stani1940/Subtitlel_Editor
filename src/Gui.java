import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


public class Gui extends Component {

    static JButton loadFileButton;
    JButton openFileButton;
    static JButton exitButton;
    static JButton submitButton;
    ButtonGroup buttonGroup;
    JRadioButton fastRadioButton, slowRadioButton;
    static JCheckBox removeTagButton;
    static JTextField textField;
    JLabel textLabel;
    //static JFileChooser fileChooser;
    static JTextArea log;


    Gui() {
        JFrame frame = new JFrame("Редактор на субтитри");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadFileButton = new JButton("Зареди файл");
        openFileButton = new JButton("Отвори файл");
        exitButton = new JButton("Изход");
        exitButton = new JButton("Изход");
        submitButton = new JButton("Запиши промените");
        fastRadioButton = new JRadioButton("Забързай с");
        slowRadioButton = new JRadioButton("Забави с");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(fastRadioButton);
        buttonGroup.add(loadFileButton);
        textField = new JTextField();
        textLabel = new JLabel();
        log = new JTextArea(5, 20);

        loadFileButton.setBounds(80, 30, 200, 50);
        openFileButton.setBounds(80, 90, 200, 50);
        openFileButton.setBackground(Color.YELLOW);
        exitButton.setBounds(80, 150, 200, 50);
        exitButton.setBackground(Color.red);
        textField.setBounds(350, 100, 50, 50);
        submitButton.setBounds(80, 210, 200, 50);
        submitButton.setBackground(Color.GREEN);
        fastRadioButton.setBounds(350, 30, 100, 50);
        slowRadioButton.setBounds(350, 60, 100, 50);
        removeTagButton = new JCheckBox("Премахни таговете от текста");
        removeTagButton.setBounds(350, 160, 200, 100);
        log.setBounds(80, 270, 200, 50);

        frame.add(loadFileButton);
        frame.add(openFileButton);
        frame.add(exitButton);
        frame.add(submitButton);
        frame.add(fastRadioButton);
        frame.add(slowRadioButton);
        frame.add(textField);
        frame.add(log);
        frame.add(removeTagButton);
        frame.setSize(700, 400);
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public static void setExitButton() {

        exitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    public static void setLoadFileButton() {
        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                showOpenFileDialog();

            }

        });
    }
     static JFileChooser fileChooser = new JFileChooser();
    private static void showOpenFileDialog() {

        fileChooser.setCurrentDirectory(new File(("src")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Subtitle Documents.srt", "sub", "srt"));
        fileChooser.setAcceptAllFileFilterUsed(true);
          int result =  fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            log.append("Зареждам файл: " + selectedFile.getName() + ".");

        } else {
            log.append("Зареждане на файл отказано от потребителя.");

        }

    }

    public static void setSubmitButton() {
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FixingSubtitles().INPUT_FILE = String.valueOf(fileChooser.getSelectedFile());

                new FixingSubtitles();
                String textMs = textField.getText().trim();

                if ((!textMs.isEmpty()) && (textMs != null)) {
                    int addition = Integer.parseInt(textMs);


                } else {
                    JOptionPane.showMessageDialog(null, "Моля въведете число за милисекунди");
                }
            }
        });
    }

    public static void setRemoveTagButton() {
        removeTagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (removeTagButton.isSelected()) {
                    String text = "<B>I don't want this to be bold<\\B>";
                    text = text.replaceAll("\\<.*?\\>", "");
                    System.out.println(text);
                } else {
                    System.out.println("no");
                }
            }
        });
    }

}