package com.geotag.tagx5.geotag;

public class Weapon {
    private String name;
    private int ammo;
    private double range;
    private int reloadTime;  //change to time eventually
    private boolean isExplosive;
    private int damage;

    public Weapon(String name, int ammo, double range, int reloadTime, boolean isExplosive, int damage){
        this.name = name;
        this.ammo = ammo;
        this.range = range;
        this.reloadTime = reloadTime;
        this.isExplosive = isExplosive;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getAmmo() {
        return ammo;
    }

    public double getRange() {
        return range;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public boolean isExplosive() {
        return isExplosive;
    }

    public int getDamage() {
        return damage;
    }

    public int fire() {
        return ammo--;
    }
}