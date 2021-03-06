package com.lyapkov.pictureoftheday.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyapkov.pictureoftheday.R
import com.lyapkov.pictureoftheday.ui.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }
}
