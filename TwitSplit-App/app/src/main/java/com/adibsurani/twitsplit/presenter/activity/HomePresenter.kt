package com.adibsurani.twitsplit.presenter.activity

import com.adibsurani.twitsplit.contract.activity.HomeContract
import io.reactivex.disposables.CompositeDisposable


class HomePresenter: HomeContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: HomeContract.View

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
        view.showTweetFragment()
    }

    override fun onDrawerOptionAboutClick() {
        view.showAboutFragment()
    }
}