package com.swastikroy.nlp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

/**
 * This class is used to split two sentences
 */
@Named
public class SentenceSplitter {

	private static SentenceSplitter sentSplitter;
	private SentenceDetectorME sentenceDetector = null;
	

	public static SentenceSplitter getInstance() {
		if (sentSplitter == null) {
			sentSplitter = new SentenceSplitter();
		}
		return sentSplitter;
	}

	private SentenceSplitter() {
	}

	public synchronized List<String> splitSent(String txt) {
		Document doc = new Document(txt);
		List<String> response = new ArrayList<String>();
		for (Sentence sent : doc.sentences()) {  // Will iterate over two sentences
			response.add(sent.text());
		}
		return response;

	}
}
