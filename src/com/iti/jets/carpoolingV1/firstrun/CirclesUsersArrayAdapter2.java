package com.iti.jets.carpoolingV1.firstrun;

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
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleController;
import com.iti.jets.carpoolingV1.common.Circle2;
import com.iti.jets.carpoolingV1.common.ContactObj;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.firstrun.CircleUsersFragment2;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.retrieveallcircles.CirclesUsersArrayAdapter.DownloadImageTask;
import com.iti.jets.carpoolingV1.retrieveallcircles.CirclesUsersArrayAdapter.ViewHolder;
import com.iti.jets.carpoolingV1.synccontactsactivity.AddUserToCircleController;
import com.iti.jets.carpoolingV1.synccontactsactivity.PbAndImage;
import com.iti.jets.carpoolingV1.synccontactsactivity.SyncContactsController;



//********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/

public class CirclesUsersArrayAdapter2 extends BaseAdapter  implements OnClickListener{
          
         /*********** Declare Used Variables *********/
		 private ArrayList<ContactObj> contactListTemp = new ArrayList<ContactObj>();
		 ContentResolver contentResolver;
         private CircleUsersFragment2 activity;
         private ArrayList data;
         private static LayoutInflater inflater=null;
         public Resources res;
         boolean checkBoxVisibleFlag = false;
         String returnServiceOutput;
         User UserValues=null;
         int i=0;
          
         /*************  CustomAdapter Constructor 
         * @param circleId *****************/
         public CirclesUsersArrayAdapter2(CircleUsersFragment2 activity2, ArrayList d,Resources resLocal) {
              
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
             public ProgressBar pb;
      
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
                 holder.pb = (ProgressBar) vi.findViewById(R.id.progressBar1);
                 holder.checkBox = (CheckBox) vi.findViewById(R.id.chkbox);
                /************  Set holder with LayoutInflater ************/
                 vi.setTag( holder );
             }
             else 
             {
            	 holder=(ViewHolder)vi.getTag();
                 vi = convertView;
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
                  holder.userImage.setTag(UserValues.getUserId());
                  holder.userImage.setId(position);
                  PbAndImage pb_and_image = new PbAndImage();
                  pb_and_image.setImg(holder.userImage);
                  pb_and_image.setPb(holder.pb);
                  new DownloadImageTask().execute(pb_and_image);
                      
                	  
                	  
                	  
                	  holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                   	   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   		  
                   		   if( holder.checkBox.isChecked())
                   		   {
                   			     UserValues = (User)data.get(position);
                   			     UserValues.setIsSelected(true);
                        		 CircleUsersFragment2 fragment = activity;
//                        		 fragment.oncheckCircle(UserValues);
                   		   }
                   		 
                   		
                   	   }
                   	  });
                  }
                  
                  /******** Set Item Click Listner for LayoutInflater for each row *******/
 
                  //vi.setOnClickListener(new OnItemClickListener( position ));
             
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
		public class DownloadImageTask extends AsyncTask<PbAndImage, Void, String> {
       	 
            ImageView imageView = null;
            ProgressBar pb = null;
         
            protected String doInBackground(PbAndImage... pb_and_images) {
                this.imageView = (ImageView)pb_and_images[0].getImg();
                this.pb = (ProgressBar)pb_and_images[0].getPb();
                String url = HttpConstants.SERVER_URL+HttpConstants.GET_IMAGE_URL;
                String bitmapRes = getBitmapDownloaded(url,((Integer)imageView.getTag()).intValue());
               
                
                return bitmapRes;
            }
         
            protected void onPostExecute(String result) {
            	 JSONObject resultJs = null;
            	 byte[] encodeByte = null;
                 try {
 					 resultJs = new JSONObject(result);
 					 if(resultJs.getString("image")!= null)
 					 {
 						encodeByte = encodeByte = Base64.decode(resultJs.getString("image"),Base64.DEFAULT); 
 						 Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
 		         	      System.out.println("Downloaded " + imageView.getId());
 		         	      imageView.setVisibility(View.VISIBLE); 
 		         	      pb.setVisibility(View.GONE);  // hide the progressbar after downloading the image.
 		         	      imageView.setImageBitmap(bitmap); //set the bitmap to the imageview.
 					 }
 					 else
 					 {
 						imageView.setImageResource(
 									res.getIdentifier(
	                              "com.androidexample.customlistview:drawable/photo"
	                              ,null,null));
 					 }
 					
// 					 System.out.println(UserValues.getImageURL());
//            		 System.out.println(encodeByte);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
         		  
         	     
            }
         
            /** This function downloads the image and returns the Bitmap 
             * @param i 
             * @param integer 
             * @param object **/
            private String getBitmapDownloaded(String url, int i) {
                System.out.println("String URL " + url);
                Bitmap bitmap = null;
                try {
                
        		    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url);
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);	
                    JSONObject userIdJs = new JSONObject();
                    userIdJs.put("userId", i);
        			nameValuePairs.add(new BasicNameValuePair("userId",userIdJs.toString()));
        			
        			try {	 		
        			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse httpResponse= httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    returnServiceOutput = EntityUtils.toString(httpEntity);   
                    Log.d(returnServiceOutput,"%%%%%%%%%%%%%%%returnService%%%%%%%%%%%%%%%%%%%");
        			} catch (Exception e) {
        				
        				e.printStackTrace();
        			}   
                    return returnServiceOutput;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return returnServiceOutput;
            }
             

        }	
}