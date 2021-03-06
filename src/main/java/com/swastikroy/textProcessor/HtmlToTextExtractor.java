package com.swastikroy.textProcessor;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

/**
 * Created by Swastik on 1/6/2017.
 * Class to extract main text from captured web page
 */

@Named
public class HtmlToTextExtractor {

    private static HtmlToTextExtractor myInstance;

    public static HtmlToTextExtractor getInstance(){
        if(myInstance == null)
            myInstance = new HtmlToTextExtractor();

        return myInstance;
    }

    private HtmlToTextExtractor(){

    }

    /**
     * Extract Main Text
     * @param url
     * @param userLineLengthInput
     * @param userFrequencyInput
     * @return
     * @throws IOException
     */
    public List<String> extractTextFromHtml(String url, double userLineLengthInput, double userFrequencyInput) throws IOException {
        List<String> lines = HtmlTagStripper.getLines(url);

        lines = LineLengthStripper.getMyInstance().processLines(lines, userLineLengthInput);

        lines = LineFrequencyStripper.getMyInstance().processLines(lines, userFrequencyInput);

        lines = LineCharacterStripper.getMyInstance().processLines(lines);

        return lines;
    }

}
