package test.diag.com.diagtest_android.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import test.diag.com.diagtest_android.modules.navigate.NavigateModel
import test.diag.com.diagtest_android.modules.navigate.NavigateScreen
import test.diag.com.diagtest_android.modules.navigate.NavigateViewModel
import test.diag.com.diagtest_android.ui.fragment.InfoFilterByCountryFragment

/**
 * Created By Ben on 10/17/20
 */
abstract class BaseNavigateActivity : AppCompatActivity() {

    private val navigateViewModel by viewModels<NavigateViewModel>()

    abstract val navigateFragmentContainerId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateViewModel.navigateObserve.observe(this, Observer { navigateModel ->
            navigateTo(navigateModel)
        })
    }

    private fun navigateTo(navigateModel: NavigateModel) {
        when (navigateModel.navigateScreen) {
            NavigateScreen.INFO_FILTER_BY_COUNTRY -> {
                supportFragmentManager.beginTransaction()
                    .add(navigateFragmentContainerId, InfoFilterByCountryFragment().apply {
                        this.arguments = navigateModel.bundle
                    }).addToBackStack(null).commit()
            }
        }
    }

}