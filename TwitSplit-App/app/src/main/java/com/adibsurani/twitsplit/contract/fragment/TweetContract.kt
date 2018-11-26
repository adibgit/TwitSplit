package com.adibsurani.twitsplit.contract.fragment

import com.adibsurani.twitsplit.contract.BaseContract

class TweetContract {

    interface View: BaseContract.View {
        fun showTweetLoad(show: Boolean)
        fun showNoTweetView()
        fun showTweetView()
        fun showErrorMessage(error: String)
        fun postTweetSuccess(tweets: List<String>)
    }

    interface Presenter:
        BaseContract.Presenter<View> {
        fun postTweet(tweet: String)
        fun showNoTweet()
        fun showTweet()
    }
}