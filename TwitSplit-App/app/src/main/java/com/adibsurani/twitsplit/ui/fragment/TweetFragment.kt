package com.adibsurani.twitsplit.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RestrictTo
import androidx.fragment.app.Fragment
import com.adibsurani.twitsplit.R
import com.adibsurani.twitsplit.contract.fragment.TweetContract
import com.adibsurani.twitsplit.di.component.DaggerFragmentComponent
import com.adibsurani.twitsplit.di.module.FragmentModule
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_tweet.*
import kotlinx.android.synthetic.main.layout_tweet_dialog.*
import javax.inject.Inject


class TweetFragment:
    Fragment(),
    TweetContract.View {

    @Inject
    lateinit var tweetPresenter: TweetContract.Presenter
    private lateinit var rootView: View
    private val handler = Handler()

    fun newInstance(): TweetFragment {
        return TweetFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_tweet, container, false)
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
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        MaterialDialog(context!!)
            .title(text = "Tweet Post Failed")
            .message(text = "Please Try Again")
            .show()
    }

    override fun postTweetSuccess(tweets: List<String>) {
        Log.e("TWEETS ::", "$tweets")
    }

    override fun showNoTweetView() {
        layout_no_tweet.visibility = View.VISIBLE
    }

    private fun injectDependency() {
        val tweetComponent = DaggerFragmentComponent
            .builder()
            .fragmentModule(FragmentModule())
            .build()
        tweetComponent.inject(this)
    }

    private fun initView() {
        setupView()
        setupClick()

    }

    private fun setupView() {
        fab_tweet.bringToFront()

        handler.postDelayed({
            tweetPresenter.showNoTweet()
        }, 1000)
    }

    private fun setupClick() {
        fab_tweet.setOnClickListener {
            showTweetDialog()
        }

        layout_post_tweet.setOnClickListener {
            tweetPresenter.postTweet(edit_text_tweet.text.toString())
//            tweetPresenter.postTweet("What if someday we live away from each other? " +
//                    "Where all the vibes and views doesnt feel the same way as before. I am afraid to leave this pieces of places where we used to hangout" +
//                    "And where can i see you again in this part of life timeline.")
        }

        image_dismiss.setOnClickListener {
            layout_dialog.visibility = View.GONE
            @RestrictTo
            fab_tweet.visibility = View.VISIBLE
        }
    }

    private fun showTweetDialog() {
        val slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
        if (layout_dialog.visibility == View.GONE) {
            @RestrictTo
            fab_tweet.visibility = View.GONE
            layout_dialog.visibility = View.VISIBLE
            layout_dialog.bringToFront()
            layout_dialog.startAnimation(slideUp)
        }
    }

    companion object {
        val TAG: String = "TweetFragment"
    }
}