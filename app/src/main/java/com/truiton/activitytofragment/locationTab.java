package com.truiton.activitytofragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class locationTab extends Fragment implements View.OnClickListener {
    private static final String LOG_TAG = "tag";
    private IFragmentToActivity mCallback;
    private TextView location, locationText;
    private Button sendsword, sendstaff, sendhealth;
    private int countTop = 0;
    private int countBottom = 0;
    private ImageView background;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.location_tab, container, false);


        location = (TextView) view.findViewById(R.id.LocationLabel);
        locationText = (TextView) view.findViewById(R.id.locationText);
        locationText.setMovementMethod(new ScrollingMovementMethod());
        sendsword = (Button) view.findViewById(R.id.swordbutton);
        sendstaff = (Button) view.findViewById(R.id.staffbutton);
        sendhealth = (Button) view.findViewById(R.id.potionbutton);
        sendsword.setOnClickListener(this);
        sendstaff.setOnClickListener(this);
        sendhealth.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView background = (ImageView) view.findViewById(R.id.Background);
        background.setImageResource(R.mipmap.castle);
    }

    public void setImage(String picture){

        ImageView background = (ImageView) getView().findViewById(R.id.Background);

        switch (picture){
            case "castle":
                background.setImageResource(R.mipmap.castle);
                break;
            case "swamp":
                background.setImageResource(R.mipmap.swamp);
                break;

        }
    }

    public void setText (String location){
        locationText = (TextView) getView().findViewById(R.id.locationText);

        switch (location){
            case "swamp":
                locationText.setText("I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp I am at a swamp ");
                break;
            case "castle":
                locationText.setText("I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle I am now at a castle ");
                break;
        }
    }

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

    @Override
    public void onDetach() {
        Log.i(LOG_TAG, "onDetach");
        mCallback = null;
        super.onDetach();
    }

    public void onRefresh() {
        Toast.makeText(getActivity(), "Fragment 2: Refresh called.",
                Toast.LENGTH_SHORT).show();

    }

    public void fragmentCommunication(String pointer, String data, int id) {

            switch (pointer) {
                case "Location":
                    switch (data){
                        case "A":
                            setImage("swamp");
                            setText("swamp");
                            break;
                        case "B":
                            setImage("castle");
                            setText("castle");
                            break;
                    }
                    location.setText(data);
                    //setLocation(data);
                    break;
            }
    }

    private void setLocation(String data) {

        switch (data){
            case "A":
                background.setImageResource(R.mipmap.swamp);
                break;
            case "B":
                background.setImageResource(R.mipmap.castle);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /* activity comms
            case R.id.button:
                mCallback.showToast("Hello from Fragment 2");
                break;
                */

            case R.id.swordbutton:
                mCallback.communicateToInventory("item", "acc1",0);
                break;

            case R.id.staffbutton:
                mCallback.communicateToInventory("item", "head",1);
                break;

            case R.id.potionbutton:
                mCallback.communicateToInventory("item","acc2",2);
                break;

        }
    }


}
