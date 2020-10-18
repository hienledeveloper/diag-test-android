package test.diag.com.diagtest_android.ui.fragment

import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import test.diag.com.diagtest_android.R
import test.diag.com.diagtest_android.base.BaseViewModelFragment
import test.diag.com.diagtest_android.databinding.FragmentInfoFilterByCountryBinding
import test.diag.com.diagtest_android.model.config.BundleKey
import test.diag.com.diagtest_android.model.local.CovidArea
import test.diag.com.diagtest_android.model.local.ErrorModel
import test.diag.com.diagtest_android.modules.behavior.BehaviorViewModel
import test.diag.com.diagtest_android.modules.datepicker.DatePickerClient
import test.diag.com.diagtest_android.modules.datepicker.DateUtil
import test.diag.com.diagtest_android.modules.summary.SummaryViewModel
import test.diag.com.diagtest_android.view.adapter.CovidAreaAdapter

/**
 * Created By Ben on 10/17/20
 */
@AndroidEntryPoint
class InfoFilterByCountryFragment : BaseViewModelFragment<FragmentInfoFilterByCountryBinding>() {

    private val summaryViewModel: SummaryViewModel by viewModels()

    private val behaviorViewModel: BehaviorViewModel by viewModels()

    private var hasFilter = false
    private var strSlugCountry: String = ""
    private var maxCases: Int = 0

    private var areaAdapter: CovidAreaAdapter? = null

    private var datePickerClient: DatePickerClient = DatePickerClient()

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
        binding?.btnFilter?.setOnClickListener {
            datePickerClient.showSelectDateRange(activity) { fromDateInMili, toDateInMili ->
                hasFilter = true
                binding?.chipFilter?.visibility = View.VISIBLE
                summaryViewModel.getInfoBySlugCountry(
                    strSlugCountry,
                    DateUtil.time2RequestString(fromDateInMili),
                    DateUtil.time2RequestString(toDateInMili)
                )
            }
        }
        binding?.chipFilter?.setOnClickListener {
            hasFilter = false
            binding?.chipFilter?.visibility = View.INVISIBLE
            summaryViewModel.getInfoBySlugCountry(strSlugCountry)
        }
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
            listAreas.asReversed().let {
                if (!hasFilter) {
                    datePickerClient.maxDate = it.first().date.time
                    datePickerClient.minDate = it.last().date.time
                    maxCases = it.first().cases
                }
                fetchUIByAreas(listAreas.asReversed())
            }


        })
    }

    private fun fetchUIByAreas(areas: List<CovidArea>) {
        if (areaAdapter == null) {
            areaAdapter = CovidAreaAdapter()
            areaAdapter?.maxCasesInCountry = maxCases
            binding?.rclArea?.apply {
                this.setHasFixedSize(true)
                this.adapter = areaAdapter
            }
        }
        areaAdapter?.fetch(areas)
    }

    override fun onErrorResponse(errorModel: ErrorModel, tag: String?) {
        showAlertDialog(errorModel.errorString) {
            summaryViewModel.getSummary()
            summaryViewModel.getInfoBySlugCountry(strSlugCountry)
        }
    }

}