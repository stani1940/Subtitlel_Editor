import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class FixingSubtitles extends Gui {
    private static int addition;
    public static String INPUT_FILE = "";
    private static final String OUTPUT_FILE = "fixed.sub";


    FixingSubtitles() {
        Scanner fileInput = null;
        PrintStream fileOutput = null;
        try {
            // Create scanner with the Cyrillic encoding
            fileInput = new Scanner(new File(INPUT_FILE), "windows-1251");
            fileOutput = new PrintStream(OUTPUT_FILE, "windows-1251");
            String line;
            while (fileInput.hasNextLine()) {
                line = fileInput.nextLine();
                String fixedLine = makeSubsFaster(line);
                fileOutput.println(fixedLine);
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

    public static int getMilliseconds() {
        addition = (Integer.parseInt(textField.getText()));
        return addition;
    }

    public static String getFromTime(String line) {
        // Find closing brace
        int bracketFromIndex = line.indexOf('}');
        // Extract 'from' time
        String fromTime = line.substring(1, bracketFromIndex);

        return fromTime;
    }

    public static String getToTime(String line) {
        // Find closing brace
        int bracketFromIndex = line.indexOf('}');
        // Find the following closing brace
        int bracketToIndex = line.indexOf('}', bracketFromIndex + 1);
        // Extract 'to' time
        String toTime = line.substring(bracketFromIndex + 2, bracketToIndex);

        return toTime;
    }

    public static String makeSubsSlower(String line) {
        int milliseconds = getMilliseconds();
        int bracketFromIndex = line.indexOf('}');
        int bracketToIndex = line.indexOf('}', bracketFromIndex + 1);
        String fromTime = getFromTime(line);
        String toTime = getToTime(line);
        // Calculate new 'from' time
        int newFromTime = (((Integer.parseInt(fromTime) / 60) * 1000 + milliseconds) / 1000) * 60;
        // Calculate new 'to' time
        int newToTime = (Integer.parseInt(toTime) / 60) * 1000 + milliseconds;
        // Create a new line using the new 'from' and 'to' times
        String fixedLine = "{" + newFromTime + "}" + "{" + newToTime + "}" + line.substring(bracketToIndex + 1);

        return fixedLine;

    }

    public static String makeSubsFaster(String line) {
        addition = getMilliseconds();
        int bracketFromIndex = line.indexOf('}');
        int bracketToIndex = line.indexOf('}', bracketFromIndex + 1);
        String fromTime = getFromTime(line);
        String toTime = getToTime(line);
        // Calculate new 'from' time
        int newFromTime = (((Integer.parseInt(fromTime) / 60) * 1000 - addition) / 1000) * 60;
        // Calculate new 'to' time
        int newToTime = (Integer.parseInt(toTime) / 60) * 1000 - addition;
        // Create a new line using the new 'from' and 'to' times
        String fixedLine = "{" + newFromTime + "}" + "{" + newToTime + "}" + line.substring(bracketToIndex + 1);

        return fixedLine;
    }


}