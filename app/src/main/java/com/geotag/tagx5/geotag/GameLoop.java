package com.geotag.tagx5.geotag;

import android.os.AsyncTask;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.ArrayList;
import java.util.List;

public class GameLoop extends AsyncTask{
    List<BackendlessUser> list;
    CurrentPlayer me;
    public GameLoop(List<BackendlessUser> list){
        this.list = list;

        //instantiate current player with data from Backendless
        int liv = (int) Backendless.UserService.CurrentUser().getProperty("livesRemaining");
        double lati = (double) Backendless.UserService.CurrentUser().getProperty("latitude");
        double longi = (double) Backendless.UserService.CurrentUser().getProperty("longitude");
        String nam = (String) Backendless.UserService.CurrentUser().getProperty("username");
        me = new CurrentPlayer(liv, lati, longi, nam);
    }
    @Override
    protected Object doInBackground(Object[] params) {
        for(BackendlessUser bu : list){

        }
        return new Object();
    }

    //Check for hit
    public boolean checkIfInRadius(BackendlessUser target) {
        double x1 = (double) Backendless.UserService.CurrentUser().getProperty("longitude");
        double y1 = (double) Backendless.UserService.CurrentUser().getProperty("latitude");
        double x2 = (double) target.getProperty("longitude");
        double y2 = (double) target.getProperty("latitude");
        double xdiff = x1 - x2;
        double ydiff = y1 - y2;
        double dist = Math.sqrt(Math.pow(xdiff, 2) + Math.pow(ydiff, 2));

        if(dist <= me.getRadius()) {
            return true;
        }
        return false;
    }

    public void updateTargetsInRadius() {
        me.clearTargetsInRadius();
        me.updateRadius();
        for(int i = 0; i < list.size(); i++) {
            if(checkIfInRadius(list.get(i))) {
                //add list.get(i) to current user's list of targets in radius
                me.addTargetInRadius(list.get(i));
            }
        }
    }

    public List<Player> backendlessUsersToPlayers(List<BackendlessUser> input) {
        List<Player> output = new ArrayList<Player>();
        for(int i = 0; i < input.size(); i++) {
            output.add(new Player(input.get(i).getObjectId()));
        }
        return output;
    }

    public void shoot() {
        updateTargetsInRadius();
        int dmg = me.getWeapon().getDamage();
        if(me.getWeapon().isExplosive()) {

        }
    }

    public void dealDamage() {
        List<Player> targets = backendlessUsersToPlayers(me.getTargetsInRadius());
        for (int i = 0; i < targets.size(); i++) {
            targets.get(i).isHit(me.getWeapon());
        }
    }
}