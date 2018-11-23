package com.adibsurani.twitsplit.presenter.fragment

import android.util.Log
import com.adibsurani.twitsplit.contract.fragment.TweetContract
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import kotlin.collections.ArrayList

class TweetPresenter : TweetContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: TweetContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: TweetContract.View) {
        this.view = view
    }

    override fun postTweet(tweet: String) {
        val originalTweetSize = tweet.count()
        val tweetChars : CharArray = tweet.toCharArray()
        val tweetList = ArrayList<String>()
        val finalTweetList = ArrayList<String>()

        Log.e("tweet size ", "$originalTweetSize")
        Log.e("tweet char ", "${tweetChars.size}")

        if (originalTweetSize > 20) {
            for (tweets in tweet.split("(?<=\\G.{20})")) {
                Log.e("TWEET", tweets)
                tweetList.add(tweets)
            }

            val tweetListSize = tweetList.size
            for(tweets in tweetList) {
                val pagedTweet = "${tweetList.indexOf(tweets) + 1}" + "/" + "$tweetListSize" + " " + tweets
                finalTweetList.add(pagedTweet)
            }

            if (finalTweetList.size == tweetListSize) {
                view.postTweetSuccess(finalTweetList)
            }

        } else {
            finalTweetList.add(tweet)
            view.postTweetSuccess(finalTweetList)
        }
    }

    override fun showNoTweet() {
        view.showTweetLoad(false)
        view.showNoTweetView()
    }


}