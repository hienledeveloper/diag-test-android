package test.diag.com.diagtest_android.ui.fragment

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import test.diag.com.diagtest_android.R
import test.diag.com.diagtest_android.base.BaseNavigateFragment
import test.diag.com.diagtest_android.base.BaseViewModelFragment
import test.diag.com.diagtest_android.databinding.FragmentSummaryBinding
import test.diag.com.diagtest_android.model.config.BundleKey
import test.diag.com.diagtest_android.model.local.Country
import test.diag.com.diagtest_android.model.local.ErrorModel
import test.diag.com.diagtest_android.modules.navigate.NavigateScreen
import test.diag.com.diagtest_android.modules.summary.SummaryViewModel
import test.diag.com.diagtest_android.view.adapter.CountryAdapter

/**
 * Created By Ben on 10/16/20
 */
@AndroidEntryPoint
class SummaryFragment : BaseViewModelFragment<FragmentSummaryBinding>() {

    private val summaryViewModel: SummaryViewModel by viewModels()

    private var countryAdapter: CountryAdapter? = null

    override fun layoutResId(): Int = R.layout.fragment_summary

    override fun viewDidLoad() {
        observeErrorViewModel(summaryViewModel, SummaryViewModel::class.simpleName)
        summaryViewModel.observeCountries.observe(this, Observer { list ->
            fetchUI(list)
        })
        summaryViewModel.getSummary()
    }

    private fun fetchUI(list: List<Country>) {
        if (countryAdapter == null) {
            countryAdapter = CountryAdapter()
            countryAdapter?.onItemClick = { country ->
                navigateTo(
                    NavigateScreen.INFO_FILTER_BY_COUNTRY,
                    bundleOf(BundleKey.COUNTRY_SLUG.name to country.slug)
                )
            }
            binding?.rclCountry?.apply {
                this.setHasFixedSize(true)
                this.adapter = countryAdapter
            }
        }
        countryAdapter?.fetch(list)
    }

    override fun onErrorResponse(errorModel: ErrorModel, tag: String?) {
        showAlertDialog(errorModel.errorString) {
            summaryViewModel.getSummary()
        }
    }

}