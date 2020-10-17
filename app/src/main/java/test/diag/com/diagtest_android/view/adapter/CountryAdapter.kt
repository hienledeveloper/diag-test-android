package test.diag.com.diagtest_android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import test.diag.com.diagtest_android.R
import test.diag.com.diagtest_android.model.local.Country
import test.diag.com.diagtest_android.view.adapter.holder.CountryHolder

/**
 * Created By Ben on 10/17/20
 */
class CountryAdapter : RecyclerView.Adapter<CountryHolder>() {

    private val items = mutableListOf<Country>()

    var onItemClick: ((Country) -> Unit)? = null

    override fun getItemViewType(position: Int): Int = R.layout.view_country

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bindCountry(items[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    fun fetch(list: List<Country>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}