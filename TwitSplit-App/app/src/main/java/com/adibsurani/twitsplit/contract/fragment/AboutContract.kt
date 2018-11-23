package com.adibsurani.twitsplit.contract.fragment

import com.adibsurani.twitsplit.contract.BaseContract

class AboutContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun loadMessageSuccess(message: String)

    }

    interface Presenter:
        BaseContract.Presenter<View> {
        fun loadMessage()
    }

}