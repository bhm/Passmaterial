package com.bustiblelemons.listview.swipabble;

/**
 * Created by bhm on 30/04/14.
 */
public interface ListViewCallback {
    public boolean onDisableList();

    public boolean onEnableList();

    public void animateRemoval(int position);
}
