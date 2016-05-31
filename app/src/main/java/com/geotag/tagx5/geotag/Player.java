package com.geotag.tagx5.geotag;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class Player {
    private int livesRemaining;
    private int health;
    private double latitude;
    private double longitude;
    private String name;
    private Weapon weapon;

    private String username;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private int score;

    public Player() {
    }

    public Player(int lives, double latitude, double longitude, String name) {
        this.livesRemaining = lives;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        health = 100;
    }

    public Player(String objID) {
        BackendlessUser thisUser = Backendless.UserService.findById(objID);
        livesRemaining = (int) thisUser.getProperty("livesRemaining");
        health = (int) thisUser.getProperty("health");
        latitude = (double) thisUser.getProperty("latitude");
        longitude = (double) thisUser.getProperty("longitude");
        name = (String) thisUser.getProperty("username");
    }

    public int getLivesRemining() {
        return livesRemaining;
    }

    public void setLivesRemaining(int lives) {
        this.livesRemaining = lives;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLivesRemaining() {
        return livesRemaining;
    }

    public void isHit(Weapon weapon) {
        health -= weapon.getDamage();
        if(health <= 0) {
            livesRemaining--;
            health = 100;
        }
    }

}