package test.diag.com.diagtest_android.helper

import android.view.View
import android.widget.ToggleButton
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean

/**
 * Created By Ben on 10/17/20
 */

@BindingAdapter("viewVisibility")
fun View.viewVisibility(hasShow: Boolean) {
    this.visibility = if (hasShow) View.VISIBLE else View.GONE
}

@BindingAdapter("onCheckedChange")
fun ToggleButton.onCheckedChange(observable: ObservableBoolean) {
    this.setOnCheckedChangeListener { _, isCheck ->
        observable.set(isCheck)
    }
}