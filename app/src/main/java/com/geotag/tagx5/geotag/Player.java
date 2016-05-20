package com.geotag.tagx5.geotag;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class Player {
    private int lives;
    private int health;
    private double latitude;
    private double longitude;
    private String name;
    private Weapon weapon;

    public Player(int lives, double latitude, double longitude, String name) {
        this.lives = lives;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        health = 100;
    }

    public Player(String objID) {
        BackendlessUser thisUser = Backendless.UserService.findById(objID);
        lives = (int) thisUser.getProperty("livesRemaining");
        health = (int) thisUser.getProperty("health");
        latitude = (double) thisUser.getProperty("latitude");
        longitude = (double) thisUser.getProperty("longitude");
        name = (String) thisUser.getProperty("username");
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
        return weapon;
    }

    public void setWeapon(com.geotag.tagx5.geotag.Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHealth() {
        return health;
    }

    public void isHit(Weapon weapon) {
        health -= weapon.getDamage();
        if(health <= 0) {
            lives--;
            health = 100;
        }
    }

}