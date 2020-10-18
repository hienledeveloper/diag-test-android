package test.diag.com.diagtest_android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.diag.com.diagtest_android.helper.NetworkClient
import test.diag.com.diagtest_android.model.config.RequestState
import test.diag.com.diagtest_android.model.local.ErrorModel
import test.diag.com.diagtest_android.modules.retrofit.ResultModel
import test.diag.com.diagtest_android.modules.retrofit.exception.ApiException
import java.net.UnknownHostException

/**
 * Created By Ben on 10/16/20
 */
abstract class BaseViewModel : ViewModel() {

    private val error = MutableLiveData<ErrorModel>()
    val errorObserve: LiveData<ErrorModel> = error

    private val requestState = MutableLiveData<RequestState>()
    val requestStateObserve: LiveData<RequestState> = requestState

    suspend fun <T> executeAPI(result: ResultModel<T>, resultCallback: (T) -> Unit) {
        withContext(Dispatchers.IO) {
            if (!NetworkClient.available) {
                notifyError(UnknownHostException())
                return@withContext
            }
            withContext(Dispatchers.Main) {
                requestState.value = RequestState.RUNNING
                result.onSuccess {
                    resultCallback.invoke(it)
                }.onError {
                    notifyError(it)
                }.onCompleted {
                    requestState.value = RequestState.COMPLETED
                }
            }
        }
    }

    private fun notifyError(errorThrowable: Throwable) {
        error.postValue(
            when (errorThrowable) {
                is UnknownHostException -> ErrorModel(
                    errorString = "Network offline",
                    errorThrowable
                )
                is ApiException -> ErrorModel(
                    errorString = errorThrowable.errorString,
                    errorThrowable
                )
                else -> ErrorModel("Somethings wrong!!", null)
            }
        )
    }

}