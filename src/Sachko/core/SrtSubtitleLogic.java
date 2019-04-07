package Sachko.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class SrtSubtitleLogic {
    private static final int ADDITION = 2;
    private static final String INPUT_FILE = "src/Sachko/SRTtitles.srt";
    private static final String OUTPUT_FILE = "src/Sachko/fixed.srt";

    public static void main(String[] args) {
        Scanner fileInput = null;
        PrintStream fileOutput = null;
        try {
            // Create scanner with the Cyrillic encoding
            fileInput = new Scanner(new File(INPUT_FILE), "windows-1251");

            fileOutput = new PrintStream(OUTPUT_FILE, "windows-1251");
            String line;
            while (fileInput.hasNextLine()) {
                line = fileInput.nextLine();
                String fixedLine = fixLine(line);
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

    private static String fixLine(String line) { //TODO Trqbva tuk da se sloji logikata
        // Find closing brace
        int bracketFromIndex = line.indexOf('}');
        // Extract 'from' time
        String fromTime = line.substring(1,bracketFromIndex);
        // Calculate new 'from' time
        int newFromTime = (((Integer.parseInt(fromTime)/60)*1000 + ADDITION)/1000)*60;
        System.out.println(newFromTime);
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
}
