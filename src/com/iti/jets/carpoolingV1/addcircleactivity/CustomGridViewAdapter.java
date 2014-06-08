package com.iti.jets.carpoolingV1.addcircleactivity;

import java.util.ArrayList;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.common.ImageItem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/** * * @author manish.s * */
public class CustomGridViewAdapter extends ArrayAdapter<ImageItem> {
	Context context;
	int layoutResourceId;
	ArrayList<ImageItem> data = new ArrayList<ImageItem>();

	public CustomGridViewAdapter(Context context, int layoutResourceId,
			ArrayList<ImageItem> gridArray) {
		super(context, layoutResourceId, gridArray);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = gridArray;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new RecordHolder();
			holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}
		ImageItem item= data.get(position);
		Bitmap item2 = item.getBitmap();
		holder.imageItem.setImageBitmap(item2);
		return row;
	}

	static class RecordHolder {
		
		ImageView imageItem;
	}
}
