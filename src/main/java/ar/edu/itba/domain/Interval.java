package ar.edu.itba.domain;

import ar.edu.itba.domain.exceptions.InvalidIntervalException;

/**
 * Created by scamisay on 14/06/16.
 */
public class Interval {

    private Double lim1;
    private Double lim2;

    public Interval(Double lim1, Double lim2) {
        if(lim1 == null || lim2 == null || lim1 > lim2){
            throw new InvalidIntervalException();
        }
        this.lim1 = lim1;
        this.lim2 = lim2;
    }

    public boolean contains(Double aValue){
        if(aValue == null){
            return false;
        }
        return lim1 <= aValue && aValue <= lim2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (!lim1.equals(interval.lim1)) return false;
        return lim2.equals(interval.lim2);

    }

    @Override
    public int hashCode() {
        int result = lim1.hashCode();
        result = 31 * result + lim2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("[%.3f, %.3f]", lim1, lim2);
    }
}
