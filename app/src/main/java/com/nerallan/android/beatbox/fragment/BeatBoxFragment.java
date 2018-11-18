package com.nerallan.android.beatbox.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nerallan.android.beatbox.BeatBox;
import com.nerallan.android.beatbox.R;
import com.nerallan.android.beatbox.Sound;

import java.util.List;

/**
 * Created by Nerallan on 11/12/2018.
 */

public class BeatBoxFragment extends Fragment{

    private BeatBox mBeatBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The normal savedInstanceState mechanism works with persisted data,
        // but the BeatBox and SoundPool classes is not persisted
        // The BeatBox instance should remain available while creating and destroying activity
        // the retainInstance property allows to save a BeatBox instance between configuration changes
        // Such fragment is not destroyed with the activity, but is preserved and transmitted to the new activity
        setRetainInstance(true);
        mBeatBox = new BeatBox(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.fragment_beat_box_recycler_view);
        // make grid with 3 column
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }


    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }


    private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button mButton;
        private Sound mSound;

//        public SoundHolder(View itemView) {
//            super(itemView);
//            mButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
//            mButton.setOnClickListener(this);
//        }

        public SoundHolder(LayoutInflater pInflater, ViewGroup container){
            super(pInflater.inflate(R.layout.list_item_sound, container, false));

            mButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
            mButton.setOnClickListener(this);
        }

        // connect sound instance with Sound object
        public void bindSound(Sound pSound){
            mSound = pSound;
            mButton.setText(mSound.getName());
        }

        @Override
        public void onClick(View v) {
            mBeatBox.play(mSound);
        }
    }


    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> pSounds){
            mSounds = pSounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
//            View view = layoutInflater.inflate(R.layout.list_item_sound, parent, false);
            return new SoundHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
