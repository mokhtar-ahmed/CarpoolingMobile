package com.iti.jets.carpoolingV1.synccontactsactivity;

import java.net.HttpURLConnection;
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

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.httphandler.HttpConstants;
import com.iti.jets.carpoolingV1.retrieveallcircles.AllCirclesListFragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;


/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class SyncContactsCustomArrayAdapter extends BaseAdapter  implements OnClickListener{
          
         /*********** Declare Used Variables *********/
         private Fragment fragment;
         private ArrayList data;
         private static LayoutInflater inflater=null;
         public Resources res;
         private CheckBox checkBox ;
         User UserValues=null;
         User UserCheckedValues=null;
         String returnServiceOutput;
         int i=0;
         public SyncContactsCustomArrayAdapter()
         {
        	 
         }
          
         /*************  CustomAdapter Constructor *****************/
         public SyncContactsCustomArrayAdapter(Fragment fragment, ArrayList d,Resources resLocal) {
              
                /********** Take passed values **********/
        	    
                 this.fragment =  fragment;
                 data=d;
                 res = resLocal;
                 
                 /***********  Layout inflator to call external xml layout () ***********/
                  inflater = ( LayoutInflater ) fragment.getActivity().
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
              
             public TextView nameTxt;
             public TextView phoneTxt;
             public ImageView userImage;
			 public CheckBox checkBox;
			 public ProgressBar pb;
			
      
         }
      
         /****** Depends upon data size called for each row , Create each ListView row *****/
         public View getView(final int position, View convertView, ViewGroup parent) {
              
             View vi = convertView;
             ViewHolder  holder;
              
             if(convertView==null){
                  
                 /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                 vi = inflater.inflate(R.layout.activity_sync_contacts, null);
                  
                 /****** View Holder Object to contain tabitem.xml file elements ******/
 
                 holder = new ViewHolder();
                 holder.nameTxt = (TextView) vi.findViewById(R.id.nameTxt);
                 holder.phoneTxt=(TextView)vi.findViewById(R.id.phoneTxt);
                 holder.userImage=(ImageView)vi.findViewById(R.id.userImage);
                 holder.checkBox=(CheckBox)vi.findViewById(R.id.chkbox);
                 holder.pb = (ProgressBar) vi.findViewById(R.id.progressBar1);
                 holder.userImage.setVisibility(View.GONE);
                 vi.setTag(holder); 
                /************  Set holder with LayoutInflater ************/
                 
             }
             else 
             {
                 holder=(ViewHolder)vi.getTag();
                 vi = convertView;
             }
             if(data.size()<=0)
             {
                 holder.nameTxt.setText("No Users Available");
                  
             }
             else
             {
                 /***** Get each Model object from Arraylist ********/
            	 holder = (ViewHolder) vi.getTag();
                 UserValues=null;
                 UserValues = ( User ) data.get( position );
                  
                 /************  Set Model values in Holder elements ***********/
 
                  holder.nameTxt.setText( UserValues.getName() );
                  holder.phoneTxt.setText(UserValues.getPhone());
                  
                 
                  holder.userImage.setTag(UserValues.getUserId());
                  holder.userImage.setId(position);
                  PbAndImage pb_and_image = new PbAndImage();
                  pb_and_image.setImg(holder.userImage);
                  pb_and_image.setPb(holder.pb);
                  new DownloadImageTask().execute(pb_and_image);
                  
                  
//                  byte [] encodeByte=Base64.decode(UserValues.getImageURL(),Base64.DEFAULT);
//         		 System.out.println(UserValues.getImageURL());
//         		 System.out.println(encodeByte);
//         	     Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//         	      // Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(userToRetrieve.getString("imageString")));
//         	     holder.userImage.setImageBitmap(bitmap);
//                  if(UserValues.getImageURL().equals("image"))
//                  {
//                	  holder.userImage.setImageResource(
//                              res.getIdentifier(
//                              "com.androidexample.customlistview:drawable/photo"
//                              ,null,null));
//                  }
                 
                  
                  /******** Set Item Click Listner for LayoutInflater for each row *******/
                  
                  vi.setOnClickListener(new OnItemClickListener( position ));
                  
                  holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
               	   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               		UserCheckedValues = (User)data.get(position);
               		UserCheckedValues.setIsSelected(isChecked);
               	   }
               	  });
             }
            
             return vi;
             
             
         }

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
          
		/********* Called when Item click in ListView ************/
        private class OnItemClickListener  implements OnClickListener{           
            private int mPosition;
             
            OnItemClickListener(int position){
                 mPosition = position;
            }
             
            @Override
            public void onClick(View arg0) {

       
              SyncContactsFragment SyncContactsActObj = (SyncContactsFragment) fragment;

             /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
             
              SyncContactsActObj.onItemClick(mPosition);
            }               
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