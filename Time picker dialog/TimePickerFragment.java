package com.example.sampleapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    // Interface to send selected time back to activity
    public interface TimeSelectedListener {
        void onTimeSelected(int hourOfDay, int minute);
    }

    private TimeSelectedListener listener;

    public TimePickerFragment() {
        // Required empty public constructor
    }

    public void setListener(TimeSelectedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(
                getActivity(), // **Must use getActivity() here**
                this,
                hour,
                minute,
                DateFormat.is24HourFormat(getActivity())
        );
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (listener != null) {
            listener.onTimeSelected(hourOfDay, minute);
        }
    }
}
