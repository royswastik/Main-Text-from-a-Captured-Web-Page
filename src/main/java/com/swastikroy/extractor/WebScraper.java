package com.swastikroy.extractor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Named;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ImmediateRefreshHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Scraper class to extract content of any webpage using HTMLUnit
 * @author Swastik
 *
 */
@Named
public class WebScraper {
	public HtmlPage getPage(String url) throws FailingHttpStatusCodeException, IOException, MalformedURLException{
	    WebClient webClient = new WebClient(BrowserVersion.CHROME);
	//    webClient.getOptions().setCssEnabled(false);//if you don't need css
	//    webClient.getOptions().setThrowExceptionOnScriptError(false);
	    
	    webClient.setRefreshHandler(new ImmediateRefreshHandler());
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setPrintContentOnFailingStatusCode(false);
		webClient.getOptions().setTimeout(1000000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPopupBlockerEnabled(false);
        webClient.setCssErrorHandler(new SilentCssErrorHandler());
        webClient.getOptions().setUseInsecureSSL(true);
	    WebRequest wrequest = new WebRequest(new URL(url));
	    HtmlPage page = webClient.getPage(wrequest);
	    webClient.closeAllWindows();
	    return page;
	}
	
	
	public HtmlPage getPageMaxHeight(String url) throws FailingHttpStatusCodeException, IOException, MalformedURLException{
	    WebClient webClient = new WebClient(BrowserVersion.CHROME);
	//    webClient.getOptions().setCssEnabled(false);//if you don't need css
	//    webClient.getOptions().setThrowExceptionOnScriptError(false);
	//    webClient.getCurrentWindow().setInnerHeight(Integer.MAX_VALUE);
	    webClient.getCurrentWindow().setInnerHeight(60000);
	    webClient.getCurrentWindow().setOuterWidth(60000);
	    webClient.setRefreshHandler(new ImmediateRefreshHandler());
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setPrintContentOnFailingStatusCode(true);
		webClient.getOptions().setTimeout(1000000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPopupBlockerEnabled(false);
        webClient.setCssErrorHandler(new SilentCssErrorHandler());
	    WebRequest wrequest = new WebRequest(new URL(url));
	    HtmlPage page = webClient.getPage(wrequest);
	    webClient.closeAllWindows();
	    return page;
	}
	public String getPageAsText(String url) throws FailingHttpStatusCodeException, IOException{
		return getPage(url).asText();
	}
	public String getPageAsXML(String url) throws FailingHttpStatusCodeException, IOException{
		return getPage(url).asXml();
	}
	public String getPageAsMaxHeightText(String url) throws FailingHttpStatusCodeException, IOException{
		return getPageMaxHeight(url).asText();
	}
	public String getPageAsMaxHeightXML(String url) throws FailingHttpStatusCodeException, IOException{
		return getPageMaxHeight(url).asXml();
	}
}
