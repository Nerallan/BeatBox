package com.nerallan.android.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nerallan on 11/14/2018.
 */

public class BeatBox {
    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";
    // is used to access assets.
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();

    public BeatBox(Context pContext){
        mAssets = pContext.getAssets();
        loadSounds();
    }

    // refers to assets using the method list (String)
    private void loadSounds(){
        String[] soundNames;
        try {
            // To get a list of available assets in this folder
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.d(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe){
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

        for (String filename : soundNames){
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            mSounds.add(sound);
        }
    }


    public List<Sound> getSounds(){
        return mSounds;
    }
}
