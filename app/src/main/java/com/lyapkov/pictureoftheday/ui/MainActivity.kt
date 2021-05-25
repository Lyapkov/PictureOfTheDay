package com.lyapkov.pictureoftheday.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import coil.api.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.lyapkov.pictureoftheday.R
import com.lyapkov.pictureoftheday.ui.api.ViewPagerAdapter
import com.lyapkov.pictureoftheday.ui.picture.BottomNavigationDrawerFragment
import com.lyapkov.pictureoftheday.ui.picture.PictureOfTheDayData
import com.lyapkov.pictureoftheday.ui.picture.PictureOfTheDayViewModel
import com.lyapkov.pictureoftheday.ui.setting.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottom_app_bar
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.main_fragment.*

private const val DAY_BEFORE_YESTERDAY= 0
private const val YESTERDAY = 1
private const val TODAY = 2

class MainActivity : AppCompatActivity() {

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProviders.of(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
        setHighlightedTab(DAY_BEFORE_YESTERDAY)

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                setHighlightedTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
        })
        setBottomAppBar(this)

    }

    private fun setHighlightedTab(position: Int) {
        val layoutInflater = LayoutInflater.from(this@MainActivity)

        tab_layout.getTabAt(DAY_BEFORE_YESTERDAY)?.customView = null
        tab_layout.getTabAt(YESTERDAY)?.customView = null
        tab_layout.getTabAt(TODAY)?.customView = null

        when (position) {
            DAY_BEFORE_YESTERDAY -> {
                setDayBeforeYesterdayTabHighlighted(layoutInflater)
            }
            YESTERDAY -> {
                setYesterdayTabHighlighted(layoutInflater)
            }
            TODAY -> {
                setTodayTabHighlighted(layoutInflater)
            }
            else -> {
                setTodayTabHighlighted(layoutInflater)
            }
        }
    }

    private fun setDayBeforeYesterdayTabHighlighted(layoutInflater: LayoutInflater) {
        val dayBeforeYesterday =
            layoutInflater.inflate(R.layout.activity_picture_day_before_yesterday, null)
        dayBeforeYesterday.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.colorAccent
                )
            )
        tab_layout.getTabAt(DAY_BEFORE_YESTERDAY)?.customView = dayBeforeYesterday
        tab_layout.getTabAt(YESTERDAY)?.customView =
            layoutInflater.inflate(R.layout.activity_picture_yesterday, null)
        tab_layout.getTabAt(TODAY)?.customView =
            layoutInflater.inflate(R.layout.activity_picture_today, null)
    }

    private fun setYesterdayTabHighlighted(layoutInflater: LayoutInflater) {
        val yesterday =
            layoutInflater.inflate(R.layout.activity_picture_yesterday, null)
        yesterday.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.colorAccent
                )
            )
        tab_layout.getTabAt(DAY_BEFORE_YESTERDAY)?.customView =
            layoutInflater.inflate(R.layout.activity_picture_day_before_yesterday, null)
        tab_layout.getTabAt(YESTERDAY)?.customView = yesterday
        tab_layout.getTabAt(TODAY)?.customView =
            layoutInflater.inflate(R.layout.activity_picture_today, null)
    }

    private fun setTodayTabHighlighted(layoutInflater: LayoutInflater) {
        val today =
            layoutInflater.inflate(R.layout.activity_picture_today, null)
        today.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.colorAccent
                )
            )
        tab_layout.getTabAt(DAY_BEFORE_YESTERDAY)?.customView =
            layoutInflater.inflate(R.layout.activity_picture_day_before_yesterday, null)
        tab_layout.getTabAt(YESTERDAY)?.customView =
            layoutInflater.inflate(R.layout.activity_picture_yesterday, null)
        tab_layout.getTabAt(TODAY)?.customView = today
    }

    private fun setBottomAppBar(view: MainActivity) {
        this.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        //setHasOptionsMenu(true)
        fab.setOnClickListener {
            if (isMain) {
                isMain = false
                bottom_app_bar.navigationIcon = null
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_back_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                bottom_app_bar.navigationIcon =
                    ContextCompat.getDrawable(this, R.drawable.ic_hamburger_menu_bottom_bar)
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_plus_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.app_bar_fav -> toast("Favourite")
            R.id.app_bar_settings -> this.supportFragmentManager
                .beginTransaction()
                .add(R.id.container, SettingsFragment())
                .addToBackStack(null).commit()
            android.R.id.home -> {
                this.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }



    companion object {
        private var isMain = true
    }
}
