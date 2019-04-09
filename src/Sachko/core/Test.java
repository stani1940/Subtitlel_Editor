package Sachko.core;

public class Test {
    public static final int ADDITIONAL_MS = 2;

    public static void main(String[] args) {
        String SubtitleText = "1\n" +
                "00:00:26,469 --> 00:00:29,907\n" +
                "<i>Тук е бежански кораб от Асгард.</i>\n";

        String SubtitleText2 = "1251\n" +
                "02:28:23,400 --> 02:28:25,400\n" +
                "Ник!";
        fixLine(SubtitleText2);
    }

    public static void fixLine(String string) {
        int index = string.indexOf('0'); //index of changes
        int indexForStartOfSubstring = index + 9;
        int indexForEndOfSubstring = index + 12;
        System.out.println("Index of 0 " + index);
        String string2 = string.substring(indexForStartOfSubstring, indexForEndOfSubstring);
        int number = Integer.parseInt(string2);
        System.out.println("THe ms are " + number);
        //TODO Ostana da opravq i namiraneto to dqsnata strana
    }
}
