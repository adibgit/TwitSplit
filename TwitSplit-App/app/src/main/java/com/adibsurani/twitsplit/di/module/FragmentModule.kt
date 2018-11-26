package com.adibsurani.twitsplit.di.module

import com.adibsurani.twitsplit.data.api.ApiServiceInterface
import com.adibsurani.twitsplit.contract.fragment.AboutContract
import com.adibsurani.twitsplit.contract.fragment.TweetContract
import com.adibsurani.twitsplit.presenter.fragment.AboutPresenter
import com.adibsurani.twitsplit.presenter.fragment.TweetPresenter
import dagger.Module
import dagger.Provides


@Module
class FragmentModule {
    @Provides
    fun provideTweetPresenter(): TweetContract.Presenter {
        return TweetPresenter()
    }
    @Provides
    fun provideAboutPresenter(): AboutContract.Presenter {
        return AboutPresenter()
    }
    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}