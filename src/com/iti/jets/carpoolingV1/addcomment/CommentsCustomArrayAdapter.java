package com.iti.jets.carpoolingV1.addcomment;

import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.common.Circle;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.deletecircle.DeleteCircleController;
import com.iti.jets.carpoolingV1.deletecomment.DeleteCommentController;
import com.iti.jets.carpoolingV1.pojos.Comment;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.CircleUsersFragment;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment.FragmentCallback;


public class CommentsCustomArrayAdapter extends BaseAdapter  implements OnClickListener{


	private AddCommentFragment fragment;
    private ArrayList data;
    private static LayoutInflater inflater=null;
	
	public static boolean deleteflag;
    public Resources res;
    Comment CommentValues=null;
    int i=0;
    
     
    /*************  CustomAdapter Constructor *****************/
    public CommentsCustomArrayAdapter(AddCommentFragment a, ArrayList d,Resources resLocal) {
         
           /********** Take passed values **********/
    	    fragment = a;
            data=d;
            res = resLocal;
         
            /***********  Layout inflator to call external xml layout () ***********/
             inflater = ( LayoutInflater )fragment.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         
    }
 
    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {
         
        if(data.size()<=0)
            return 1;
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
     
    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
         
   	 public TextView usernameTxt,commentDateTxt,commentTxt;
     public ImageView userImage,deleteCommentImg;
     
    }
 
    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(final int position, View convertView, ViewGroup parent) {
         
        View vi = convertView;
        ViewHolder holder;
         
        if(convertView==null){
             
            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.customlist_comments, null);
             
            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.usernameTxt = (TextView) vi.findViewById(R.id.usernameTxt);
            holder.commentTxt = (TextView) vi.findViewById(R.id.commenttTxt);
            holder.commentDateTxt=(TextView) vi.findViewById(R.id.datecommentTxt);
            holder.userImage=(ImageView)vi.findViewById(R.id.userImg);
            holder.deleteCommentImg = (ImageView)vi.findViewById(R.id.deleteCommentImg); 
            
           
           /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
            
             
        }
        else 
            holder=(ViewHolder)vi.getTag();
         
        if(data.size()<=0)
        {
            holder.usernameTxt.setText("Connection Error");
             
        }
        else
        {
            /***** Get each Model object from Arraylist ********/
            CommentValues=null;
            CommentValues = ( Comment ) data.get( position );
             
            /************  Set Model values in Holder elements ***********/

             holder.usernameTxt.setText( CommentValues.getCommentOwnerName() );
             holder.commentTxt.setText(CommentValues.getCommentText());
             
            // holder.commentDateTxt.setText((CharSequence) new Date());
             holder.deleteCommentImg.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					  CommentValues = (Comment)data.get(position);
					  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								fragment.getActivity());
				 
							// set title
							alertDialogBuilder.setTitle("Confirm");
				 
							// set dialog message
							alertDialogBuilder
								.setMessage("Are you sure you want to delete this comment?")
								.setCancelable(false)
								.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, close
										// current activity
										DeleteCommentController controller = new DeleteCommentController();
										controller.setArguments	(fragment,CommentValues);
									}
								  })
								.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										dialog.cancel();
									}
								});
				 
								// create alert dialog
								AlertDialog alertDialog = alertDialogBuilder.create();
				 
								// show it
								alertDialog.show(); 	
				}
			});
           	 //holder.circleImage.setImageResource(R.drawable.ic_people);
             
            // vi.setOnClickListener(new OnItemClickListener( position ));
             /******** Set Item Click Listner for LayoutInflater for each row *******/ 
        }       
        return vi;
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	/********* Called when Item click in ListView ************/
//   private class OnItemClickListener  implements OnClickListener{           
//       private int mPosition;
//        
//       OnItemClickListener(int position){
//            mPosition = position;
//       }
//        
//       @Override
//       public void onClick(View arg0) {
//
//       
//        
////       	  AllCirclesListFragment AllCirclesActObj = activity;
////
////             /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
////
////              AllCirclesActObj.onItemClick(mPosition);
//        
//         
//       }               
//   }   
//   
  
}
