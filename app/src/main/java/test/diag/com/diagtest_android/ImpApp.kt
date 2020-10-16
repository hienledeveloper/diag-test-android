package test.diag.com.diagtest_android

import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * Created By Ben on 10/16/20
 */
@HiltAndroidApp
class ImpApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }

    companion object {
        lateinit var context: Context
    }

}