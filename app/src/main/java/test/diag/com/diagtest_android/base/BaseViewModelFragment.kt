package test.diag.com.diagtest_android.base

import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import test.diag.com.diagtest_android.R
import test.diag.com.diagtest_android.model.local.ErrorModel

/**
 * Created By Ben on 10/18/20
 */
abstract class BaseViewModelFragment<T : ViewDataBinding> : BaseNavigateFragment<T>() {

    fun observeErrorViewModel(viewModel: BaseViewModel, tag: String? = null) {
        viewModel.errorObserve.observe(this, {
            onErrorResponse(it, tag)
        })
    }

    open fun onErrorResponse(errorModel: ErrorModel, tag: String?) {}

    fun showAlertDialog(strContent: String?, retryCallback: (() -> Unit)? = null) {
        context?.let { ctx ->
            AlertDialog.Builder(ctx).setTitle(strContent?:"Somethings wrong!!").setCancelable(false).setPositiveButton("Retry") { _, _ ->
                retryCallback?.invoke()
            }.show()
        }
    }
}