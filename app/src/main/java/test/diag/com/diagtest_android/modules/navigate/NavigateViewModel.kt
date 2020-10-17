package test.diag.com.diagtest_android.modules.navigate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import test.diag.com.diagtest_android.base.BaseViewModel

/**
 * Created By Ben on 10/17/20
 */
class NavigateViewModel : BaseViewModel() {

    private val navigate = MutableLiveData<NavigateModel>()
    val navigateObserve: LiveData<NavigateModel> = navigate

    fun navigateTo(navigateModel: NavigateModel) {
        navigate.postValue(NavigateModel(navigateModel.navigateScreen, navigateModel.bundle))
    }

}