package com.adibsurani.twitsplit.di.component

import com.adibsurani.twitsplit.di.module.FragmentModule
import com.adibsurani.twitsplit.view.fragment.AboutFragment
import com.adibsurani.twitsplit.view.fragment.TweetFragment
import dagger.Component


@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    /*inject fragment here*/
    fun inject(tweetFragment: TweetFragment)
    fun inject(aboutFragment: AboutFragment)

}
