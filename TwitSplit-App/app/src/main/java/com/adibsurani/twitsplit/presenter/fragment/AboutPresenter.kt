package com.adibsurani.twitsplit.presenter.fragment

import com.adibsurani.twitsplit.contract.fragment.AboutContract

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