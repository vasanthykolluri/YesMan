package com.rock.yesman.fragments;

import java.util.Calendar;

import com.rock.yesman.MyDialogFragmentListener;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
	
	private String year;
	private String month;
	private String day;
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
    	MyDialogFragmentListener activity = (MyDialogFragmentListener) getActivity();
    	this.year = String.valueOf(year);
    	this.month = String.valueOf(month);
    	this.day = String.valueOf(day);
    	activity.onReturnDate(this.month + "/" + this.day + "/" + this.year);
    	
    }

}
