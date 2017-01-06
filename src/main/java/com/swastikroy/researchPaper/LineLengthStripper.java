package com.swastikroy.researchPaper;

import com.swastikroy.nlp.MyTokenizer;
import com.swastikroy.util.MathUtil;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Swastik on 1/3/2017.
 */
@Named
public class LineLengthStripper {

    public static LineLengthStripper myInstance;

    private LineLengthStripper(){

    };

    public static LineLengthStripper getMyInstance(){
        if(myInstance == null){
            myInstance = new LineLengthStripper();
        }
        return myInstance;
    }

    public int findMinimumNumberOfWordsAllowedPerLine(double userSuppliedThreshold, List<String> lines){
        List<Double> lineCountList = getLineCountList(lines);
        Double meanWordCount = MathUtil.calculateMean(lineCountList);
        Double standardDeviation = MathUtil.calculateStandardDeviation(lineCountList);
        Double minimumNumberOfWordsAllowedPerLine = (userSuppliedThreshold * standardDeviation);
        return minimumNumberOfWordsAllowedPerLine.intValue();
    }

    public List<String> processLines(List<String> lines, double userSuppliedInput){
        int shortLineThreshold = findMinimumNumberOfWordsAllowedPerLine(userSuppliedInput, lines);
        List<String> response = new ArrayList<String>();
        Iterator<String> it = lines.listIterator();
        while(it.hasNext()){
            String line = it.next().trim();
            int wordCount = getWordCount(line);
            if(wordCount > shortLineThreshold){
                response.add(line);
            }
        }
        return response;
    }

    public List<Double> getLineCountList(List<String> lines){
        List<Double> lineCountList = new ArrayList<Double>();
        Iterator<String> it = lines.listIterator();
        while (it.hasNext()){
            String line = it.next().trim();
            int wordCount = getWordCount(line);
            lineCountList.add((double)wordCount);
        }
        return lineCountList;
    }

    public int getWordCount(String line){
        List<String> tokens =  MyTokenizer.getMyInstance().getTokens(line.trim());
        int wordCount = tokens.size();
        return wordCount;
    }
}
