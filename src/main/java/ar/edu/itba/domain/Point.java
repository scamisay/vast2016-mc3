package ar.edu.itba.domain;

import java.util.Date;

/**
 * Created by scamisay on 30/06/16.
 */
public class Point {

    private Date date;
    private Double value;

    public Point(Date date, Double value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
