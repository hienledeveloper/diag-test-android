package test.diag.com.diagtest_android.modules.summary

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.diag.com.diagtest_android.base.BaseViewModel
import test.diag.com.diagtest_android.model.local.Country
import test.diag.com.diagtest_android.model.local.CovidArea
import test.diag.com.diagtest_android.model.local.Global

/**
 * Created By Ben on 10/16/20
 */
class SummaryViewModel @ViewModelInject constructor(private val summaryRepository: SummaryRepository) :
    BaseViewModel() {

    private val countries = MutableLiveData<List<Country>>()
    val observeCountries: LiveData<List<Country>> = countries

    private val global = MutableLiveData<Global>()
    val observeGlobal: LiveData<Global> = global

    private val covidArea = MutableLiveData<List<CovidArea>>()
    val observeCovidArea: LiveData<List<CovidArea>> = covidArea

    fun getSummary() {
        viewModelScope.launch {
            executeAPI(summaryRepository.getSummary()) { summary ->
                countries.postValue(summary.countries)
                global.postValue(summary.global)
            }
        }
    }

    fun getInfoBySlugCountry(strSlugCountry: String) {
        viewModelScope.launch {
            executeAPI(summaryRepository.getInfoByCountry(strSlugCountry)) { areas ->
                covidArea.postValue(areas)
            }
        }
    }

}