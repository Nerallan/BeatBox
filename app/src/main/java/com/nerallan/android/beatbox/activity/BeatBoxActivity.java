package com.nerallan.android.beatbox.activity;

import android.support.v4.app.Fragment;

import com.nerallan.android.beatbox.fragment.BeatBoxFragment;

public class BeatBoxActivity extends SingleFragmentActivity{


    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}
