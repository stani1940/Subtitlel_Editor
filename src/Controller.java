import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void initController() {
        setExitButton();
        setLoadFileButton();
        setOpenFileButton();
        setSubmitButton();
        setRemoveTagButton();
    }

    private void setExitButton() {
        view.getExitButton().addActionListener(e -> System.exit(0));
    }

    private void setLoadFileButton() {
        view.getLoadFileButton().addActionListener(e -> showOpenFileDialog());
    }

    private void showOpenFileDialog() {
        view.getFileChooser().setCurrentDirectory(new File(("src")));
        view.getFileChooser().setFileSelectionMode(JFileChooser.FILES_ONLY);
        view.getFileChooser().addChoosableFileFilter(new FileNameExtensionFilter("Subtitle Documents with srt and sub", "sub", "srt"));
        view.getFileChooser().setAcceptAllFileFilterUsed(true);
        int result = view.getFileChooser().showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = view.getFileChooser().getSelectedFile();
            view.getLog().append("Зареждам файл: " + selectedFile.getName() + ".");
        } else {
            view.getLog().append("Зареждане на файл отказано от потребителя.");
        }
    }

    private String getFileExtension(JFileChooser fileChooser) {
        String[] split = fileChooser.getSelectedFile().getName().split("\\.");
        return split[split.length - 1];
    }

    private void openFile() {
        if (getFileExtension(view.getFileChooser()).equals("sub")) {
            File file = new File("fixed.sub");
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File file = new File("fixed.srt");
            try {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void setOpenFileButton() {
        view.getOpenFileButton().addActionListener(e -> openFile());
    }

    public void setSubmitButton() {
        view.getSubmitButton().addActionListener(e -> editLoadedFile());
    }

    public void setRemoveTagButton() {
        view.getRemoveTagButton().addActionListener(e -> removeTagsFromLoadedFile());
    }

    private void removeTagsFromLoadedFile() {
        if (view.getRemoveTagButton().isSelected()) {
            model.setInputFile(String.valueOf(view.getFileChooser().getSelectedFile()));
            if (getFileExtension(view.getFileChooser()).equals("sub")) {
                model.setOutputFile("fixed.sub");
                model.removeTagsFromFile();
            }
            if (getFileExtension(view.getFileChooser()).equals("srt")) {
                model.setOutputFile("fixed.srt");
                model.removeTagsFromFile();
            }

        }
    }

    private void editLoadedFile() {

        String textMs = view.getTextField().getText();
        validateChooseRadioButtons();
        if (!textMs.isEmpty()) {
            if (getRadioButtonsValue()) {
                int mSeconds = Integer.parseInt(textMs);
                setProperties(mSeconds);
            }
            if (!getRadioButtonsValue()) {
                int mSeconds = -Integer.parseInt(textMs);
                setProperties(mSeconds);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Моля въведете число за милисекунди");
        }
    }

    private void setProperties(int mSeconds) {
        model.setAddition(mSeconds);
        model.setInputFile(String.valueOf(view.getFileChooser().getSelectedFile()));
        if (getFileExtension(view.getFileChooser()).equals("sub")) {
            model.setOutputFile("fixed.sub");
            model.fixFilesWithSubExtension();
        }
        if (getFileExtension(view.getFileChooser()).equals("srt")) {
            model.setOutputFile("fixed.srt");
            model.fixFileWithSrtExtension();
        }
    }

    private void validateChooseRadioButtons() {
        if (!view.getFastRadioButton().isSelected() && !view.getSlowRadioButton().isSelected()) {
            JOptionPane.showMessageDialog(null, "Моля изберете опция за забързване или забавяне");
        }
    }

    private boolean getRadioButtonsValue() {
        return view.getSlowRadioButton().isSelected();
    }
}
