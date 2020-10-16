package test.diag.com.diagtest_android.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created By Ben on 10/16/20
 */
abstract class BaseFragment <T : ViewDataBinding> : Fragment() {

    var binding: T? = null

    abstract fun layoutResId(): Int

    abstract fun viewDidLoad()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<T>(inflater, layoutResId(), container, false)
        binding?.root?.setBackgroundColor(Color.parseColor("#4a4a4a"))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = this
        viewDidLoad()
    }

}