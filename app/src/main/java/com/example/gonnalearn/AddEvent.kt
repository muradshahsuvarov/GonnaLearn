package com.example.gonnalearn

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_add_event.*
import kotlinx.android.synthetic.main.fragment_add_event.view.*
import java.util.*


class AddEvent : Fragment() {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myInflater =  inflater.inflate(R.layout.fragment_add_event, container, false)

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        myInflater.eventStartDate.setOnClickListener {
            val datePicker = DatePickerDialog(myInflater.context, DatePickerDialog.OnDateSetListener {
                view, mYear, mMonth, mDay -> eventStartDate.setText("$mDay/$mMonth/$mYear")
            } , year, month, day)

            datePicker.show()
        }


        myInflater.eventEndDate.setOnClickListener {
            val datePicker = DatePickerDialog(myInflater.context, DatePickerDialog.OnDateSetListener {
                    view, mYear, mMonth, mDay -> eventEndDate.setText("$mDay/$mMonth/$mYear")
            } , year, month, day)

            datePicker.show()
        }

        myInflater.addEventButton.setOnClickListener{
            Toast.makeText(context, "Event added!", Toast.LENGTH_SHORT).show()
        }

        return myInflater
    }

}