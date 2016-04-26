package com.geotag.tagx5.geotag;

/**
 * Created by Jaydon on 4/22/2016.
 */
public class Weapon {
    private String name;
    private int ammo;
    private double range;
    private int reloadTime;  //change to time eventually
    private boolean isExplosive;

    public Weapon(String name, int ammo, double range, int reloadTime, boolean isExplosive){
        this.name=name;
        this.ammo=ammo;
        this.range=range;
        this.reloadTime=reloadTime;
        this.isExplosive=isExplosive;
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

    public int fire(){
        return ammo--;
    }


}
