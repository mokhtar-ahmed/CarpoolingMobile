package com.iti.jets.carpoolingV1.eventDetails;

import java.util.List;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.pojos.Comment;
import com.iti.jets.carpoolingV1.pojos.Event;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomCommentBaseAdapter extends BaseAdapter {
    Context context;
    List<Comment> rowItems;
 
    public CustomCommentBaseAdapter(Context context, List<Comment> items) {
        this.context = context;
        this.rowItems = items;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
 
        LayoutInflater mInflater = (LayoutInflater)
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.notification_cell, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.label1);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.label2);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
         
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        Comment rowItem = (Comment) getItem(position);
 
        holder.txtDesc.setText(rowItem.getUsername());
        holder.txtTitle.setText(rowItem.getText()+"\n"+ rowItem.getDate().toString());
        holder.imageView.setImageResource(R.drawable.ic_user);
 
        return convertView;
    }
 
    @Override
    public int getCount() {
        return rowItems.size();
    }
 
    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
}