package com.adibsurani.twitsplit.di.component

import com.adibsurani.twitsplit.application.BaseApp
import com.adibsurani.twitsplit.di.module.ApplicationModule
import dagger.Component


@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: BaseApp)

}