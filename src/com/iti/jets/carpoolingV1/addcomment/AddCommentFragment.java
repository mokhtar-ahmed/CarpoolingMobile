package com.iti.jets.carpoolingV1.addcomment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.pojos.Comment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CirclesCustomArrayAdapter;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddCommentFragment extends Fragment {

	
	View rootView;
	Button sendCommentBtn;
	EditText commentTextField;
	ListView list;
	String comment = null;
	AddCommentController controller;
	ArrayList<Comment> commentsArrayList = new ArrayList<Comment>();
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		setHasOptionsMenu(true);
		rootView = inflater.inflate(R.layout.add_comment_fragment,container, false);
		commentTextField = (EditText)rootView.findViewById(R.id.commentTextField);
		sendCommentBtn = (Button)rootView.findViewById(R.id.sendCommentBtn);
		sendCommentBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!commentTextField.getText().toString().equals(""))
				{
					comment = commentTextField.getText().toString();
					controller = new AddCommentController();
					System.out.println("COMMENT"+"   "+comment);
					controller.setArguments(comment,AddCommentFragment.this);
				}
			
			}
		});
		
		return rootView;
	}	
	
	public void getAllEventComments(String result)
	{
		JSONArray commentsJsArray;
		JSONObject tempJsonObj,commentOwnerJsObj;
		try {
			 tempJsonObj = new JSONObject(result);
		if(!tempJsonObj.getBoolean("HasError"))
		{
			commentsJsArray = tempJsonObj.getJSONArray("ResponseValue");
			
			for(int i=0;i<commentsJsArray.length();i++)
			{
				JSONObject jsObj = commentsJsArray.getJSONObject(i);
				System.out.println(jsObj);
				Comment tempComment = new Comment();
				commentOwnerJsObj = jsObj.getJSONObject("CommentOwner");
				tempComment.setCommentId(jsObj.getInt("id"));
				tempComment.setCommentText(jsObj.getString("CommentText"));
				tempComment.setCommentOwnerName(commentOwnerJsObj.getString("usernam"));
				commentsArrayList.add(tempComment);
				
			}
			System.out.println("Size"+"  "+commentsArrayList.size());
		
		}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Resources res =getResources();
        list = ( ListView ) rootView.findViewById( R.id.commentsList );   
        /**************** Create Custom Adapter *********/
        CommentsCustomArrayAdapter adapter = new CommentsCustomArrayAdapter(AddCommentFragment.this,commentsArrayList,res);	
        list.setAdapter(adapter);
	}
}

