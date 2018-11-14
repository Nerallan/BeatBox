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

        mBeatBox = new BeatBox(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.fragment_beat_box_recycler_view);
        // make grid with 3 column
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return view;
    }


    private class SoundHolder extends RecyclerView.ViewHolder {
        private Button mButton;
        private Sound mSound;

        public SoundHolder(View itemView) {
            super(itemView);
            mButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
        }

        // connect sound instance with Sound object
        public void bindSound(Sound pSound){
            mSound = pSound;
            mButton.setText(mSound.getName());
        }
    }


    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }


    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> pSounds){
            mSounds = pSounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_sound, parent, false);
            return new SoundHolder(view);
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
