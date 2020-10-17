package test.diag.com.diagtest_android.ui.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import test.diag.com.diagtest_android.R
import test.diag.com.diagtest_android.base.BaseFragment
import test.diag.com.diagtest_android.databinding.FragmentInfoFilterByCountryBinding
import test.diag.com.diagtest_android.model.config.BundleKey
import test.diag.com.diagtest_android.model.local.CovidArea
import test.diag.com.diagtest_android.modules.behavior.BehaviorViewModel
import test.diag.com.diagtest_android.modules.summary.SummaryViewModel
import test.diag.com.diagtest_android.view.adapter.CovidAreaAdapter

/**
 * Created By Ben on 10/17/20
 */
@AndroidEntryPoint
class InfoFilterByCountryFragment : BaseFragment<FragmentInfoFilterByCountryBinding>() {

    private val summaryViewModel: SummaryViewModel by viewModels()

    private val behaviorViewModel: BehaviorViewModel by viewModels()

    private var strSlugCountry: String = ""

    private var areaAdapter: CovidAreaAdapter? = null

    override fun layoutResId(): Int = R.layout.fragment_info_filter_by_country

    override fun viewDidLoad() {
        setUp()
        observeViewModel()
        summaryViewModel.getSummary()
        summaryViewModel.getInfoBySlugCountry(strSlugCountry)
    }

    private fun setUp() {
        binding?.behaviorViewModel = behaviorViewModel
        arguments?.getString(BundleKey.COUNTRY_SLUG.name)?.let { slug ->
            strSlugCountry = slug
        }
        binding?.btnBack?.setOnClickListener { activity?.onBackPressed() }
    }

    private fun observeViewModel() {
        summaryViewModel.observeCountries.observe(this, Observer { listCountries ->
            binding?.country = listCountries.find { country ->
                country.slug == strSlugCountry
            }
        })
        summaryViewModel.observeGlobal.observe(this, Observer { global ->
            binding?.global = global
        })
        summaryViewModel.observeCovidArea.observe(this, Observer { listAreas ->
            listAreas.sortedByDescending { it.cases }.let {
                fetchUIByAreas(it)
            }

        })
    }

    private fun fetchUIByAreas(areas: List<CovidArea>) {
        if (areaAdapter == null) {
            areaAdapter = CovidAreaAdapter()
            binding?.rclArea?.apply {
                this.setHasFixedSize(true)
                this.adapter = areaAdapter
            }
        }
        areaAdapter?.fetch(areas)
    }

}