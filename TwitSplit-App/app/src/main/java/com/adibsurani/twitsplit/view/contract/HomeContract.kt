package com.adibsurani.twitsplit.view.contract

class HomeContract {

    interface View: BaseContract.View {
        fun showTweetFragment()
        fun showAboutFragment()
    }

    interface Presenter: BaseContract.Presenter<HomeContract.View> {
        fun onDrawerOptionAboutClick()
    }

}