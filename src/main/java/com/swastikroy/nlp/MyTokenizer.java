package com.swastikroy.nlp;

import edu.stanford.nlp.simple.Sentence;

import java.util.List;

/**
 * Created by Swastik on 1/6/2017.
 */
public class MyTokenizer {

    public static MyTokenizer myInstance;

    private MyTokenizer(){

    };

    public static MyTokenizer getMyInstance(){
        if(myInstance == null){
            myInstance = new MyTokenizer();
        }
        return myInstance;
    }

    /**
     * Returns list of tokens from a given sentence
     * @param sentence
     * @return
     */
    public List<String> getTokens(String sentence){
        Sentence sent = new Sentence(sentence);
        List<String> words = sent.words();
        return words;
    }
}
