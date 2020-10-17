package test.diag.com.diagtest_android.ui.activity

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import test.diag.com.diagtest_android.R
import test.diag.com.diagtest_android.base.BaseNavigateActivity

@AndroidEntryPoint
class MainActivity : BaseNavigateActivity() {

    override val navigateFragmentContainerId: Int = R.id.mainFragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}