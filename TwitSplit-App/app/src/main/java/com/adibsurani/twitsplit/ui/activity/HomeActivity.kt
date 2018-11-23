package com.adibsurani.twitsplit.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.adibsurani.twitsplit.R
import com.adibsurani.twitsplit.di.component.DaggerActivityComponent
import com.adibsurani.twitsplit.di.module.ActivityModule
import com.adibsurani.twitsplit.helper.AnimType
import com.adibsurani.twitsplit.contract.activity.HomeContract
import com.adibsurani.twitsplit.ui.fragment.AboutFragment
import com.adibsurani.twitsplit.ui.fragment.TweetFragment
import javax.inject.Inject

class HomeActivity:
    AppCompatActivity(),
    HomeContract.View {

    @Inject
    lateinit var homePresenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        homePresenter.attach(this)

    }

    override fun showAboutFragment() {
        if (supportFragmentManager.findFragmentByTag(AboutFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(AnimType.FADE.getAnimPair().first, AnimType.FADE.getAnimPair().second)
                .replace(R.id.layout_container, AboutFragment().newInstance(), AboutFragment.TAG)
                .commit()
        }
    }

    override fun showTweetFragment() {
        supportFragmentManager
            .beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(AnimType.SLIDE.getAnimPair().first, AnimType.SLIDE.getAnimPair().second)
            .replace(R.id.layout_container, TweetFragment().newInstance(), TweetFragment.TAG)
            .commit()
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG)

        if (fragment == null) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)
    }

}
