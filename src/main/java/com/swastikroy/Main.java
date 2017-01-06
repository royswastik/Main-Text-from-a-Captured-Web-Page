package com.swastikroy;

import com.swastikroy.textProcessor.HtmlToTextExtractor;

import java.io.IOException;
import java.util.List;

/**
 * Created by Swastik on 1/6/2017.
 * Main class to run code from terminal
 */
public class Main {
    public static void main(String[] args) throws IOException {

        if(args[0].equals(new String("-help")) || args.length < 3 )
        {
            usage();
            return;
        }
        else{
            String url = args[0];
            Double shortLineThreshold = Double.parseDouble(args[1]);
            Double frequencyThreshold = Double.parseDouble(args[2]);
            List<String> lines = HtmlToTextExtractor.getInstance().extractTextFromHtml(url , shortLineThreshold , frequencyThreshold);
            System.out.println(lines);
        }


    }

    private static void usage() {
        System.out.println("java -cp mainTextFromCapturedWebPage.jar com.swastikroy.Main \"Url of the web page\" ... \"/short line threshold value\"  ... \"/frequency threshold value\"");
    }
}
