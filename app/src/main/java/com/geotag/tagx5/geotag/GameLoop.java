package com.geotag.tagx5.geotag;

import android.os.AsyncTask;

import com.backendless.BackendlessUser;

import java.util.List;

/**
 * Created by csastudent2015 on 5/16/16.
 */
public class GameLoop extends AsyncTask{
    List<BackendlessUser> list;
    public GameLoop(List<BackendlessUser> list){
        this.list = list;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        for(BackendlessUser bu : list){

        }
        return new Object();
    }

    //Check for hit
    private boolean checkForHit(BackendlessUser bu){
        return false;
    }
}
