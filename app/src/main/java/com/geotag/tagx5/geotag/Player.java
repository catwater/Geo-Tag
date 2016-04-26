package com.geotag.tagx5.geotag;

/**
 * Created by csastudent2015 on 4/22/16.
 */
public class Player {
    private int lives;
    private int health;
    private double latitude;
    private double longitude;
    private String name;
    private Weapon Weapon;

    public Player(int lives, double latitude, double longitude, String name) {
        this.lives = lives;

        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        health = 100;
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

    public com.geotag.tagx5.geotag.Weapon getWeapon() {
        return Weapon;
    }

    public void setWeapon(com.geotag.tagx5.geotag.Weapon weapon) {
        Weapon = weapon;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
