package com.adibsurani.twitsplit.application

import android.app.Application
import com.adibsurani.twitsplit.di.component.ApplicationComponent
import com.adibsurani.twitsplit.di.component.DaggerApplicationComponent
import com.adibsurani.twitsplit.di.module.ApplicationModule

class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setupApplication()
    }

    fun setupApplication() {
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}