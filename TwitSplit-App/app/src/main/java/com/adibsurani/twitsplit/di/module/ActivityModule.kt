package com.adibsurani.twitsplit.di.module

import android.app.Activity
import com.adibsurani.twitsplit.view.contract.HomeContract
import com.adibsurani.twitsplit.view.presenter.HomePresenter
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }


    @Provides
    fun providePresenter(): HomeContract.Presenter {
        return HomePresenter()
    }


}