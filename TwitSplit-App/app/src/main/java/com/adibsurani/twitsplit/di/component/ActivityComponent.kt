package com.adibsurani.twitsplit.di.component

import com.adibsurani.twitsplit.di.module.ActivityModule
import com.adibsurani.twitsplit.ui.activity.HomeActivity
import com.adibsurani.twitsplit.ui.activity.SplashActivity
import dagger.Component


@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(homeActivity: HomeActivity)
    fun inject(splashActivity: SplashActivity)
}