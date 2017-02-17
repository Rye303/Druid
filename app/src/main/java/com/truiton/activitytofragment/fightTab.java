package com.truiton.activitytofragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class fightTab extends Fragment implements View.OnClickListener {

    // Debug
    private static final String LOG_TAG = "tag";
    // Fragment communication object
    private IFragmentToActivity mCallback;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fight_tab, container, false);


        return  view;
    }

    // shouldn't get called either
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (IFragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentToActivity");
        }
    }

    // shouldn't be called
    @Override
    public void onDetach() {
        Log.i(LOG_TAG, "onDetach travel");
        mCallback = null;
        super.onDetach();
    }

    // refresh button
    public void onRefresh() {
        Toast.makeText(getActivity(), "Fragment 1: Refresh called.",
                Toast.LENGTH_SHORT).show();
    }


    // if there is buttons this is where they get processed
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


    // Receive data
    public void fragmentCommunication(String pointer, String data) {

    }
}
