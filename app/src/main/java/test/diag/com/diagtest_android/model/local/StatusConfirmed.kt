package test.diag.com.diagtest_android.model.local

import com.google.gson.annotations.SerializedName

/**
 * Created By Ben on 10/17/20
 */
open class StatusConfirmed {
    @SerializedName("NewConfirmed")
    val newConfirmed: Int = 0

    @SerializedName("NewDeaths")
    val newDeaths: Int = 0

    @SerializedName("NewRecovered")
    val newRecovered: Int = 0

    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int = 0

    @SerializedName("TotalDeaths")
    val totalDeaths: Int = 0

    @SerializedName("TotalRecovered")
    val totalRecovered: Int = 0
}