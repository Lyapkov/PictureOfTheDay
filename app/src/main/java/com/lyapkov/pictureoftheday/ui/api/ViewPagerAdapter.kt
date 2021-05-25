package com.lyapkov.pictureoftheday.ui.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val DAY_BEFORE_YESTERDAY_FRAGMENT = 0
private const val YESTERDAY_FRAGMENT = 1
private const val TODAY_FRAGMENT = 2

class ViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(PictureDayBeforeYesterdayFragment(), PictureYesterdayFragment(), PictureTodayFragment())

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> fragments[DAY_BEFORE_YESTERDAY_FRAGMENT]
            1 -> fragments[YESTERDAY_FRAGMENT]
            2 -> fragments[TODAY_FRAGMENT]
            else -> fragments[TODAY_FRAGMENT]
        }
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }
}