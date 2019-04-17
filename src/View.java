import java.awt.*;
import javax.swing.*;

public class View extends Component {

    private JButton loadFileButton;
    private JButton openFileButton;
    private JButton exitButton;
    private JButton submitButton;
    private JRadioButton fastRadioButton, slowRadioButton;
    private JCheckBox removeTagButton;
    private JTextField textField;
    private JTextArea log;
    private JFileChooser fileChooser = new JFileChooser();

    public View() {
        JFrame frame = new JFrame("Редактор на субтитри");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadFileButton = new JButton("Зареди файл");
        openFileButton = new JButton("Отвори файл");
        exitButton = new JButton("Изход");
        submitButton = new JButton("Запиши промените");
        fastRadioButton = new JRadioButton("Забързай с");
        slowRadioButton = new JRadioButton("Забави с");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(fastRadioButton);
        buttonGroup.add(slowRadioButton);
        JLabel textLabelMSeconds = new JLabel("Mилисекунди");
        textField = new JTextField();
        JLabel textLabel = new JLabel("Developed by Alexander, Venelina and Stanislav");
        log = new JTextArea(5, 20);
        log.setFont(new Font("Arial", Font.BOLD, 20));
        loadFileButton.setBounds(50, 20, 250, 100);
        loadFileButton.setFont(new Font("Arial", Font.BOLD, 25));
        openFileButton.setBounds(50, 130, 250, 100);
        openFileButton.setBackground(Color.YELLOW);
        openFileButton.setFont(new Font("Arial", Font.BOLD, 25));
        exitButton.setBounds(50, 240, 250, 100);
        exitButton.setBackground(Color.red);
        exitButton.setFont(new Font("Arial", Font.BOLD, 30));
        textField.setBounds(510, 20, 50, 100);
        textField.setFont(new Font("Arial", Font.BOLD, 40));
        textLabelMSeconds.setBounds(570, 20, 150, 100);
        textLabelMSeconds.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.setBounds(50, 350, 250, 100);
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.setBackground(Color.GREEN);
        fastRadioButton.setBounds(350, 20, 150, 60);
        fastRadioButton.setFont(new Font("Arial", Font.BOLD, 20));
        slowRadioButton.setBounds(350, 60, 150, 60);
        slowRadioButton.setFont(new Font("Arial", Font.BOLD, 20));
        removeTagButton = new JCheckBox("Премахни таговете от текста");
        removeTagButton.setBounds(350, 130, 350, 100);
        removeTagButton.setFont(new Font("Arial", Font.BOLD, 22));
        log.setBounds(350, 350, 350, 100);
        textLabel.setBounds(300, 500, 600, 50);
        textLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(loadFileButton);
        frame.add(openFileButton);
        frame.add(exitButton);
        frame.add(submitButton);
        frame.add(fastRadioButton);
        frame.add(slowRadioButton);
        frame.add(textField);
        frame.add(log);
        frame.add(removeTagButton);
        frame.add(textLabelMSeconds);
        frame.add(textLabel);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(Color.gray);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public JButton getLoadFileButton() {
        return loadFileButton;
    }

    public JButton getOpenFileButton() {
        return openFileButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JRadioButton getFastRadioButton() {

        return fastRadioButton;
    }


    public JRadioButton getSlowRadioButton() {

        return slowRadioButton;
    }

    public JCheckBox getRemoveTagButton() {

        return removeTagButton;
    }

    public JTextField getTextField() {

        return textField;
    }

    public JTextArea getLog() {
        return log;
    }

    public JFileChooser getFileChooser() {

        return fileChooser;
    }

}