package com.iti.jets.carpoolingV1.firstrun;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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
import com.iti.jets.carpoolingV1.addcircleactivity.AddCircleFragment;
import com.iti.jets.carpoolingV1.R;
import com.iti.jets.carpoolingV1.common.Circle2;
import com.iti.jets.carpoolingV1.common.CircleItemObj;
import com.iti.jets.carpoolingV1.common.User;
import com.iti.jets.carpoolingV1.firstrun.AllCirclesListFragment2;



//********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/

public class CirclesCustomArrayAdapter2 extends BaseAdapter  implements OnClickListener{
          
         /*********** Declare Used Variables *********/
       
         private AllCirclesListFragment2 activity2;
         private ArrayList data;
         private static LayoutInflater inflater=null;
         boolean checkBoxVisibleFlag = false;
         String visibleFlag = "empty";
		 boolean flag2 = true;
		public static boolean deleteflag;
         public Resources res;
         Circle2 CircleValues=null;
         int i=0;
         public CirclesCustomArrayAdapter2()
         {
        	 
         }
          
         /*************  CustomAdapter Constructor *****************/
   
         
         public CirclesCustomArrayAdapter2(AllCirclesListFragment2 a, ArrayList d,Resources resLocal) {
             
             /********** Take passed values **********/
        	 flag2 = true;
              activity2 = a;
              data=d;
              res = resLocal;
           
              /***********  Layout inflator to call external xml layout () ***********/
               inflater = ( LayoutInflater )activity2.getActivity().
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
              
        	 public TextView circleNameTxt;        
             public ImageView circleImage;
             public CheckBox checkBox;
             public ImageView nextArrowImg;
             
      
             
         }
      
         /****** Depends upon data size called for each row , Create each ListView row *****/
         public View getView(final int position, View convertView, ViewGroup parent) {
              
             View vi = convertView;
             ViewHolder holder;
              
             if(convertView==null){
                  
                 /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                 vi = inflater.inflate(R.layout.customlist_circles, null);
                  
                 /****** View Holder Object to contain tabitem.xml file elements ******/
 
                 holder = new ViewHolder();
                 holder.circleNameTxt = (TextView) vi.findViewById(R.id.circleNameTxt);
                 holder.circleImage=(ImageView)vi.findViewById(R.id.circleImage);
                 holder.checkBox = (CheckBox)vi.findViewById(R.id.chkbox);
                 //holder.nextArrowImg = (ImageView)vi.findViewById( ); 
                 //holder.checkBox.setVisibility(1);

                /************  Set holder with LayoutInflater ************/
                 vi.setTag( holder );
    
             }
             else{ 
                 holder=(ViewHolder)vi.getTag();
                 if((checkBoxVisibleFlag == true))
                   {
                  	 holder.checkBox.setVisibility(1); 
                  	
                   }
//             if((checkBoxVisibleFlag == true) && (visibleFlag.equals("empty")))
//             {
//            	 holder.checkBox.setVisibility(1); 
//            	 visibleFlag = "true";
//             }
//             if((checkBoxVisibleFlag == true )&&(visibleFlag.equals("true")))
//             {
//            	 //holder.nextArrowImg.setVisibility(View.INVISIBLE);
//            	 holder.checkBox.setVisibility(View.INVISIBLE); 
//            	 
//             }
//             else if((checkBoxVisibleFlag == true )&&(visibleFlag.equals("false")))
//             {
//            	 holder.checkBox.setVisibility(1); 
//             }
             }
             if(data.size()<=0)
             {
                 holder.circleNameTxt.setText("Connection Error");
                  
             }
             else
             {
                 /***** Get each Model object from Arraylist ********/
            	
                 CircleValues=null;
                 CircleValues = ( Circle2 ) data.get( position );
                 if(checkBoxVisibleFlag)
                 {
                	 if(CircleValues.getCircleName().equalsIgnoreCase("Friends"))
                     {
                   	  holder.checkBox.setVisibility(View.INVISIBLE); 
                   	  
                     }
                     else if(CircleValues.getCircleName().equalsIgnoreCase("Family"))
                     {
                   	  holder.checkBox.setVisibility(View.INVISIBLE);
                     }
                     else if(CircleValues.getCircleName().equalsIgnoreCase("Work"))
                     {
                   	  holder.checkBox.setVisibility(View.INVISIBLE);
                     }
                 }
                 
                 /************  Set Model values in Holder elements ***********/
//                  for(int i=0;i<AddCircleFragment.circleItemList.size();i++)
//                  {
//                	  if(CircleValues.getCircleName().equals()))
//                  }
//                  for (CircleItemObj c : AddCircleFragment.circleItemList) {
//                	    
//                	  if(CircleValues.getCircleName().equals(c.getName()))
//                	  {
//                		  	
//                	  }
//                	  else
//                	  {
//                		  
//                	  }
//                	  
//                	}
                  holder.circleNameTxt.setText( CircleValues.getCircleName() );
                  if(checkBoxVisibleFlag)
                  {
                	  if((CircleValues.getCircleName().equalsIgnoreCase("Family"))||
                			  (CircleValues.getCircleName().equalsIgnoreCase("Friends"))||
                			  (CircleValues.getCircleName().equalsIgnoreCase("Work")))
                	  {
//                		  holder.circleNameTxt.setTextColor(Color.parseColor("#FFF333")); 
                          vi.setBackgroundColor(Color.parseColor("#BDBCBA"));  
                	  }
                	  
                	  		
                  }
                  if(CircleValues.getResId() != 0)
                  {
                      
                    	  holder.circleImage.setImageResource(CircleValues.getResId());
                     
                  }
                  else if (CircleValues.getCircleName().equals("Friends")) {
                	  holder.circleImage.setImageResource(R.drawable.p5);
                  } else if (CircleValues.getCircleName().equals("Family")) {
                	  holder.circleImage.setImageResource(R.drawable.p8);
                  } else if (CircleValues.getCircleName().equals("Work")) {
                	  holder.circleImage.setImageResource(R.drawable.p10);
                  }
                  else
                  {
                	  holder.circleImage.setImageResource(R.drawable.ic_people);
                  }
                  /******** Set Item Click Listner for LayoutInflater for each row *******/
                  holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                	   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                		 CircleValues = (Circle2)data.get(position);
                		 if(flag2)
                		 {
                			 AllCirclesListFragment2 AllCirclesActObj = activity2;
                    		 AllCirclesActObj.oncheckCircle(CircleValues);
                		 }
                		 
                		
                	   }
                	  });

             }
             vi.setOnClickListener(new OnItemClickListener( position ));
             
             return vi;
         }

		@Override
		public void onClick(View arg0) {
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

            
                if(flag2)
                {
                	AllCirclesListFragment2 AllCirclesActObj = activity2;

                    /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

                     AllCirclesActObj.onItemClick(mPosition);
                }
          
            	  

            }               
        }   
        
        public void setCheckBoxVisible(){
        
        	  checkBoxVisibleFlag = true;
        	  
        }
          
}