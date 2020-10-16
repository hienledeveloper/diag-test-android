package test.diag.com.diagtest_android.modules.retrofit.exception

import okio.IOException

/**
 * Created By Ben on 10/16/20
 */
data class ApiException(val errorString: String? = null) : IOException()