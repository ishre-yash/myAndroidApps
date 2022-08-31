package com.ishreyash.myfirstapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDate :TextView ?= null
    private var ageInMinutes :TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        selectedDate = findViewById(R.id.selectedDate)
        ageInMinutes = findViewById(R.id.ageInMinutes)
        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,{ _,selectedYear,selectedMonth,selectedDayOfMonth ->
//            selectedDate?.setText("$selectedDayOfMonth/${selectedMonth+1}/$selectedYear")
            val newSelectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            selectedDate?.text = newSelectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

            val theDate = sdf.parse(newSelectedDate)
            theDate?.let{
                val selectedDateInMinutes = theDate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                currentDate?.let{
                    val currentDateInMinutes = currentDate.time/60000

                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                    ageInMinutes?.text = differenceInMinutes.toString()
                }
            }
        },
            year,month,day)

        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
        dpd.show()
    }
}