package com.adibsurani.twitsplit.view.presenter

import com.adibsurani.twitsplit.view.contract.AboutContract

class AboutPresenter: AboutContract.Presenter {

    private lateinit var view: AboutContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun attach(view: AboutContract.View) {
        this.view = view
    }

    override fun loadMessage() {

    }


}