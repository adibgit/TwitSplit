package com.adibsurani.twitsplit.di.module

import android.app.Activity
import com.adibsurani.twitsplit.contract.activity.HomeContract
import com.adibsurani.twitsplit.contract.activity.SplashContract
import com.adibsurani.twitsplit.presenter.activity.HomePresenter
import com.adibsurani.twitsplit.presenter.activity.SplashPresenter
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private var activity: Activity) {
    @Provides
    fun provideActivity(): Activity {
        return activity
    }
    @Provides
    fun provideHomePresenter(): HomeContract.Presenter {
        return HomePresenter()
    }
    @Provides
    fun provideSplashPresenter(): SplashContract.Presenter {
        return SplashPresenter()
    }
}