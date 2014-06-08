package com.iti.jets.carpoolingV1.common;

import java.util.Calendar;

import com.iti.jets.carpoolingV1.editprofileactivity.EditProfileFragement;
import com.iti.jets.carpoolingV1.registrationactivity.RegisterFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import android.widget.DatePicker;

public class DatePickerFragment2 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

EditProfileFragement editProfileFragment = null;
int year,month,day;
public DatePickerFragment2(EditProfileFragement editProfileFragment) {
		// TODO Auto-generated constructor stub
	this.editProfileFragment = editProfileFragment;
	}

@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
final Calendar c = Calendar.getInstance();

 year = c.get(Calendar.YEAR);
 month = c.get(Calendar.MONTH);
 day = c.get(Calendar.DAY_OF_MONTH);



// Create a new instance of DatePickerDialog and return it
return new DatePickerDialog(getActivity(), this, year, month, day);
}

public void onDateSet(DatePicker view, int year, int month, int day) {
// Do something with the date chosen by the user
	
	editProfileFragment.year = year;
	editProfileFragment.month = month;
	editProfileFragment.day = day;
	editProfileFragment.dateTextView.setText( year +"-"+(month+1)+"-"+day);
}
}