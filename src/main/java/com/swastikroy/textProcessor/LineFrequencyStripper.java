package com.swastikroy.textProcessor;

import com.swastikroy.nlp.MyPOSTagger;
import com.swastikroy.nlp.MyTokenizer;
import com.swastikroy.util.MathUtil;
import com.swastikroy.util.WordNetUtil;

import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Swastik on 1/4/2017.
 */
@Named
public class LineFrequencyStripper  {

    public static LineFrequencyStripper myInstance;

    private LineFrequencyStripper(){

    };

    public static LineFrequencyStripper getMyInstance(){
        if(myInstance == null){
            myInstance = new LineFrequencyStripper();
        }
        return myInstance;
    }

    /**
     * Finds minimum frequency score allowed per line
     * @param userSuppliedThreshold
     * @param lines
     * @return
     * @throws IOException
     */
    public int findMinimumFrequenceScoreAllowedPerLine(double userSuppliedThreshold, List<String> lines) throws IOException {
        List<Double> lineFrequencyScoreList = getLineFrequencyScoreList(lines);
        Double standardDeviation = MathUtil.calculateStandardDeviation(lineFrequencyScoreList);
        Double minimumFrequencyScoreAllowedPerLine = (userSuppliedThreshold * standardDeviation);
        return minimumFrequencyScoreAllowedPerLine.intValue();
    }

    /**
     * Finds frequency score for each line in a list
     * @param lines
     * @return
     * @throws IOException
     */
    public List<Double> getLineFrequencyScoreList(List<String> lines) throws IOException {
        List<Double> lineFrequencyScoreList = new ArrayList<Double>();
        Iterator<String> it = lines.listIterator();
        while (it.hasNext()){
            String line = it.next();
            int totalLineFrequencyScore =  getFrequencyScore(line);
            lineFrequencyScoreList.add((double)totalLineFrequencyScore);
        }
        return lineFrequencyScoreList;
    }

    /**
     * Eliminates lines which fall under frequency threshold value
     * @param lines
     * @param userSuppliedInput
     * @return
     * @throws IOException
     */
    public List<String> processLines(List<String> lines, double userSuppliedInput) throws IOException {
        int frequencyThreshold = findMinimumFrequenceScoreAllowedPerLine(userSuppliedInput, lines);
        List<String> response = new ArrayList<String>();
        Iterator<String> it = lines.listIterator();
        while(it.hasNext()){
            String line = it.next().trim();
            int frequencyScore = getFrequencyScore(line);
            if(frequencyScore > frequencyThreshold){
                response.add(line);
            }
        }
        return response;
    }

    /**
     * Get frequency score for a line, which is sum of frequency score of its words
     * @param line
     * @return
     * @throws IOException
     */
    public int getFrequencyScore(String line) throws IOException {
        List<String> tokens = MyTokenizer.getMyInstance().getTokens(line);
        List<String> posTags = MyPOSTagger.getMyInstance().getPOS(line);
        return WordNetUtil.getInstance().getTotalWordsFrequencyCount(tokens, posTags);
    }
}
