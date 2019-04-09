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

    private static String fixLine(String line) {
        //TODO sloji logikata ot Testoviq fail

        return "";
    }
    private static String removeTags(String line){
        if (line.contains("<i>")) {
            String tempWord = "<i>" + " ";
            line = line.replaceAll(tempWord, "");
            tempWord = " " + "<i>";
            line= line.replaceAll(tempWord, "");
        }
        if (line.contains("</i>")) {
            String tempWord = "</i>" + " ";
            line = line.replaceAll(tempWord, "");
            tempWord = " " + "</i>";
             line= line.replaceAll(tempWord, "");
        }
        return line;
    }
}
