package test.diag.com.diagtest_android.modules.summary

import test.diag.com.diagtest_android.base.BaseRepository
import test.diag.com.diagtest_android.model.response.SummaryResponse
import test.diag.com.diagtest_android.modules.retrofit.ApiService

/**
 * Created By Ben on 10/16/20
 */
class SummaryRepository(private val apiService: ApiService): BaseRepository() {

    suspend fun getSummary() = executeApi<SummaryResponse> { apiService.getSummary() }

}