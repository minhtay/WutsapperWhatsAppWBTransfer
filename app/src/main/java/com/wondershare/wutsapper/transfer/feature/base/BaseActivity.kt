package com.wondershare.wutsapper.transfer.feature.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewmodel<*>> : AppCompatActivity(),
    BaseNavigator, BaseFragment.Callback {

    var viewDataBinding: T? = null
        private set
    private var viewmodel: V? = null
    var snackbar: Snackbar? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val mViewmodel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.viewmodel = if (viewmodel == null) mViewmodel else viewmodel
        viewDataBinding!!.setVariable(bindingVariable, viewmodel)
        viewDataBinding!!.executePendingBindings()
        setContentView(viewDataBinding!!.root)
    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun hideSnackbar() {
        snackbar?.let {
            if (it.isShown) {
                it.dismiss()
            }
        }
    }

    override fun showToast(msg: String) {}
    override fun showSnackbar() {}
    override fun showSnackbarWithRetry() {}
    override fun showOffline() {}

    override fun showDialog(dialog: Dialog) {
        dialog.show()
    }

    override fun hideDialog(dialog: Dialog) {
        dialog.dismiss()
    }

    override fun gone(view: View) {
        view.visibility = View.GONE
    }

    override fun visible(view: View) {
        view.visibility = View.VISIBLE
    }

    fun replaceFragment(fragment: Fragment, idView: Int, tag: String) {
        supportFragmentManager.beginTransaction().replace(idView, fragment).commit()
    }

    fun addFragment(fragment: Fragment, idView: Int, tag: String) {
        supportFragmentManager.beginTransaction().add(idView, fragment).addToBackStack(tag).commit()
    }

    fun destroyFragment(fragment: Fragment, tag: String) {
        TODO("Not yet implemented")
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    fun onBackstackFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}