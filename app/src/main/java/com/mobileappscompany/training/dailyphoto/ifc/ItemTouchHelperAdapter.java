package com.mobileappscompany.training.dailyphoto.ifc;

/**
 * @author mpamplona
 * @version 1.0
 *
 * Created on 3/6/2016
 *
 * Callbacks for swip and move in RecyclerViews events
 *
 */
public interface ItemTouchHelperAdapter {

    void onItemDismiss(int position);

    void onItemSwip(int position);

}
