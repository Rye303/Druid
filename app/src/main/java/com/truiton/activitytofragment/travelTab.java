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

public class travelTab extends Fragment {

    // Debug
    private static final String LOG_TAG = "tag";
    // Fragment communication object
    private IFragmentToActivity mCallback;

    // init
    private TextView locationLabel;
    private ListView travelList;
    private String location = "A";
    ArrayAdapter<String> adapter = null;

    // Initial travel list for location A
    String[] travel = {"B","C","D",""};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.travel_tab, container, false);

        locationLabel = (TextView) view.findViewById(R.id.CurrentLocation);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, travel);
        travelList = (ListView) view.findViewById(R.id.TravelList);
        travelList.setAdapter(adapter);

        // when list is clicked
        travelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //clicked string
                TextView destination = (TextView) view;
                Toast.makeText(getActivity(),"travel to: "+destination.getText(),Toast.LENGTH_SHORT).show();
                // set current location in Travel Tab
                locationLabel.setText(destination.getText());
                location = destination.getText().toString();
                Log.i(LOG_TAG, "location set to: "+location);

                switch (location){
                    case "A":
                        // set new travel list for A
                        travel[0] = "B";
                        travel[1] = "C";
                        travel[2] = "D";
                        travel[3] = "";
                        // send to location tab
                        mCallback.communicateToLocation("Location", "A",0);
                        break;

                    case "B":
                        travel[0] = "A";
                        travel[1] = "C";
                        travel[2] = "D";
                        travel[3] = "E";
                        mCallback.communicateToLocation("Location", "B",0);
                        break;

                    case "C":
                        travel[0] = "A";
                        travel[1] = "B";
                        travel[2] = "D";
                        travel[3] = "";
                        mCallback.communicateToLocation("Location", "C",0);
                        break;

                    case "D":
                        travel[0] = "B";
                        travel[1] = "C";
                        travel[2] = "E";
                        travel[3] = "";
                        mCallback.communicateToLocation("Location", "D",0);
                        break;

                    case "E":
                        travel[0] = "A";
                        travel[1] = "B";
                        travel[2] = "D";
                        travel[3] = "";
                        mCallback.communicateToLocation("Location", "E",0);
                        break;
                }
                // update the list
                adapter.notifyDataSetChanged();
            }
        });

        // reset the listview just incase
        travelList = (ListView) view.findViewById(R.id.TravelList);
        travelList.setAdapter(adapter);
        return view;
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

/*
    // if there is buttons this is where they get processed
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mCallback.showToast("Hello from Fragment 1");
                break;

            case R.id.button2:
                mCallback.communicateToInventory("Top", "From travel: "+countTop+"");
                break;

            case R.id.button3:
                mCallback.communicateToLocation("Bottom", "From travel: "+countBottom+"");
                break;
        }
    }
*/

    // Receive data
    public void fragmentCommunication(String pointer, String data, int id) {

        switch (pointer) {
            case "Location":
                Log.i(LOG_TAG, "travel recieved Location data"+data);
                locationLabel.setText(data);
                break;

        }
    }
}
