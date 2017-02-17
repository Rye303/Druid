package com.truiton.activitytofragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IFragmentToActivity {
    private final String LOG_TAG = "MainActivity";
    private PagerAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Tab labels (if tab added this must be updated)
        ArrayList<String> tabs = new ArrayList<>();
        tabs.add("Travel");
        tabs.add("Scout");
        tabs.add("Pack");
        tabs.add("Stats");
        tabs.add("Fight");
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // set up
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabs);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // do not destroy the fragments, can keep up to 5 fragments loaded at all times.
        viewPager.setOffscreenPageLimit(5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // scroll to tabs ( if tab added, this must be updated)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            int position = tabLayout.getSelectedTabPosition();
            Fragment fragment = adapter.getFragment(tabLayout
                    .getSelectedTabPosition());
            if (fragment != null) {
                switch (position) {
                    case 0:
                        ((travelTab) fragment).onRefresh();
                        break;
                    case 1:
                        ((locationTab) fragment).onRefresh();
                        break;
                    case 2:
                        ((inventoryTab) fragment).onRefresh();
                        break;
                    case 3:
                        ((inventoryTab) fragment).onRefresh();
                        break;
                    case 4:
                        ((inventoryTab) fragment).onRefresh();
                        break;

                }
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // quick way to show stuff to screen (don't touch)
    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // send data to Travel Tab (don't touch)
    @Override
    public void communicateToTravel(String pointer, String data, int id) {
        travelTab Travelfragment = (travelTab) adapter.getFragment(0);
        if (Travelfragment != null) {
            Travelfragment.fragmentCommunication(pointer,data, id);
        } else {
            Log.i(LOG_TAG, "Fragment 0 is not initialized");
        }
    }

    // Send data to Location Tab (don't touch)
    @Override
    public void communicateToLocation(String pointer, String data, int id) {
        locationTab Locationfragment = (locationTab) adapter.getFragment(1);
        if (Locationfragment != null) {
            Locationfragment.fragmentCommunication(pointer,data, id);
        } else {
            Log.i(LOG_TAG, "Fragment 1 is not initialized");
        }
    }

    // Send Data to Inventory tab (don't touch)
    @Override
    public void communicateToInventory(String pointer, String data, int id) {
        inventoryTab Inventoryfragment = (inventoryTab) adapter.getFragment(2);
        if (Inventoryfragment != null) {
            Inventoryfragment.fragmentCommunication(pointer,data, id);
        } else {
            Log.i(LOG_TAG, "Fragment 2 is not initialized");
        }
    }

    // Send Data to Inventory tab (don't touch)
    @Override
    public void communicateToStats(String pointer, String data, int id) {
        statsTab Statsfragment = (statsTab) adapter.getFragment(3);
        if (Statsfragment != null) {
            Statsfragment.fragmentCommunication(pointer,data, id);
        } else {
            Log.i(LOG_TAG, "Fragment 3 is not initialized");
        }
    }
}
