package test.diag.com.diagtest_android.modules.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.diag.com.diagtest_android.BuildConfig
import java.util.concurrent.TimeUnit

/**
 * Created By Ben on 10/16/20
 */
object RetrofitClient {

    private const val TIME_OUT = 5L

    val retrofit: Retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

    private val okHttpClient: OkHttpClient
        get() {
            val logger = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.i("http", message)
                }
            })
            logger.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

            val okHTTPClientBuilder = OkHttpClient.Builder()
            okHTTPClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            okHTTPClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
            okHTTPClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            okHTTPClientBuilder.addInterceptor(logger)

            return okHTTPClientBuilder.build()
        }

}