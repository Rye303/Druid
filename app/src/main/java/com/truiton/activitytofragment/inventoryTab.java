package com.truiton.activitytofragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class inventoryTab extends Fragment implements View.OnClickListener {
    Player player1 = new Player();
    private static final String LOG_TAG = "tag";
    private IFragmentToActivity mCallback;
    private CheckBox treasureCheck;
    private Button Card;
    private Button sendtoLocation;
    private Button sendtoTravel;
    private int items = 0;
    private ImageButton image1, image2, image3;
    private ImageButton[] btns;
    int[] imageList = new int[]{R.mipmap.sword1,R.mipmap.staff1,R.mipmap.health};
    List<String> myItems = new ArrayList<String>();
    List<Integer> myIds = new ArrayList<Integer>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inventory_tab, container, false);

        //ard = (Button) view.findViewById(R.id.GetCard);
        //Card.setOnClickListener(this);

        //treasureCheck = (CheckBox) view.findViewById(R.id.checkBox);
        //treasureCheck.setOnClickListener(this);

        image1 = (ImageButton) view.findViewById(R.id.Image1);
        image1.setOnClickListener(this);

        image2 = (ImageButton) view.findViewById(R.id.Image2);
        image2.setOnClickListener(this);

        image3 = (ImageButton) view.findViewById(R.id.Image3);
        image3.setOnClickListener(this);

        btns = new ImageButton[]{image1, image2, image3};

        //sendtoLocation = (Button) view.findViewById(R.id.button1);
        //sendtoTravel = (Button) view.findViewById(R.id.button2);

        //sendtoLocation.setOnClickListener(this);
        //sendtoTravel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton image1 = (ImageButton) view.findViewById(R.id.Image1);
        ImageButton image2 = (ImageButton) view.findViewById(R.id.Image2);
        ImageButton image3 = (ImageButton) view.findViewById(R.id.Image3);
        image1.setImageResource(R.mipmap.none);
        image2.setImageResource(R.mipmap.none);
        image3.setImageResource(R.mipmap.none);
    }

    @Override
    public void onAttach(Context context) {
        Log.i(LOG_TAG, "onAttach inventory");
        super.onAttach(context);
        try {
            mCallback = (IFragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentToActivity");
        }
    }
/*
    @Override
    public void onDetach() {
        Log.i(LOG_TAG, "onDetach inventory");
        mCallback = null;
        super.onDetach();
    }
*/
    public void onRefresh() {
        Toast.makeText(getActivity(), "Fragment 2: Refresh called.",
                Toast.LENGTH_SHORT).show();
    }

    /*
    Incoming communications, the pointer string is specified by the sender and indicates where the string value should go.
    Each tab will have a specific static layout with labels for each object.
    For example the pointer "Top" currently sets the top text view on any Tab to the String "data"
    */
    public void fragmentCommunication(String pointer, String data, int id) {

        switch (pointer) {
            case "item":
                setImage(data,id);
                break;

            case "Bottom":
                Log.i(LOG_TAG, "inventory recieved bottom instance"+data);
                //bottom.setText(data);
                break;

        }
    }

    // button clicks processed by id
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /* To activity comms
            case R.id.button:
                mCallback.showToast("Hello from Fragment 2");
                break;
                */
            /*

            case R.id.button1:
                countTop++;
                mCallback.communicateToLocation("Top", "From inventory: "+countTop+"");
                break;

            case R.id.button2:
                countBottom++;
                mCallback.communicateToTravel("Bottom", "From inventory: "+countBottom+"");
                break;



            case R.id.GetCard:
                getCard();
                break;

            case R.id.checkBox:
                if (treasureCheck.isChecked())
                    player1.treasureFound = Boolean.TRUE;

                else
                    player1.treasureFound = Boolean.FALSE;
                break;

                */
            case R.id.Image1:
                if (items >= 1) {
                    msg("Item equipped");
                    mCallback.communicateToStats("item", myItems.get(0), myIds.get(0));
                }
                else
                    msg("No item to equip");
                break;

            case R.id.Image2:
                if (items >= 2){
                    msg("Item equipped");
                    mCallback.communicateToStats("item", myItems.get(1), myIds.get(1));
                }
                else
                    msg("No item to equip");
                break;

            case R.id.Image3:
                if (items >= 3){
                    msg("Item equipped");
                    mCallback.communicateToStats("item", myItems.get(2), myIds.get(2));
                }
                else
                    msg("No item to equip");
                break;

        }
    }

    public void setImage(String data, int itemID){

        // put the type of item into a list
        myItems.add(data);
        myIds.add(itemID);

        // set the image
        (btns[items]).setImageResource(imageList[itemID]);
        items++;
        Log.i(LOG_TAG, "Set Image"+items);

    }

    private void getCard() {

        if (player1.treasureFound)
            msg("treasure was found");
        else
            msg("no treasure found");

    }

    // Call Toast
    private void msg(String s)
    {
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }
}
