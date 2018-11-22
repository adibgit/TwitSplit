package com.adibsurani.twitsplit.view.contract

class TweetContract {

    interface View: BaseContract.View {
        fun showTweetLoad(show: Boolean)
        fun showErrorMessage(error: String)
        fun postTweetSuccess(tweets: List<String>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun postTweet(tweets: List<String>)
    }
}