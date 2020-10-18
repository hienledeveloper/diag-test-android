package test.diag.com.diagtest_android.modules.datepicker

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created By Ben on 10/18/20
 */
object DateUtil {

    private const val DATE_FORMAT = "yyyy/MM/dd HH:mm"
    private const val REQUEST_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

    fun date2CorrectFormat(date: Date): String {
        return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(date)
    }

    fun time2RequestString(mil: Long): String {
        return SimpleDateFormat(REQUEST_FORMAT, Locale.getDefault()).format(Date(mil).apply {
            this.hours = 0
            this.minutes = 0
        })
    }


}