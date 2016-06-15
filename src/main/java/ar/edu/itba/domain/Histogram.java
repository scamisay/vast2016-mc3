package ar.edu.itba.domain;

import ar.edu.itba.domain.exceptions.HistogramException;

import java.util.*;

/**
 * Created by scamisay on 14/06/16.
 */
public class Histogram {

    private final List<Double> values;
    private Integer intervalsNumber = 10;

    public Histogram(List<Double> values) {
        if(values == null){
            throw new HistogramException("values cannot be null");
        }
        this.values = values;
        Collections.sort(this.values);

        if(values.size() < intervalsNumber){
            intervalsNumber = values.size();
        }
    }

    public void setIntervalsNumber(Integer intervalsNumber) {
        if(intervalsNumber == null || values.size() < intervalsNumber || intervalsNumber < 0){
            throw new HistogramException("intervalsNumber must between 1 and values.size()");
        }
        this.intervalsNumber = intervalsNumber;
    }

    public Map<Interval, Integer> build(){

        //como la lista esta ordenada puedo asumir los siguiente
        Double min = values.get(0);
        Double max = values.get(values.size()-1);

        List<Interval> intervals = buildIntervals(min, max, intervalsNumber);

        Map<Interval, Integer> histogram = new HashMap<>();

        for(Interval anInterval : intervals){
            histogram.put(anInterval, 0);
            for(Double aValue : values){
                if(anInterval.contains(aValue)){
                    histogram.put(anInterval, histogram.get(anInterval) + 1);
                }
            }
        }

        return histogram;
    }

    private List<Interval> buildIntervals(Double min, Double max, Integer intervalsNumber) {
        Double intervalsOffset = (max - min) / intervalsNumber;  //todo: check remains double

        List<Interval> intervals = new ArrayList<>();
        for(double i = min ; i <= max ; i+= intervalsOffset){
            intervals.add(new Interval(i, i + intervalsOffset));
        }

        return intervals;
    }


    public static void main(String args[]){
        List<Double> values = Arrays.asList(5.2, 8.8, 81.5, 45.5, 1.2, 351.1, 53.5 , 45.55, 89.0,23.2, 42.881,12.201,9.5);
        Histogram hist = new Histogram(values);
        hist.build();
    }
}
