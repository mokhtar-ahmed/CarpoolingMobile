package com.iti.jets.carpoolingV1.eventRequests;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.httphandler.AcceptEvent;
import com.iti.jets.carpoolingV1.pojos.CustomUser;
import com.iti.jets.carpoolingV1.pojos.EntityFactory;
import com.iti.jets.carpoolingV1.pojos.Event;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    List<CustomUser> rowItems;
    int idEvent;
    RequestsHomeController controller;
    RequestsHome view;
    
    public CustomBaseAdapter(Context context, List<CustomUser> items, int idEvent
    		, RequestsHomeController controller , RequestsHome view ) {
    	 this.context = context;
    	 this.view = view;
         this.rowItems = items;
         this.idEvent = idEvent;
         this.controller = controller;
    }
 
   
	/*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        Button accept;
        Button reject;
    }
 
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
 
        LayoutInflater mInflater = (LayoutInflater)
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_request_cell, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.label1);
            holder.accept = (Button) convertView.findViewById(R.id.acceptBtn);
            holder.reject = (Button) convertView.findViewById(R.id.RejectBtn);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
            
         
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        CustomUser rowItem = (CustomUser) getItem(position);
 
      
        holder.txtTitle.setText(rowItem.getUsername());
        
		final JSONObject ob  = new JSONObject();
		
		try {
			ob.put("eventId", idEvent);
			ob.put("userId", rowItem.getId());
			//controller.joinEventHandler(ob.toString());
		//	prog = ProgressDialog.show(getActivity(), "connection", "wait to store your request",true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
        holder.accept.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				controller.acceptHandler(ob.toString());
				view.userRequest.remove(position);
				view.adapter.notifyDataSetChanged();
				
			}
		});
        holder.reject.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				controller.rejectHandler(ob.toString());
				rowItems.remove(position);
				view.userRequest.remove(position);
				view.adapter.notifyDataSetChanged();	
            }
		});
                
        
    	//Bitmap bmp = BitmapFactory.decodeByteArray(rowItem.getImage(), 0, rowItem.getImage().length);
        //holder.imageView.setImageBitmap(bmp);
 
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