package com.truiton.activitytofragment;

/**
 * Created by Ryan on 2/16/2017.
 */

public class Player {

    // inventory, check to see if there is an item before you can equip
    public Boolean swordItem = Boolean.FALSE;
    public Boolean staffItem = Boolean.FALSE;
    public Boolean potionItem = Boolean.FALSE;


    // Default no treasure found.
    public Boolean treasureFound = Boolean.FALSE;

    // Init strength = 1
    public int strength = 1;

}
