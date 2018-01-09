package com.example.vibhor.assignment192;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vibhor on 04-01-2018.
 */

//CustomAdapter class which is extending BaseAdapter class
public class CustomAdapter extends BaseAdapter
{
    //initialising values
    private ArrayList<Data> informationList;
    private Context mContext;
    private LayoutInflater layoutInflater;

    //constructor
    public CustomAdapter(Context context, ArrayList<Data> list)
    {
        mContext = context;
        informationList = list;
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //method to get size of the arrayList
    @Override
    public int getCount() {
        return informationList.size();
    }

    //method to get particular element of the arrayList
    @Override
    public Object getItem(int i) {
        return informationList.get(i);
    }

    //method to get position of particular element in arrayList
    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        //inflating layout
        view = layoutInflater.inflate(R.layout.row,viewGroup,false);

        //setting reference of Textview's with their ID's in view
        TextView mname = view.findViewById(R.id.name_movie);
        TextView mvotes = view.findViewById(R.id.votes_movie);
        TextView mid = view.findViewById(R.id.id_movie);

        //setting values to view's object
        mname.setText(informationList.get(i).getName());
        mvotes.setText(informationList.get(i).getVotes());
        mid.setText(informationList.get(i).getId());

        //returning view
        return view;
    }
}
