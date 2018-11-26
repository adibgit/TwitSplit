package com.adibsurani.twitsplit.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import androidx.annotation.RestrictTo
import androidx.fragment.app.Fragment
import com.adibsurani.twitsplit.R
import com.adibsurani.twitsplit.contract.fragment.TweetContract
import com.adibsurani.twitsplit.di.component.DaggerFragmentComponent
import com.adibsurani.twitsplit.di.module.FragmentModule
import com.adibsurani.twitsplit.helper.RecyclerUtil
import com.adibsurani.twitsplit.ui.adapter.TweetAdapter
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_tweet.*
import kotlinx.android.synthetic.main.layout_tweet_dialog.*
import java.util.*
import javax.inject.Inject


class TweetFragment:
    Fragment(),
    TweetContract.View {

    @Inject
    lateinit var tweetPresenter: TweetContract.Presenter
    private lateinit var rootView: View
    private val handler = Handler()
    private lateinit var tweetAdapter: TweetAdapter
    private val tweetList = ArrayList<String>()

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
        initData()
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
        for (tweet in tweets) {
            tweetList.add(tweet)
            //tweetList.reverse()
            tweetAdapter.notifyDataSetChanged()
        }
        tweetPresenter.showTweet()
    }

    override fun showTweetView() {
        layout_no_tweet.visibility = View.GONE
        recycler_tweet.visibility = View.VISIBLE
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
        setupAdapter()
    }

    private fun initData() {
        tweetList.reverse()
    }

    private fun setupView() {
        fab_tweet.bringToFront()
        handler.postDelayed({
            tweetPresenter.showNoTweet()
        }, 1000)
        edit_text_tweet.imeOptions =  EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE
        edit_text_tweet.setRawInputType(InputType.TYPE_CLASS_TEXT)
    }

    private fun setupClick() {
        fab_tweet.setOnClickListener {
            showTweetDialog()
        }

        layout_post_tweet.setOnClickListener {
            val tweetContent = edit_text_tweet.text.toString()
            val tweetContentSize = tweetContent.count()

            if (tweetContentSize > 50) {
                MaterialDialog(context!!)
                    .title(text = "Post Tweet")
                    .message(text = "Your tweet has more than 50 characters. \n Are you sure to split into multiple tweet posts?")
                    .show {
                        negativeButton(text = "Cancel")
                        positiveButton(text = "Sure") {
                            dismissDialog()
                            tweetPresenter.postTweet(tweetContent)
                            clearEditText()
                        }
                    }
            } else {
                dismissDialog()
                tweetPresenter.postTweet(edit_text_tweet.text.toString())
                clearEditText()
            }
        }

        image_dismiss.setOnClickListener {
            dismissDialog()
        }
    }

    private fun clearEditText() {
        edit_text_tweet.setText("")
    }

    private fun setupAdapter() {
        RecyclerUtil.setupVertical(recycler_tweet,context!!)
        tweetAdapter = TweetAdapter(activity!!,context!!,tweetList)
        recycler_tweet.adapter = tweetAdapter
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

    private fun dismissDialog() {
        layout_dialog.visibility = View.GONE
        @RestrictTo
        fab_tweet.visibility = View.VISIBLE
    }

    companion object {
        val TAG: String = "TweetFragment"
    }
}