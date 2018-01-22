package com.example.srimadhan11.madlabexp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<String> header;
    private ArrayList<String> link;

    ListAdapter(Activity activity, ArrayList<String> header, ArrayList<String> link){
        this.activity = activity;
        this.header = header;
        this.link = link;
    }

    @Override
    public int getCount() {
        return header.size();
    }

    @Override
    public String getItem(int position) {
        return header.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.adapter_list, parent, false);
        } else {
            rowView = convertView;
        }

        TextView reportName = rowView.findViewById(R.id.text);
        reportName.setText(header.get(position));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(link.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        });

        return rowView;
    }
}
