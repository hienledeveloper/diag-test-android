package test.diag.com.diagtest_android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import test.diag.com.diagtest_android.R
import test.diag.com.diagtest_android.model.local.CovidArea
import test.diag.com.diagtest_android.view.adapter.holder.AreaChartHolder

/**
 * Created By Ben on 10/17/20
 */
class CovidAreaAdapter : RecyclerView.Adapter<AreaChartHolder>() {

    private val items = mutableListOf<CovidArea>()

    var maxCasesInCountry = 0

    override fun getItemViewType(position: Int): Int = R.layout.view_area_chart

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaChartHolder {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return AreaChartHolder(binding)
    }

    override fun onBindViewHolder(holder: AreaChartHolder, position: Int) {
        holder.bind(items[position], maxCasesInCountry)
    }

    override fun getItemCount(): Int = items.size

    fun fetch(list: List<CovidArea>) {
        items.clear()
        if (list.isNotEmpty()) {
            items.addAll(list)
            notifyDataSetChanged()
        }
    }
}