import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class Model {


    public int addition;
    public String inputFile = "";
    public String outputFile = "";

    public Model() {

    }

    public void setAddition(int addition) {

        this.addition = addition;
    }

    public void setInputFile(String inputFile) {

        this.inputFile = inputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    void fixFilesWithSubExtension() {
        Scanner fileInput = null;
        PrintStream fileOutput = null;
        try {
            // Create scanner with the Cyrillic encoding
            fileInput = new Scanner(new File(inputFile), "windows-1251");
            fileOutput = new PrintStream(outputFile, "windows-1251");
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

    private String fixLine(String line) {
        // Find closing brace
        int bracketFromIndex = line.indexOf('}');
        // Extract 'from' time
        String fromTime = line.substring(1, bracketFromIndex);
        // Calculate new 'from' time
        int newFromTime = (((Integer.parseInt(fromTime) / 60) * 1000 + addition) / 1000) * 60;
        System.out.println(addition);
        // Find the following closing brace
        int bracketToIndex = line.indexOf('}', bracketFromIndex + 1);

        // Extract 'to' time
        String toTime = line.substring(bracketFromIndex + 2, bracketToIndex);

        // Calculate new 'to' time
        int newToTime = (Integer.parseInt(toTime) / 60) * 1000 + addition;
        // Create a new line using the new 'from' and 'to' times
        return "{" + newFromTime + "}" + "{" + newToTime + "}" + line.substring(bracketToIndex + 1);
    }

    void fixFileWithSrtExtension() {
        Scanner fileInput = null;
        PrintStream fileOutput = null;
        try {
            // Create scanner with the Cyrillic encoding
            fileInput = new Scanner(new File(inputFile), "windows-1251");

            fileOutput = new PrintStream(outputFile, "windows-1251");
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

    private String fixSrtLine(String string) {
        int indexOfArrow = string.indexOf('-');
        String leftMilSecond = string.substring(indexOfArrow - 4, indexOfArrow - 1);
        String rightMilSecond = string.substring(string.length() - 3, string.length());
        String BleftMilSecond = string.substring(indexOfArrow - 7, indexOfArrow - 5);
        String BrightMilSecond = string.substring(string.length() - 6, string.length() - 4);
        int BLeftMilSeconds = Integer.parseInt(BleftMilSecond);
        int BRightMilSeconds = Integer.parseInt(BrightMilSecond);
        int LeftMilSeconds = Integer.parseInt(leftMilSecond);
        int RightMilSeconds = Integer.parseInt(rightMilSecond);
        if (LeftMilSeconds + addition < 1000 && RightMilSeconds + addition < 1000) {
            LeftMilSeconds = LeftMilSeconds + addition;
            RightMilSeconds = RightMilSeconds + addition;
        } else {
            if (LeftMilSeconds + addition >= 1000 && RightMilSeconds + addition <= 1000) {
                RightMilSeconds = RightMilSeconds + addition;
                LeftMilSeconds = LeftMilSeconds + addition;
                String BLeft = Integer.toString(LeftMilSeconds);
                LeftMilSeconds = Integer.parseInt(BLeft.replaceFirst(BLeft.substring(0, 1), ""));
                BLeftMilSeconds += Integer.parseInt(BLeft.substring(0, 1));
            } else if (RightMilSeconds + addition >= 1000 && LeftMilSeconds + addition <= 1000) {
                LeftMilSeconds = LeftMilSeconds + addition;
                RightMilSeconds = RightMilSeconds + addition;
                String BRight = Integer.toString(RightMilSeconds);
                RightMilSeconds = Integer.parseInt(BRight.replaceFirst(BRight.substring(0, 1), ""));
                BRightMilSeconds += Integer.parseInt(BRight.substring(0, 1));
            } else {
                RightMilSeconds = RightMilSeconds + addition;
                LeftMilSeconds = LeftMilSeconds + addition;
                String BLeft = Integer.toString(LeftMilSeconds);
                LeftMilSeconds = Integer.parseInt(BLeft.replaceFirst(BLeft.substring(0, 1), ""));
                BLeftMilSeconds += Integer.parseInt(BLeft.substring(0, 1));
                String BRight = Integer.toString(RightMilSeconds);
                RightMilSeconds = Integer.parseInt(BRight.replaceFirst(BRight.substring(0, 1), ""));
                BRightMilSeconds += Integer.parseInt(BRight.substring(0, 1));
            }
        }
        String newLeftSeconds = Integer.toString(LeftMilSeconds);
        String newRightSeconds = Integer.toString(RightMilSeconds);
        String newBLeftSeconds = Integer.toString(BLeftMilSeconds);
        String newBRightSeconds = Integer.toString(BRightMilSeconds);
        if (newLeftSeconds.length() == 1) {
            newLeftSeconds = "00" + newLeftSeconds;
        } else if (newLeftSeconds.length() == 2) {
            newLeftSeconds = "0" + newLeftSeconds;
        }
        if (newRightSeconds.length() == 1) {
            newRightSeconds = "00" + newRightSeconds;
        } else if (newRightSeconds.length() == 2) {
            newRightSeconds = "0" + newRightSeconds;
        } else {
        }
        if (newBLeftSeconds.length() == 1) {
            newBLeftSeconds = "0" + newBLeftSeconds;
        }
        if (newBRightSeconds.length() == 1) {
            newBRightSeconds = "0" + newBRightSeconds;
        }

        string = string.replace(leftMilSecond, newLeftSeconds);
        string = string.replace(BleftMilSecond, newBLeftSeconds);
        string = string.replace(BrightMilSecond, newBRightSeconds);
        string = string.replace(rightMilSecond, newRightSeconds);
        return string;
    }

    public void removeTagsFromFile() {
        Scanner fileInput = null;
        PrintStream fileOutput = null;

        try {
            // Create scanner with the Cyrillic encoding
            fileInput = new Scanner(new File(inputFile), "windows-1251");
            fileOutput = new PrintStream(outputFile, "windows-1251");
            String line;
            while (fileInput.hasNextLine()) {
                line = fileInput.nextLine();
                String fixedLine = line.replaceAll("<.*?>", "");
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
}