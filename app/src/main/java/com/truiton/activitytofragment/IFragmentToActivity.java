package com.truiton.activitytofragment;

// Communication headquarters, makes sure there are public functions, to the main activity. Fragments can use these to speak to each other

public interface IFragmentToActivity {
    void showToast(String msg);

    void communicateToTravel(String pointer, String data, int id);

    void communicateToLocation(String pointer, String data, int id);

    void communicateToInventory(String pointer, String data, int id);

    void communicateToStats(String pointer, String data, int id);

}