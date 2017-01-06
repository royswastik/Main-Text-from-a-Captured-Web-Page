package com.swastikroy.textProcessor;

import com.swastikroy.extractor.MyBoilerpipeExtractor;
import com.swastikroy.nlp.SentenceSplitter;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.*;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;

import javax.inject.Inject;
import javax.inject.Named;
import java.net.URL;
import java.util.List;

/**
 * Created by Swastik on 1/6/2017.
 */
@Named
public class HtmlTagStripper {

    @Inject
    MyBoilerpipeExtractor myBoilerpipeExtractor;


    public String getText(String url){
        try {
            final HTMLDocument htmlDoc = HTMLFetcher.fetch(new URL(url));
            final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
            final KeepEverythingExtractor extractor = CommonExtractors.KEEP_EVERYTHING_EXTRACTOR;
            String everything = extractor.getText(doc);
            return everything;
        } catch (Exception e) {
            return "";
        }
    }

    public List<String> getLines(String url){
        String allText = getText(url);
        List<String> lines = SentenceSplitter.getInstance().splitSent(allText);
        return lines;
    }
}
