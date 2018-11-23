package com.adibsurani.twitsplit.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.adibsurani.twitsplit.R
import com.adibsurani.twitsplit.contract.activity.SplashContract
import com.adibsurani.twitsplit.di.component.DaggerActivityComponent
import com.adibsurani.twitsplit.di.module.ActivityModule
import javax.inject.Inject

class SplashActivity:
    AppCompatActivity(),
    SplashContract.View {

    @Inject
    lateinit var splashPresenter: SplashContract.Presenter
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        injectDependency()
        splashPresenter.attach(this)
        initView()
    }

    override fun gotoHomeActivity() {
        val intent =  Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)
    }

    private fun initView() {
        handler.postDelayed({
            splashPresenter.gotoHome()
        }, 500)


    }

}