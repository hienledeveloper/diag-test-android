package test.diag.com.diagtest_android.base

import test.diag.com.diagtest_android.modules.retrofit.ResponseModel
import test.diag.com.diagtest_android.modules.retrofit.ResultModel
import test.diag.com.diagtest_android.modules.retrofit.exception.ApiException

/**
 * Created By Ben on 10/16/20
 */
abstract class BaseRepository {

    suspend inline fun <reified _Response> executeApi(api: suspend () -> _Response): ResultModel<_Response> {
        return try {
            api.invoke().let { response ->
                ResultModel(data = response)
            }
        } catch (ex: Exception) {
            ResultModel(errorException = ex)
        }
    }

}