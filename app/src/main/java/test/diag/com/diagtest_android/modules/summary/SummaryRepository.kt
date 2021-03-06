package test.diag.com.diagtest_android.modules.summary

import test.diag.com.diagtest_android.base.BaseRepository
import test.diag.com.diagtest_android.modules.retrofit.ApiService

/**
 * Created By Ben on 10/16/20
 */
class SummaryRepository(private val apiService: ApiService) : BaseRepository() {

    suspend fun getSummary() = executeApi { apiService.getSummary() }

    suspend fun getCountries() = executeApi { apiService.getCountries() }

    suspend fun getInfoByCountry(
        strSlugCountry: String,
        strFromDate: String? = null,
        strToDate: String? = null
    ) =
        executeApi { apiService.getInfoByCountry(strSlugCountry, strFromDate, strToDate) }

}