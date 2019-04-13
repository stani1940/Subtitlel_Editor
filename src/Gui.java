import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


public class Gui extends Component {

    JButton loadFileButton;
    JButton openFileButton;
    JButton exitButton;
    JButton submitButton;
    ButtonGroup buttonGroup;
    JRadioButton fastRadioButton, slowRadioButton;
    JCheckBox removeTagButton;
    JTextField textField;
    JLabel textLabel;
    JTextArea log;


    Gui() {
        JFrame frame = new JFrame("Редактор на субтитри");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadFileButton = new JButton("Зареди файл");
        openFileButton = new JButton("Отвори файл");
        exitButton = new JButton("Изход");
        submitButton = new JButton("Запиши промените");
        fastRadioButton = new JRadioButton("Забързай с");
        slowRadioButton = new JRadioButton("Забави с");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(fastRadioButton);
        buttonGroup.add(slowRadioButton);
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

    public void setExitButton() {

        exitButton.addActionListener(e -> {
            System.exit(0);
        });


    }

    public void setLoadFileButton() {
        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                showOpenFileDialog();

            }

        });
    }

    JFileChooser fileChooser = new JFileChooser();

    private void showOpenFileDialog() {

        fileChooser.setCurrentDirectory(new File(("src")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Subtitle Documents with srt and sub", "sub", "srt"));
        fileChooser.setAcceptAllFileFilterUsed(true);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            log.append("Зареждам файл: " + selectedFile.getName() + ".");

        } else {
            log.append("Зареждане на файл отказано от потребителя.");

        }

    }


    private String getFileExtension(JFileChooser fileChooser) {
        String[] split = fileChooser.getSelectedFile().getName().split("\\.");
        String ext = split[split.length - 1];
        return ext;
    }


    public void setSubmitButton() {
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String textMs = textField.getText();

                if ((!textMs.isEmpty()) && (textMs != null)) {
                    if (getRadioButtonsValue()) {
                        int mSeconds = Integer.parseInt(textMs);
                        if (getFileExtension(fileChooser).equals("sub")) {
                            FixingSubtitles.ADDITION = mSeconds;
                            FixingSubtitles.INPUT_FILE = String.valueOf(fileChooser.getSelectedFile());
                            new FixingSubtitles();

                        } else if (getFileExtension(fileChooser).equals("srt")) {
                            new SrtSubtitleLogic().INPUT_FILE = String.valueOf(fileChooser.getSelectedFile());

                            new SrtSubtitleLogic().ADDITION = mSeconds;
                            new SrtSubtitleLogic();
                        }
                    }
                    if (!getRadioButtonsValue()) {
                        int mSeconds = -Integer.parseInt(textMs);
                        if (getFileExtension(fileChooser).equals("sub")) {
                            new FixingSubtitles().INPUT_FILE = String.valueOf(fileChooser.getSelectedFile());

                            new FixingSubtitles().ADDITION = mSeconds;
                        } else if (getFileExtension(fileChooser).equals("srt")) {
                            new SrtSubtitleLogic().INPUT_FILE = String.valueOf(fileChooser.getSelectedFile());

                            new SrtSubtitleLogic().ADDITION = mSeconds;
                            new SrtSubtitleLogic();
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Моля въведете число за милисекунди");
                }
            }
        });
    }

    public void setRemoveTagButton() {
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

    public boolean getRadioButtonsValue() {
        if (fastRadioButton.isSelected()) {
            return true;
        } else {
            return false;
        }

    }

}