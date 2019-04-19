package Sachko.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class SrtSubtitleLogic {
    private static int ADDITION = 2;
    private static final String INPUT_FILE = "src/Sachko/SRTtitles.srt";
    private static final String OUTPUT_FILE = "src/Sachko/fixed3.srt";

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


    public static String fixLine(String string) {
        int indexOfArrow = string.indexOf('-');
        String leftMilSecond = string.substring(indexOfArrow - 4, indexOfArrow - 1);
        String rightMilSecond = string.substring(string.length() - 3, string.length());
        String BleftMilSecond = string.substring(indexOfArrow - 7, indexOfArrow - 5);
        String BrightMilSecond = string.substring(string.length() - 6, string.length() - 4);
        String leftHours = string.substring(indexOfArrow - 10, indexOfArrow - 8);
        String rightHours = string.substring(string.length() - 9, string.length() - 7);
        int BLeftMilSeconds = Integer.parseInt(BleftMilSecond);
        int BRightMilSeconds = Integer.parseInt(BrightMilSecond);
        int LeftMilSeconds = Integer.parseInt(leftMilSecond);
        int RightMilSeconds = Integer.parseInt(rightMilSecond);
        if (LeftMilSeconds + ADDITION < 1000 && RightMilSeconds + ADDITION < 1000) {
            LeftMilSeconds = LeftMilSeconds + ADDITION;
            RightMilSeconds = RightMilSeconds + ADDITION;
        } else {
            if (LeftMilSeconds + ADDITION >= 1000 && RightMilSeconds + ADDITION <= 1000) {
                RightMilSeconds = RightMilSeconds + ADDITION;
                LeftMilSeconds = LeftMilSeconds + ADDITION;
                String BLeft = Integer.toString(LeftMilSeconds);
                LeftMilSeconds = Integer.parseInt(BLeft.replaceFirst(BLeft.substring(0, 1), ""));
                BLeftMilSeconds += Integer.parseInt(BLeft.substring(0, 1));
            } else if (RightMilSeconds + ADDITION >= 1000 && LeftMilSeconds + ADDITION <= 1000) {
                LeftMilSeconds = LeftMilSeconds + ADDITION;
                RightMilSeconds = RightMilSeconds + ADDITION;
                String BRight = Integer.toString(RightMilSeconds);
                RightMilSeconds = Integer.parseInt(BRight.replaceFirst(BRight.substring(0, 1), ""));
                BRightMilSeconds += Integer.parseInt(BRight.substring(0, 1));
            } else {
                RightMilSeconds = RightMilSeconds + ADDITION;
                LeftMilSeconds = LeftMilSeconds + ADDITION;
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
        } else {
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
   }

