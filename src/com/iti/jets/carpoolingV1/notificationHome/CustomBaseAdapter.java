package com.iti.jets.carpoolingV1.notificationHome;

import java.util.List;

import com.google.android.gms.internal.ho;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.pojos.Event;
import com.iti.jets.carpoolingV1.pojos.Notification;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    List<Notification> rowItems;
 
    public CustomBaseAdapter(Context context, List<Notification> items) {
        this.context = context;
        this.rowItems = items;
    }
 
    /*private view holder class*/
    private class ViewHolder {
    	LinearLayout container;
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
            
            holder.container = (LinearLayout) convertView.findViewById(R.id.container);
            holder.txtDesc = (TextView) convertView.findViewById(R.id.label1);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.label2);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        Notification rowItem = (Notification) getItem(position);
     
        holder.txtDesc.setText(rowItem.getMessage());
        holder.txtTitle.setText(rowItem.getNotificationDate().toString());
        
        if(rowItem.getEventType().equals("unread")){
        	holder.imageView.setImageResource(R.drawable.ic_action_email);
        }else if(rowItem.getEventType().equals("read")){
        	
        	holder.container.setBackgroundColor(context.getResources().getColor(R.color.readed_container));
        	holder.imageView.setImageResource(R.drawable.ic_action_read);
        }
    
 
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