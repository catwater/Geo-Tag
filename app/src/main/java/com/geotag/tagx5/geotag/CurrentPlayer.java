package com.geotag.tagx5.geotag;

import com.backendless.BackendlessUser;

import java.util.ArrayList;
import java.util.List;

public class CurrentPlayer extends Player {
    private double radius;
    private List<BackendlessUser> targetsInRadius;

    public CurrentPlayer(int lives, double latitude, double longitude, String name) {
        super(lives, latitude, longitude, name);
        radius = this.getWeapon().getRange();
        targetsInRadius = new ArrayList<BackendlessUser>();
    }

    public void updateRadius() {
        radius = this.getWeapon().getRange();
    }

    public double getRadius() {
        return radius;
    }

    public void addTargetInRadius(BackendlessUser target) {
        targetsInRadius.add(target);
    }

    public void setTargetsInRadius(List<BackendlessUser> updatedList) {
        targetsInRadius = updatedList;
    }

    public void clearTargetsInRadius() {
        targetsInRadius = new ArrayList<BackendlessUser>();
    }
}