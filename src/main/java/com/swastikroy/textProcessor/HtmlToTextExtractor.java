package com.swastikroy.textProcessor;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

/**
 * Created by Swastik on 1/6/2017.
 */

@Named
public class HtmlToTextExtractor {

    @Inject
    HtmlTagStripper htmlTagStripper;

    public List<String> extractTextFromHtml(String url, double userLineLengthInput, double userFrequencyInput) throws IOException {
        List<String> lines = htmlTagStripper.getLines(url);

        lines = LineLengthStripper.getMyInstance().processLines(lines, userLineLengthInput);

        lines = LineFrequencyStripper.getMyInstance().processLines(lines, userFrequencyInput);

        lines = LineCharacterStripper.getMyInstance().processLines(lines);

        return lines;
    }


}
