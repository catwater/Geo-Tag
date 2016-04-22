package com.geotag.tagx5.geotag;

/**
 * Created by csastudent2015 on 4/22/16.
 */
public class Player {
    private int lives;
    private double latitude;
    private double longitude;
    private String name;

    public Player(int lives, double latitude, double longitude, String name) {
        this.lives = lives;

        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
