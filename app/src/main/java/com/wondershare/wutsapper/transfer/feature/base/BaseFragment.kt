package com.wondershare.wutsapper.transfer.feature.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewmodel<*>> : Fragment(), BaseNavigator {

    var baseActivity: BaseActivity<*, *>? = null
        private set

    var viewDataBinding: T? = null
        private set
    private var viewmodel: V? = null
    private var mRootView: View? = null

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.baseActivity = context
            context.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = mViewmodel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = viewDataBinding?.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.setVariable(bindingVariable, viewmodel)
        viewDataBinding?.executePendingBindings()
    }

    override fun showToast(msg: String) {
    }

    override fun showSnackbar() {
    }

    override fun showSnackbarWithRetry() {
    }

    override fun showOffline() {
    }

    override fun showDialog(dialog: Dialog) {
        baseActivity!!.showDialog(dialog)
    }

    override fun hideSnackbar() {
    }

    override fun hideKeyboard() {
    }

    override fun hideDialog(dialog: Dialog) {
        baseActivity!!.hideDialog(dialog)
    }

    override fun gone(view: View) {
    }

    override fun visible(view: View) {
    }

    interface Callback{
        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}