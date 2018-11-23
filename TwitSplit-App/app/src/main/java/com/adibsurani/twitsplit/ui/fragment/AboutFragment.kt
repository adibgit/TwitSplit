package com.adibsurani.twitsplit.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adibsurani.twitsplit.R
import com.adibsurani.twitsplit.di.component.DaggerFragmentComponent
import com.adibsurani.twitsplit.di.module.FragmentModule
import com.adibsurani.twitsplit.contract.fragment.AboutContract
import javax.inject.Inject

class AboutFragment:
    Fragment(),
    AboutContract.View {

    @Inject
    lateinit var aboutPresenter: AboutContract.Presenter
    private lateinit var rootView: View

    fun newInstance(): AboutFragment {
        return AboutFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_about, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aboutPresenter.attach(this)
        aboutPresenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        aboutPresenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {

    }

    override fun loadMessageSuccess(message: String) {

    }

    private fun injectDependency() {
        val aboutComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        aboutComponent.inject(this)
    }

    private fun initView() {

    }

    companion object {
        val TAG: String = "AboutFragment"
    }
}