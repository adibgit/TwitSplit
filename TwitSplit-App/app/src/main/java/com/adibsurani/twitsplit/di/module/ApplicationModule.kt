package com.adibsurani.twitsplit.di.module

import android.app.Application
import com.adibsurani.twitsplit.application.BaseApp
import com.adibsurani.twitsplit.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }

}