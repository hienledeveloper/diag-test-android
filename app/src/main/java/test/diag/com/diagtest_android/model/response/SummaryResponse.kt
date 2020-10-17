package test.diag.com.diagtest_android.model.response

import com.google.gson.annotations.SerializedName
import test.diag.com.diagtest_android.model.local.Country
import test.diag.com.diagtest_android.model.local.Global
import test.diag.com.diagtest_android.modules.retrofit.ResponseModel

/**
 * Created By Ben on 10/16/20
 */
data class SummaryResponse(
    @SerializedName("Global")
    var global: Global?,
    @SerializedName("Countries")
    var countries: List<Country> = emptyList()
) : ResponseModel()