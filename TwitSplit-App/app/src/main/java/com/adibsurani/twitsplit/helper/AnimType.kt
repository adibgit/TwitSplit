package com.adibsurani.twitsplit.helper

import com.adibsurani.twitsplit.R

enum class AnimType {

    SLIDE,
    FADE;

    fun getAnimPair(): Pair<Int, Int> {
        when(this) {
            SLIDE -> return Pair(R.anim.slide_left, R.anim.slide_right)
            FADE -> return Pair(R.anim.fade_in, R.anim.fade_out)
        }

        return Pair(R.anim.slide_left, R.anim.slide_right)
    }

}