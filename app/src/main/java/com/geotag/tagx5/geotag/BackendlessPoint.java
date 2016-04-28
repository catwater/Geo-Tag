package com.geotag.tagx5.geotag;

/**
 * Created by csastudent2015 on 4/26/16.
 */
public class BackendlessPoint {

    private double x;
    private double y;

    public BackendlessPoint() {}

    public BackendlessPoint(double x,double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
