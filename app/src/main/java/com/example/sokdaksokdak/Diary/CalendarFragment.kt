package com.example.sokdaksokdak.Diary

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sokdaksokdak.R
import com.example.sokdaksokdak.database.Diary
import com.example.sokdaksokdak.databinding.FragmentCalendarBinding
import com.example.sokdaksokdak.writeDiary.DiaryFragment
import com.example.sokdaksokdak.writeDiary.WriteDiaryViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList



class CalendarFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val calendar = Calendar.getInstance()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireActivity(),
            this,
            initialYear,
            initialMonth,
            initialDay
        )
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

        val selectedDate = SimpleDateFormat("dd-MM-yyyy").format(calendar.time)
        val selectedDateBundle = Bundle()
        selectedDateBundle.putString("SELECTED_DATE", selectedDate)

        setFragmentResult("KEY", selectedDateBundle)
    }
}