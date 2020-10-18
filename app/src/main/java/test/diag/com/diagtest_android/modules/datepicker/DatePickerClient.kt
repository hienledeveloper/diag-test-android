package test.diag.com.diagtest_android.modules.datepicker

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

/**
 * Created By Ben on 10/18/20
 */
class DatePickerClient {

    var maxDate: Long = 0L
    var minDate: Long = 0L
    var fromDate: Long = Calendar.getInstance().timeInMillis
    var toDate: Long = Calendar.getInstance().timeInMillis

    fun showSelectDateRange(activity: FragmentActivity?, callback:(Long, Long) -> Unit) {
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        val now = Calendar.getInstance()
        builder.setCalendarConstraints(
            CalendarConstraints.Builder().setStart(minDate).setEnd(maxDate).setValidator(
                RangeValidator(minDate, maxDate)
            ).build()
        )
        builder.setSelection(androidx.core.util.Pair(fromDate, toDate))
        val picker = builder.build()
        picker.addOnNegativeButtonClickListener { picker.dismiss() }
        picker.addOnPositiveButtonClickListener {
            fromDate = it.first ?: 0L
            toDate = it.second ?: 0L
            callback.invoke(fromDate, toDate)
        }
        picker.show(activity?.supportFragmentManager!!, picker.toString())
    }

}