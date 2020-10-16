package test.diag.com.diagtest_android.modules.retrofit

import retrofit2.http.GET
import test.diag.com.diagtest_android.model.local.Country
import test.diag.com.diagtest_android.model.response.SummaryResponse

/**
 * Created By Ben on 10/16/20
 */
interface ApiService {

    @GET("summary")
    suspend fun getSummary(): SummaryResponse

}