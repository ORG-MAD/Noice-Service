package com.example.noice_service;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainAdapter extends BaseExpandableListAdapter {
    ArrayList<String> listGroup;
    HashMap<String, ArrayList<String>> listChild;

    //Create constructor
    public MainAdapter(ArrayList<String> listGroup, HashMap<String, ArrayList<String>> listChild){
        this.listGroup = listGroup;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        //Return group list size
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int i) {
        //Return child list size
        return listChild.get(listGroup.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        //Return group item
        return listGroup.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        //Return child item
        return listChild.get(listGroup.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        //Initialize view
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(android.R.layout.simple_expandable_list_item_1
                        ,viewGroup,false);

        //Initialize and assign variable
        TextView textView = view.findViewById( android.R.id.text1);

        //Initialize string
        String sGroup = String.valueOf(getGroup(i));

        //Set text on text view
        textView.setText(sGroup);

        textView.setTextSize(20);

        //Set text style bold
        textView.setTypeface(null, Typeface.BOLD);

        //Set text color
        textView.setTextColor(Color.YELLOW);

        //Return view
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        //Initialize view
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(android.R.layout.simple_selectable_list_item
                        ,viewGroup, false);

        //Initialize and assign variable
        TextView textView = view.findViewById( android.R.id.text1);

        //Initialize string
        String sChild = String.valueOf(getChild(i, i1));

        //Set text on text view
        textView.setText(sChild);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(20);
        //Set OnClickListener
        textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Display toast
                Toast.makeText(viewGroup.getContext(),sChild,Toast.LENGTH_SHORT).show();
            }
        });

        //Return view
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
