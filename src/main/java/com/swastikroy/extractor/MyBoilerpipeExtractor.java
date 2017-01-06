package com.swastikroy.extractor;

import java.net.URL;
import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.inject.Named;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.swastikroy.model.ArticleContent;

import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.extractors.CanolaExtractor;
import de.l3s.boilerpipe.extractors.CommonExtractors;
import de.l3s.boilerpipe.extractors.KeepEverythingExtractor;
import de.l3s.boilerpipe.extractors.NumWordsRulesExtractor;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;

/**
 * Boilerpipe extractor to extract relevant information 
 * @author Swastik
 *
 */
@Named
public class MyBoilerpipeExtractor {
	
	@Inject
	WebScraper webScraper;
	
	public ArticleContent extract(String url){
		try {
            final HTMLDocument htmlDoc = HTMLFetcher.fetch(new URL(url));
            final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
            String title = doc.getTitle();
 
            String content = ArticleExtractor.INSTANCE.getText(doc);
           
            final KeepEverythingExtractor extractor = CommonExtractors.KEEP_EVERYTHING_EXTRACTOR;
            String desc = NumWordsRulesExtractor.INSTANCE.getText(doc); 
            String everything = CanolaExtractor.INSTANCE.getText(doc);
            ArticleContent article = new ArticleContent(title, content, null);
            article.setEverything(everything);
            return article;
        } catch (Exception e) {
            return null;
        }
	}
	
	public ArticleContent extract(HtmlPage htmlPage){
		try {
			String rawHtmlString = htmlPage.asXml();
			final HTMLDocument htmlDoc = new HTMLDocument(rawHtmlString.getBytes(),Charset.defaultCharset());
       //     final HTMLDocument htmlDoc = HTMLFetcher.fetch(new URL(url));
            final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
            String title = doc.getTitle();
 
            String content = ArticleExtractor.INSTANCE.getText(doc);
           
            final KeepEverythingExtractor extractor = CommonExtractors.KEEP_EVERYTHING_EXTRACTOR;
            String desc = NumWordsRulesExtractor.INSTANCE.getText(doc); 
            String everything = CanolaExtractor.INSTANCE.getText(doc);
            ArticleContent article = new ArticleContent(title, content, null);
            article.setEverything(everything);
            return article;
        } catch (Exception e) {
            return null;
        }
	}
}
