package test.diag.com.diagtest_android.ui.fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import test.diag.com.diagtest_android.R
import test.diag.com.diagtest_android.base.BaseFragment
import test.diag.com.diagtest_android.databinding.FragmentSummaryBinding
import test.diag.com.diagtest_android.model.local.Country
import test.diag.com.diagtest_android.modules.summary.SummaryViewModel
import test.diag.com.diagtest_android.view.adapter.CountryAdapter

/**
 * Created By Ben on 10/16/20
 */
@AndroidEntryPoint
class SummaryFragment : BaseFragment<FragmentSummaryBinding>() {

    private val summaryViewModel: SummaryViewModel by viewModels()

    private var countryAdapter: CountryAdapter? = null

    override fun layoutResId(): Int = R.layout.fragment_summary

    override fun viewDidLoad() {
        summaryViewModel.observeCountries.observe(this, Observer { list ->
            fetchUI(list)
        })
        summaryViewModel.getSummary()
    }

    private fun fetchUI(list: List<Country>) {
        if (countryAdapter == null) {
            countryAdapter = CountryAdapter()
            countryAdapter?.onItemClick = { country ->
                Toast.makeText(requireContext(), country.country, Toast.LENGTH_LONG).show()
            }
            binding?.rclCountry?.apply {
                this.setHasFixedSize(true)
                this.adapter = countryAdapter
            }
        }
        countryAdapter?.fetch(list)
    }

}