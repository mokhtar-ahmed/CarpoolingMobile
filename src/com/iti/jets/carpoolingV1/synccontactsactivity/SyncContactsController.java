package com.iti.jets.carpoolingV1.synccontactsactivity;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.common.ContactObj;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.httphandler.*;
import com.iti.jets.carpoolingV1.jsonhandler.*;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.*;
import android.widget.*;


public class SyncContactsController {

	
	private ArrayList<ContactObj> contactListNumber = new ArrayList<ContactObj>();
	private ArrayList<ContactObj> contactListTemp = new ArrayList<ContactObj>();
	private JSONArray contactListJSArray;
	private JsonConverter jsonConverterObject;
	private SyncContactsServiceHandler syncContactsHanlerObject;
	private String url = HttpConstants.SERVER_URL+ HttpConstants.SYNC_CONTACTS_URL;
	private ArrayAdapter<String> arrayadapter;
	ContentResolver contentResolver;
	

	public SyncContactsController()
	{
		
	}
	public SyncContactsController ( ContentResolver contentResolver , SyncContactsFragment syncContactsActivity){
		
		this.contentResolver = contentResolver;
		JSONArray contactListJSArray = new JSONArray();
		//Fetching Contact List From User's Phone
		contactListNumber = this.fetchContacts();
		//Converting ArrayList To JSONarray
		jsonConverterObject = new JsonConverter();
//		contactListJSArray = jsonConverterObject.arrayListToJSONArray(contactListNumber); 
		for (int i=0;i<contactListNumber.size();i++)
		{
			ContactObj contactObj = contactListNumber.get(i);
			JSONObject tempJsObj = new JSONObject();
			try {
				tempJsObj.put("contactName", contactObj.getName());
				tempJsObj.put("contactPhone", contactObj.getPhoneNo());
			
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			contactListJSArray.put(tempJsObj);
		}
		Log.d("URLLLLLLLLLLLLLLL",url);
		syncContactsHanlerObject = new SyncContactsServiceHandler();
		syncContactsHanlerObject.connectToWebService(contactListJSArray,syncContactsActivity,url);
		

	}
	
	
	
	public ArrayList<ContactObj> fetchContacts() {
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
	

}
