import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;


public class Gui implements ActionListener {

    static JButton loadFileButton;
    JButton openFileButton;
    static JButton exitButton;
    JButton submitButton;
    JRadioButton fastRadioButton,slowRadioButton;
    ButtonGroup buttonGroup;
    JTextField textField;
    JLabel textLabel;
    JFileChooser fileChooser;
    static JTextArea log;

    Gui() {
        JFrame frame = new JFrame("Редактор на субтитри");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadFileButton = new JButton("Зареди файл");
        openFileButton = new JButton("Отвори файл");
        exitButton = new JButton("Изход");
        exitButton = new JButton("Изход");
        submitButton = new JButton("Запиши проблемите");
        fastRadioButton = new JRadioButton("Забързай с");
        slowRadioButton = new JRadioButton("Забави с");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(fastRadioButton);
        buttonGroup.add(loadFileButton);
        textField = new JTextField();
        textLabel =new JLabel();
        log = new JTextArea(5,20);

        loadFileButton.setBounds(80, 30, 200, 50);
        openFileButton.setBounds(80, 90, 200, 50);
        openFileButton.setBackground(Color.YELLOW);
        exitButton.setBounds(80, 150, 200, 50);
        textField.setBounds(350, 100, 50, 50);
        submitButton.setBounds(80, 210, 200, 50);
        fastRadioButton.setBounds(350, 30, 100, 50);
        slowRadioButton.setBounds(350, 60, 100, 50);
        log.setBounds(80,270,200,50);
        frame.add(loadFileButton);
        frame.add(openFileButton);
        frame.add(exitButton);
        frame.add(submitButton);
        frame.add(fastRadioButton);
        frame.add(slowRadioButton);
        frame.add(textField);
        frame.add(log);

        frame.setSize(700, 400);
        frame.setLayout(null);
        frame.setResizable(true);

        frame.setVisible(true);
        submitButton.addActionListener(this);
    }
    public static void setExitButton(){

        exitButton.addActionListener(e -> {
            System.exit(0);
        });
    }
    public static void setLoadFileButton(){
        loadFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    log.append("Зареждам файл: " + selectedFile.getName() + "." );
                } else {
                    log.append("Зареждане на файл отказано от потребителя." );
                }

            }


        });
    }
    public String nameSelectedFile(JFileChooser fileChooser) {
        return fileChooser.getSelectedFile().getName();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = textField.getText();


        if ((!userName.isEmpty()) && (userName != null)) {
            if (userName.trim().equals("admin")) {
                //labelResult.setText(" Вход успешен, " + userName);
            } else {
                // labelResult.setText(" Вход неуспешен ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Моля въведете потребителско име и парола");
        }
    }

    public static void main(String[] args) {


    }
}