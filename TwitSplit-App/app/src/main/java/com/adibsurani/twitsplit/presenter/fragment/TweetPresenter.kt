package com.adibsurani.twitsplit.presenter.fragment

import android.util.Log
import com.adibsurani.twitsplit.contract.fragment.TweetContract
import io.reactivex.disposables.CompositeDisposable

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
        val tweetSize = 50
        val originalTweetSize = tweet.count()
        val finalTweetList = ArrayList<String>()

        if (originalTweetSize > tweetSize) {
            val chunkTweets = tweet.chunked(tweetSize)
            val chunkTweetsSize = chunkTweets.size

            Log.e("Chunk Tweets", "$chunkTweets")
            Log.e("Chunk Tweets Size", "$chunkTweetsSize")
            Log.e("Tweet Size", "$originalTweetSize")

            for(tweets in chunkTweets) {
                val pagedTweet = "${chunkTweets.indexOf(tweets) + 1}" + "/" + "$chunkTweetsSize" + " " + tweets
                finalTweetList.add(pagedTweet)
            }

            Log.e("Final Tweet Size", "${finalTweetList[0].length}")

            if (finalTweetList.size == chunkTweetsSize) {
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