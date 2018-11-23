package com.adibsurani.twitsplit.presenter.activity

import com.adibsurani.twitsplit.contract.activity.SplashContract
import io.reactivex.disposables.CompositeDisposable

class SplashPresenter: SplashContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: SplashContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: SplashContract.View) {
        this.view = view
    }

    override fun gotoHome() {
        view.gotoHomeActivity()
    }


}