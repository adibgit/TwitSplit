package com.adibsurani.twitsplit.di.component

import com.adibsurani.twitsplit.di.module.ActivityModule
import com.adibsurani.twitsplit.view.activity.HomeActivity
import dagger.Component


@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    //register your activity here
    fun inject(homeActivity: HomeActivity)

}