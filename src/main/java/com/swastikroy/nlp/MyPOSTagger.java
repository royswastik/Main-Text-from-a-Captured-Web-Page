package com.swastikroy.nlp;

import edu.stanford.nlp.simple.Sentence;

import java.util.List;

/**
 * Created by Swastik on 1/6/2017.
 */
public class MyPOSTagger {
    public static MyPOSTagger myInstance;

    private MyPOSTagger(){

    };

    public static MyPOSTagger getMyInstance(){
        if(myInstance == null){
            myInstance = new MyPOSTagger();
        }
        return myInstance;
    }

    public List<String> getPOS(String sentence){
        Sentence sent = new Sentence(sentence);
        List<String> words = sent.posTags();
        return words;
    }
}
