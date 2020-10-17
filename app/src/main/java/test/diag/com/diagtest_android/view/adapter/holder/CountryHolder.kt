package test.diag.com.diagtest_android.view.adapter.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import test.diag.com.diagtest_android.databinding.ViewCountryBinding
import test.diag.com.diagtest_android.model.local.Country

/**
 * Created By Ben on 10/17/20
 */
class CountryHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindCountry(country: Country) {
        if (binding is ViewCountryBinding) {
            binding.data = country
        }
    }

}