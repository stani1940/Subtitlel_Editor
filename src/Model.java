import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Model {


    public int ADDITION;
    public    String INPUT_FILE = "";
    public   String OUTPUT_FILE = "";

    public Model() {
        this.ADDITION=ADDITION;
        this.INPUT_FILE =INPUT_FILE;
        this.OUTPUT_FILE=OUTPUT_FILE;
    }

    public int getADDITION() {
        return ADDITION;
    }

    public String getOUTPUT_FILE() {
        return OUTPUT_FILE;
    }

    public void setADDITION(int ADDITION) {

        this.ADDITION = ADDITION;
    }


    public void setINPUT_FILE(String INPUT_FILE) {

        this.INPUT_FILE = INPUT_FILE;
    }

    public void setOUTPUT_FILE(String OUTPUT_FILE) {
        this.OUTPUT_FILE = OUTPUT_FILE;
    }

    void FixSubtitles() {
        Scanner fileInput = null;
        PrintStream fileOutput = null;
        try {
            // Create scanner with the Cyrillic encoding
            fileInput = new Scanner(new File(INPUT_FILE), "windows-1251");
            System.out.println(INPUT_FILE);
            fileOutput = new PrintStream(OUTPUT_FILE, "windows-1251");
            String line;
            while (fileInput.hasNextLine()) {
                line = fileInput.nextLine();
                String fixedLine = fixLine(line);
                fileOutput.println(fixedLine);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException fnfe) {
            System.err.println(fnfe.getMessage());
        } finally {
            if (null != fileInput) {
                fileInput.close();
            }
            if (null != fileOutput) {
                fileOutput.close();
            }
        }

    }
    private  String fixLine(String line) {
        // Find closing brace
        int bracketFromIndex = line.indexOf('}');
        // Extract 'from' time
        String fromTime = line.substring(1,bracketFromIndex);
        // Calculate new 'from' time
        int newFromTime = (((Integer.parseInt(fromTime)/60)*1000 + ADDITION)/1000)*60;
        System.out.println(ADDITION);
        // Find the following closing brace
        int bracketToIndex = line.indexOf('}', bracketFromIndex + 1);

        // Extract 'to' time
        String toTime = line.substring(bracketFromIndex + 2, bracketToIndex);

        // Calculate new 'to' time
        int newToTime = (Integer.parseInt(toTime)/60)*1000+ADDITION;
        // Create a new line using the new 'from' and 'to' times
        String fixedLine = "{" + newFromTime + "}" + "{" + newToTime + "}" + line.substring(bracketToIndex + 1);
        return fixedLine;
    }
    void srtSubtitleLogic() {
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
                    String fixedLine = fixSrtLine(line);
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

    private   String fixSrtLine(String string) {
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