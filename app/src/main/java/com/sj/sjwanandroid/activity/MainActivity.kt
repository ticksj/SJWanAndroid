package com.sj.sjwanandroid.activity

import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.sj.sjwanandroid.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override val layoutId = R.layout.activity_main;

    override fun onStart() {
        super.onStart()
        var controller = Navigation.findNavController(this, R.id.main_host_fragment)
        btm_nv.setupWithNavController(controller)
    }


    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

}
