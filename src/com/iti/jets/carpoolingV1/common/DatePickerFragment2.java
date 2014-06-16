package com.iti.jets.carpoolingV1.common;

import java.util.Calendar;
import java.util.Date;

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
int currentYear = c.get(Calendar.YEAR);
int currentMonth = c.get(Calendar.MONTH);
int currentDay = c.get(Calendar.DAY_OF_MONTH);
int currentHour = c.get(Calendar.HOUR);
int currentMinute = c.get(Calendar.MINUTE);



//Create a new instance of DatePickerDialog and return it
DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
Date minDate = new Date(1996, month, day);
//Date maxDate = new Date(1980,month,day);

//dialog.getDatePicker().init(currentYear-18, currentMonth, currentDay, null);
dialog.getDatePicker().setMinDate(new Date(currentYear+1+34 - 1980,currentMonth, currentDay).getTime());
dialog.getDatePicker().setMaxDate(new Date(currentYear+1-19 - 1900,currentMonth, currentDay).getTime());
return dialog;
}

public void onDateSet(DatePicker view, int year, int month, int day) {
// Do something with the date chosen by the user
	
	editProfileFragment.year = year;
	editProfileFragment.month = month;
	editProfileFragment.day = day;
	editProfileFragment.dateTextView.setText( year +"-"+(month+1)+"-"+day);
}
}