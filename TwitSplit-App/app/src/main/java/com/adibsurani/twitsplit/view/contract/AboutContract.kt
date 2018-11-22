package com.adibsurani.twitsplit.view.contract

class AboutContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun loadMessageSuccess(message: String)

    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadMessage()
    }

}