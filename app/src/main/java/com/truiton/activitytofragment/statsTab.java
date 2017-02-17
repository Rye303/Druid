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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class statsTab extends Fragment implements View.OnClickListener {

    // Debug
    private static final String LOG_TAG = "tag";
    // Fragment communication object
    private IFragmentToActivity mCallback;

    private ImageButton acc1, head, acc2;
    private ImageButton[] btns;
    int[] imageList = new int[]{R.mipmap.sword1,R.mipmap.staff1,R.mipmap.health};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats_tab, container, false);

        acc1 = (ImageButton) view.findViewById(R.id.Acc1);
        acc1.setOnClickListener(this);

        acc2 = (ImageButton) view.findViewById(R.id.Acc2);
        acc2.setOnClickListener(this);

        head = (ImageButton) view.findViewById(R.id.Head);
        head.setOnClickListener(this);

        btns = new ImageButton[]{acc1, head, acc2};

        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton acc1 = (ImageButton) view.findViewById(R.id.Acc1);
        ImageButton head = (ImageButton) view.findViewById(R.id.Head);
        ImageButton acc2 = (ImageButton) view.findViewById(R.id.Acc2);
        acc1.setImageResource(R.mipmap.none);
        head.setImageResource(R.mipmap.none);
        acc2.setImageResource(R.mipmap.none);
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
    public void fragmentCommunication(String pointer, String data, int id) {

        switch (pointer) {
            case "item":
                Log.i(LOG_TAG, "gragment cooms success"+data+id);
                setImage(data, id);
                break;

            case "Bottom":
                Log.i(LOG_TAG, "inventory recieved bottom instance"+data);
                //bottom.setText(data);
                break;

        }

    }

    public void setImage(String item, int itemID){

        switch (item){

            case "acc1":
                acc1.setImageResource(imageList[itemID]);
                break;
            case "head":
                head.setImageResource(imageList[itemID]);
                break;
            case "acc2":
                acc2.setImageResource(imageList[itemID]);
                break;
        }

    }

}
