package test.diag.com.diagtest_android.base

import test.diag.com.diagtest_android.modules.retrofit.ResultModel

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