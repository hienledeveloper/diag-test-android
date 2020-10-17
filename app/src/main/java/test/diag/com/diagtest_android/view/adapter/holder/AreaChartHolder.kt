package test.diag.com.diagtest_android.view.adapter.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import test.diag.com.diagtest_android.databinding.ViewAreaChartBinding
import test.diag.com.diagtest_android.model.local.CovidArea

/**
 * Created By Ben on 10/17/20
 */
class AreaChartHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(covidArea: CovidArea, maxCasesInCountry: Int) {
        if (binding is ViewAreaChartBinding) {
            binding.progressCase.max = maxCasesInCountry
            binding.data = covidArea
        }
    }

}