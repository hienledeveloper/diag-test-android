package test.diag.com.diagtest_android.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import test.diag.com.diagtest_android.modules.navigate.NavigateModel
import test.diag.com.diagtest_android.modules.navigate.NavigateScreen
import test.diag.com.diagtest_android.modules.navigate.NavigateViewModel

/**
 * Created By Ben on 10/17/20
 */
abstract class BaseNavigateFragment<T : ViewDataBinding> : BaseFragment<T>() {

    private val navigateViewModel by activityViewModels<NavigateViewModel>()

    fun navigateTo(navigateScreen: NavigateScreen, bundle: Bundle? = null) {
        navigateViewModel.navigateTo(NavigateModel(navigateScreen, bundle))
    }

}