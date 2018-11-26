package com.adibsurani.twitsplit.di.component

import com.adibsurani.twitsplit.di.module.FragmentModule
import com.adibsurani.twitsplit.ui.fragment.AboutFragment
import com.adibsurani.twitsplit.ui.fragment.TweetFragment
import dagger.Component


@Component(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(tweetFragment: TweetFragment)
    fun inject(aboutFragment: AboutFragment)
}
