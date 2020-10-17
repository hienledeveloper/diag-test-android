package test.diag.com.diagtest_android

import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import test.diag.com.diagtest_android.helper.NetworkClient

/**
 * Created By Ben on 10/16/20
 */
@HiltAndroidApp
class ImpApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        NetworkClient.initialize(applicationContext)
    }

    companion object {
        lateinit var context: Context
    }

}