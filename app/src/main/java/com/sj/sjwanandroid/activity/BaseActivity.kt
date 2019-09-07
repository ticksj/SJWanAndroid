package com.sj.sjwanandroid.activity

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by SJ on 2019/9/3.
 */
abstract class BaseActivity: AppCompatActivity() {
   protected abstract val layoutId:Int;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

}