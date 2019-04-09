package Sachko.core;

public class Test {
    public static final int ADDITIONAL_MS = 2;

    public static void main(String[] args) {
        String SubtitleText ="00:00:26,469 --> 00:00:29,907";


        System.out.println(fixLine(SubtitleText));
    }

    public static String fixLine(String string) {
        int indexOfArrow = string.indexOf('-');
        String leftMilSecond = string.substring(indexOfArrow - 4, indexOfArrow - 1);
        String rightMilSecond = string.substring(string.length() - 3, string.length());
        int LeftMilSeconds = Integer.parseInt(leftMilSecond);
        int RightMilSeconds = Integer.parseInt(rightMilSecond);
        LeftMilSeconds = LeftMilSeconds + ADDITIONAL_MS;
        RightMilSeconds = RightMilSeconds + ADDITIONAL_MS;
        String newLeftSeconds = Integer.toString(LeftMilSeconds);
        String newRightSeconds = Integer.toString(RightMilSeconds);
        string = string.replace(leftMilSecond, newLeftSeconds);
        string = string.replace(rightMilSecond, newRightSeconds);
        return string;
    }
}