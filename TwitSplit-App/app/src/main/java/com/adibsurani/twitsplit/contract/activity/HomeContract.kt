package com.adibsurani.twitsplit.contract.activity

import com.adibsurani.twitsplit.contract.BaseContract

class HomeContract {

    interface View: BaseContract.View {
        fun showTweetFragment()
        fun showAboutFragment()
    }

    interface Presenter:
        BaseContract.Presenter<HomeContract.View> {
        fun onDrawerOptionAboutClick()
    }

}