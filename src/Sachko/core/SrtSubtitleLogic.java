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


        public static String fixLine (String string){
            int index = string.indexOf('0'); //index of changes
            int indexForStartOfSubstringLeft = index + 9;
            int indexForEndOfSubstringLeft = index + 12;
            String string2 = string.substring(indexForStartOfSubstringLeft, indexForEndOfSubstringLeft);
            int leftSidedSeconds = Integer.parseInt(string2);
            int indexForStartOfSubstringRight = index + 26;
            int indexForEndOfSubstringRight = index + 29;
            String string3 = string.substring(indexForStartOfSubstringRight, indexForEndOfSubstringRight);
            int rightSidedSeconds = Integer.parseInt(string3);
            String oldLeftSidedSecondsString = Integer.toString(leftSidedSeconds);
            String oldRightSidedSecondsString = Integer.toString(rightSidedSeconds);
            leftSidedSeconds = leftSidedSeconds + ADDITION;
            rightSidedSeconds = rightSidedSeconds + ADDITION;
            String newLeftSidedSecondsString = Integer.toString(leftSidedSeconds);
            String newRightSidedSecondsString = Integer.toString(rightSidedSeconds);
            string = string.replace(oldLeftSidedSecondsString, newLeftSidedSecondsString);
            string = string.replace(oldRightSidedSecondsString, newRightSidedSecondsString);
            return string;
        }
    }
