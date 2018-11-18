package com.nerallan.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
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
    private static final int MAX_SOUNDS = 5;
    // AssetManager is used to access assets.
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    // The SoundPool class allows you to load a large set of sounds into memory and control
    // the maximum number of sounds played simultaneously.
    private SoundPool mSoundPool;

    public BeatBox(Context pContext){
        mAssets = pContext.getAssets();
        // This constructor is deprecated, but it is needed for compatibility
        // new constructor SoundPool.Builder is not available in the minimum supported version of API 16, so we use the older constructor instead.
        // 1 param - how many sounds can be played at any time.
        // 2 param - type of audio stream that can be played by a SoundPool object. Each of audio streams has independent volume settings
        // 3 param - sampling quality
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
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
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                // load sounds into SoundPool
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }
        }
    }

    // loading Sound into SoundPool
    private void load(Sound pSound) throws IOException {
        AssetFileDescriptor assetFileDescriptor = mAssets.openFd(pSound.getAssetPath());
        // uploading file to SoundPool for later playback.
        int soundId = mSoundPool.load(assetFileDescriptor, 1);
        pSound.setSoundId(soundId);
    }


    public void play(Sound pSound){
        Integer soundId = pSound.getSoundId();
        // if the object Sound failed to load
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    // release SoundPool resources
    public void release(){
        mSoundPool.release();
    }


    public List<Sound> getSounds(){
        return mSounds;
    }
}
