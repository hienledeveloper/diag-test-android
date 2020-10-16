package test.diag.com.diagtest_android.modules.retrofit

/**
 * Created By Ben on 10/16/20
 */
class ResultModel<T>(
    private val data: T? = null,
    private var errorException: Throwable? = null
) {

    fun onSuccess(callback: (T) -> Unit): ResultModel<T> {
        data?.let {
            callback.invoke(it)
        }
        return this
    }

    fun onError(callback: (Throwable) -> Unit): ResultModel<T> {
        errorException?.let {
            callback.invoke(it)
        }
        return this
    }
}
