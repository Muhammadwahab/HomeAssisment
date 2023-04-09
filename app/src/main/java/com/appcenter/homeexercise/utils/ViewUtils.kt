package com.appcenter.homeexercise.utils

import android.view.View
import android.view.ViewGroup

object ViewUtils {

    fun View.remove(){
        this.visibility=View.GONE
    }
    fun View.show(){
        this.visibility=View.VISIBLE
    }
    fun View.hide(){
        this.visibility=View.INVISIBLE
    }

    fun ViewGroup.remove(){
        this.visibility=View.GONE
    }
    fun ViewGroup.show(){
        this.visibility=View.VISIBLE
    }
    fun ViewGroup.hide(){
        this.visibility=View.INVISIBLE
    }
}