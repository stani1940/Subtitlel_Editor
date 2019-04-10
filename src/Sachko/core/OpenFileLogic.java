package Sachko.core;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenFileLogic {


    public static void main(String[] args) throws IOException {
        //text file, should be opening in default text editor
        File file = new File("src/Sachko/SRTtitles.srt");

        //first check if Desktop is supported by Platform or not
        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop is not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) desktop.open(file);

    }

}

