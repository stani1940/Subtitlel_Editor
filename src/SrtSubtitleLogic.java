import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class SrtSubtitleLogic extends Gui {
    public  int ADDITION;
    public   String INPUT_FILE = "";
    private  final String OUTPUT_FILE = "fixed.srt";

    SrtSubtitleLogic() {
        Scanner fileInput = null;
        PrintStream fileOutput = null;
        try {
            // Create scanner with the Cyrillic encoding
            fileInput = new Scanner(new File(INPUT_FILE), "windows-1251");

            fileOutput = new PrintStream(OUTPUT_FILE, "windows-1251");
            String line;
            while (fileInput.hasNextLine()) {
                line = fileInput.nextLine();
                if (line.contains(" --> ")) {
                    String fixedLine = fixLine(line);
                    fileOutput.println(fixedLine);
                } else {
                    fileOutput.println(line);
                }

            }
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        } catch (UnsupportedEncodingException uee) {
            System.err.println(uee.getMessage());
        } finally {
            if (null != fileInput) {
                fileInput.close();
            }
            if (null != fileOutput) {
                fileOutput.close();
            }
        }
    }


    public  String fixLine(String string) {
        int indexOfArrow = string.indexOf('-');
        String leftMilSecond = string.substring(indexOfArrow - 4, indexOfArrow - 1);
        String rightMilSecond = string.substring(string.length() - 3, string.length());
        int LeftMilSeconds = Integer.parseInt(leftMilSecond);
        int RightMilSeconds = Integer.parseInt(rightMilSecond);
        LeftMilSeconds = LeftMilSeconds + ADDITION;
        RightMilSeconds = RightMilSeconds + ADDITION;
        String newLeftSeconds = Integer.toString(LeftMilSeconds);
        String newRightSeconds = Integer.toString(RightMilSeconds);
        string = string.replace(leftMilSecond, newLeftSeconds);
        string = string.replace(rightMilSecond, newRightSeconds);
        return string;
    }
}

