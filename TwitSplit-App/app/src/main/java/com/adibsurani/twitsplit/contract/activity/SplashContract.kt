package com.adibsurani.twitsplit.contract.activity

import com.adibsurani.twitsplit.contract.BaseContract

class SplashContract {

    interface View: BaseContract.View {
        fun gotoHomeActivity()
    }

    interface Presenter: BaseContract.Presenter<SplashContract.View> {
        fun gotoHome()
    }

}