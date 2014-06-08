package com.iti.jets.carpoolingV1.retrieveallcircles;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.Log;
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
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleController;
import com.iti.jets.carpoolingV1.common.Circle2;
import com.iti.jets.carpoolingV1.common.ContactObj;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.synccontactsactivity.AddUserToCircleController;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsController;



//********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/

public class CirclesUsersArrayAdapter extends BaseAdapter  implements OnClickListener{
          
         /*********** Declare Used Variables *********/
		 private ArrayList<ContactObj> contactListTemp = new ArrayList<ContactObj>();
		 ContentResolver contentResolver;
         private CircleUsersFragment activity;
         private ArrayList data;
         private static LayoutInflater inflater=null;
         public Resources res;
         boolean checkBoxVisibleFlag = false;
         User UserValues=null;
         int i=0;
          
         /*************  CustomAdapter Constructor 
         * @param circleId *****************/
         public CirclesUsersArrayAdapter(CircleUsersFragment activity2, ArrayList d,Resources resLocal) {
              
                /********** Take passed values **********/
                 activity = activity2;
                 data=d;
                 res = resLocal;
              
                 /***********  Layout inflator to call external xml layout () ***********/
                 
                  inflater = ( LayoutInflater )activity.getActivity().
                                              getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              
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
              
             public TextView userNameTxt;
             public TextView phoneTxt;
             public ImageView userImage;
             public CheckBox checkBox;
      
         }
      
         /****** Depends upon data size called for each row , Create each ListView row *****/
         public View getView(final int position, View convertView, ViewGroup parent) {
              
             View vi = convertView;
             final ViewHolder holder;
              
             if(convertView==null){
                  
                 /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                 vi = inflater.inflate(R.layout.circle_users_customlist, null);
                  
                 /****** View Holder Object to contain tabitem.xml file elements ******/
 
                 holder = new ViewHolder();
                 holder.userNameTxt = (TextView) vi.findViewById(R.id.nameTxt);
                 holder.userImage =(ImageView)vi.findViewById(R.id.userImage);
                 holder.phoneTxt = (TextView) vi.findViewById(R.id.phoneTxt);
                 holder.checkBox = (CheckBox) vi.findViewById(R.id.chkbox);
                /************  Set holder with LayoutInflater ************/
                 vi.setTag( holder );
             }
             else 
             {
            	 holder=(ViewHolder)vi.getTag();
            	 if((checkBoxVisibleFlag == true))
                 {
                	 holder.checkBox.setVisibility(1); 
                	
                 }
             }
              
             if(data.size()<=0)
             {
                 holder.userNameTxt.setText("No Users Available");
                  
             }
             else
             {
                 /***** Get each Model object from Arraylist ********/
                UserValues =null;
                UserValues  = ( User ) data.get( position );
                  
                 /************  Set Model values in Holder elements ***********/
                  ArrayList<ContactObj> contactListNumber = new ArrayList<ContactObj>();
                  contactListNumber = this.fetchContacts();	
                  for(int j=0;j<contactListNumber.size();j++)
                  {
                	  if(UserValues.getPhone().equals(contactListNumber.get(j).getPhoneNo()))
                	  {
                		  holder.userNameTxt.setText(contactListNumber.get(j).getName());
                		  break;
                	  }
                  }
                 
                  holder.phoneTxt.setText(UserValues.getPhone());
                  if (UserValues.getImageURL().equals("Friends")) {
                	  holder.userImage.setImageResource(R.drawable.p5);
                  } else if (UserValues.getImageURL().equals("Family")) {
                	  holder.userImage.setImageResource(R.drawable.p8);
                  } else if (UserValues.getImageURL().equals("Work")) {
                	  holder.userImage.setImageResource(R.drawable.p10);
                  }
                  else
                  {
//                	  holder.userImage.setImageResource(R.drawable.photo);
                	  byte [] encodeByte=Base64.decode(UserValues.getImageURL(),Base64.DEFAULT);
              		  System.out.println(UserValues.getImageURL());
              		  System.out.println(encodeByte);
              	      Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
              	      // Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(userToRetrieve.getString("imageString")));
              	      holder.userImage.setImageBitmap(bitmap);
                       if(UserValues.getImageURL().equals("image"))
                       {
                     	  holder.userImage.setImageResource(
                                   res.getIdentifier(
                                   "com.androidexample.customlistview:drawable/photo"
                                   ,null,null));
                       }
                      
                	  
                	  
                	  
                	  holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                   	   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   		  
                   		   if( holder.checkBox.isChecked())
                   		   {
                   			     UserValues = (User)data.get(position);
                   			     UserValues.setIsSelected(true);
                        		 CircleUsersFragment fragment = activity;
                        		 fragment.oncheckCircle(UserValues);
                   		   }
                   		 
                   		
                   	   }
                   	  });
                  }
                  
                  /******** Set Item Click Listner for LayoutInflater for each row *******/
 
                  //vi.setOnClickListener(new OnItemClickListener( position ));
             }
             return vi;
         }

		private ArrayList<ContactObj> fetchContacts() {
			// TODO Auto-generated method stub
			String phoneNumber = null;
	         
	        String name  = null;
	        String email = null;
	        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
	        String full_Name = ContactsContract.Contacts.DISPLAY_NAME;
	        String _ID = ContactsContract.Contacts._ID;
	        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
	        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
	        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
	        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
	        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
	        Uri EmailCONTENT_URI =  ContactsContract.CommonDataKinds.Email.CONTENT_URI;
	        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
	        String DATA = ContactsContract.CommonDataKinds.Email.DATA;
	        StringBuffer output = new StringBuffer();
	        ContentResolver contentResolver = activity.getActivity().getContentResolver();
	        Cursor cursor = contentResolver.query(CONTENT_URI, null,null, null, null); 
	        // Loop for every contact in the phone
	        int i = 0;
	        if (cursor.getCount() > 0) {
	            while (cursor.moveToNext())
	            {
	                String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));		  
	                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));
	                if (hasPhoneNumber > 0) {
	                   
	                    // Query and loop for every phone number of the contact
	                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);
	                    while (phoneCursor.moveToNext()) {
	                    	
	                    	phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));  
	                        name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));
	                        
	                        contactListTemp.add(new ContactObj(name, phoneNumber));
	                        output.append("\n Phone number:" + phoneNumber);
	                    }
	                    phoneCursor.close();
	                    
	                }
	                output.append("\n");
	               
	                
	            }
	            
	           
	        }
	        
	        return  contactListTemp;
			
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}

		/********* Called when Item click in ListView ************/
//        private class OnItemClickListener  implements OnClickListener{           
//            private int mPosition;
//             
//            OnItemClickListener(int position){
//                 mPosition = position;
//            }
//             
//            @Override
//            public void onClick(View arg0) {
//
//       
//              AllCirclesListActivity AllCirclesActObj = activity;
//
//             /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
//
//              AllCirclesActObj.onItemClick(mPosition);
//            }               
//        }   
		
		public void setCheckBoxVisible(){
	        
      	  checkBoxVisibleFlag = true;
      	  
      }
		
}