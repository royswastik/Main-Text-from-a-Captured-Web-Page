package com.swastikroy.util;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Swastik on 1/4/2017.
 */
public class MathUtil {

    /**
     * Calculate mean or average
     * @param list
     * @return
     */
    public static Double calculateMean(List<Double> list){
        int size = list.size();
        if(size == 0)return null;
        Double sum = 0d;
        Iterator<Double> it = list.listIterator();
        while(it.hasNext()){
            Double current = it.next();
            sum += current;
        }
        Double mean = sum/size;
        return mean;
    }

    /**
     * Calculate Variance
     * @param list
     * @return
     */
    public static Double calculateVariance(List<Double> list){
        Double mean = calculateMean(list);
        Double varianceSum = 0d;
        int size = list.size();
        Iterator<Double> it = list.listIterator();
        while(it.hasNext()){
            Double current = it.next();
            varianceSum += (current - mean)*(current - mean);
        }
        Double variance = varianceSum/size;
        return variance;
    }

    /**
     * Calculate Standard Deviation
     * @param list
     * @return
     */
    public static Double calculateStandardDeviation(List<Double> list){
        Double variance = calculateVariance(list);
        Double standardDeviation = Math.sqrt(variance);
        return standardDeviation;
    }
}
