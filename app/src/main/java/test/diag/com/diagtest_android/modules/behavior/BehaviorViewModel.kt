package test.diag.com.diagtest_android.modules.behavior

import androidx.databinding.ObservableBoolean
import test.diag.com.diagtest_android.base.BaseViewModel

/**
 * Created By Ben on 10/17/20
 */
class BehaviorViewModel : BaseViewModel() {

    val onGlobalBoardCheckedChange = ObservableBoolean(false)
    val onCountryBoardCheckedChange = ObservableBoolean(false)

}