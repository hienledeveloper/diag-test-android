package test.diag.com.diagtest_android.modules.datepicker

import android.os.Parcel
import android.os.Parcelable
import com.google.android.material.datepicker.CalendarConstraints

/**
 * Created By Ben on 10/18/20
 */
//https://stackoverflow.com/questions/36004398/restrict-range-in-android-datepicker-custom-dialog
class RangeValidator (private val minDate:Long, private val maxDate:Long) : CalendarConstraints.DateValidator{


    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented")
    }

    override fun describeContents(): Int {
        TODO("not implemented")}

    override fun isValid(date: Long): Boolean {
        return !(minDate > date || maxDate < date)

    }

    companion object CREATOR : Parcelable.Creator<RangeValidator> {
        override fun createFromParcel(parcel: Parcel): RangeValidator {
            return RangeValidator(parcel)
        }

        override fun newArray(size: Int): Array<RangeValidator?> {
            return arrayOfNulls(size)
        }
    }

}
