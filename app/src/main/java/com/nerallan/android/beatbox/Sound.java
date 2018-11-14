package com.nerallan.android.beatbox;

/**
 * Created by Nerallan on 11/14/2018.
 */

// object to store file name
public class Sound {
    private String mAssetPath;
    private String mName;

    public Sound(String pAssetPath){
        mAssetPath = pAssetPath;
        String[] components = pAssetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wav", "");
    }

    public String getAssetPath() {
        return mAssetPath;
    }


    public String getName() {
        return mName;
    }
}


