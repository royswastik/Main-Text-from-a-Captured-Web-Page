package com.swastikroy.util;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Swastik on 1/4/2017.
 */
public class WordNetUtil {
    private static WordNetUtil instanceWordNetUtil;
    private static String wnhome = "E:\\Install\\Program Files (x86)\\WordNet\\2.1";
    private static IDictionary dict;

    private WordNetUtil(){

    };

    public static WordNetUtil getInstance() throws IOException {
        if(instanceWordNetUtil == null){
            instanceWordNetUtil = new WordNetUtil();
            String path = wnhome + File.separator + "dict";
            URL url = null;
            try{ url = new URL("file", null, path); }
            catch(MalformedURLException e){ e.printStackTrace(); }

            // construct the dictionary object and open it
            dict = new Dictionary(url);
            dict.open();
        }
        return instanceWordNetUtil;
    }

    public int getWordFrequencyCount(String wordText, POS pos){
        IIndexWord idxWord = dict.getIndexWord(wordText, pos);
        if(idxWord == null){
            return 0;
        }
        IWordID wordID = idxWord.getWordIDs().get(0);
        IWord word = dict.getWord(wordID);
        int count = dict.getSenseEntry(word.getSenseKey()).getTagCount();
        return count;
    }

    public int getTotalWordsFrequencyCount(List<String> tokens, List<String> posTags){
        int totalFrequencyScore = 0;
        for(int i =0; i < tokens.size() ; i++){
            POS pos = getPOSFromTag(posTags.get(i));
            if(pos != null){
                int frequencyCount = getWordFrequencyCount(tokens.get(i), pos);
                totalFrequencyScore += frequencyCount;
            }
        }
        return totalFrequencyScore;
    }

    public POS getPOSFromTag(String pos){
        if(pos.startsWith("NN")){
            return POS.NOUN;
        }
        else if(pos.startsWith("VB")){
            return POS.VERB;
        }
        else if(pos.startsWith("JJ")){
            return POS.ADJECTIVE;
        }
        else  if(pos.startsWith("RB")){
            return POS.ADJECTIVE;
        }
        else {
            return null;
        }
    }

}
