package com.adibsurani.twitsplit.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adibsurani.twitsplit.R
import com.adibsurani.twitsplit.di.component.DaggerFragmentComponent
import com.adibsurani.twitsplit.di.module.FragmentModule
import com.adibsurani.twitsplit.view.contract.TweetContract
import com.afollestad.materialdialogs.MaterialDialog
import javax.inject.Inject

class TweetFragment:
    Fragment(),
    TweetContract.View {

    @Inject
    lateinit var tweetPresenter: TweetContract.Presenter
    private lateinit var rootView: View

    fun newInstance(): TweetFragment {
        return TweetFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_tweet, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tweetPresenter.attach(this)
        tweetPresenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tweetPresenter.unsubscribe()
    }

    override fun showTweetLoad(show: Boolean) {
        if (show) {

        } else {

        }
    }

    override fun showErrorMessage(error: String) {
        MaterialDialog(context!!)
            .title(text = "Tweet Post Failed")
            .message(text = "Please Try Again")
            .show()
    }

    override fun postTweetSuccess(tweets: List<String>) {

    }

    private fun injectDependency() {
        val tweetComponent = DaggerFragmentComponent
            .builder()
            .fragmentModule(FragmentModule())
            .build()
        tweetComponent.inject(this)
    }

    private fun initView() {

    }
}