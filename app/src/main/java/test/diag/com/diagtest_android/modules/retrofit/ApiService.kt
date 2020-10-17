package test.diag.com.diagtest_android.modules.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import test.diag.com.diagtest_android.model.local.Country
import test.diag.com.diagtest_android.model.local.CovidArea
import test.diag.com.diagtest_android.model.response.SummaryResponse

/**
 * Created By Ben on 10/16/20
 */
interface ApiService {

    @GET("summary")
    suspend fun getSummary(): SummaryResponse

    @GET("countries")
    suspend fun getCountries(): List<Country>

    @GET("country/{slug}/status/confirmed")
    suspend fun getInfoByCountry(
        @Path("slug") slugCountry: String,
        @Query("from") fromDate: String?,
        @Query("to") toDate: String?
    ): List<CovidArea>

}