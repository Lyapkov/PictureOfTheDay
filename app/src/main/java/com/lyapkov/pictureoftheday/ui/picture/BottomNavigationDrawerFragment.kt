package com.lyapkov.pictureoftheday.ui.picture

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lyapkov.pictureoftheday.R
import com.lyapkov.pictureoftheday.ui.animations.AnimationsActivity
import com.lyapkov.pictureoftheday.ui.animations.AnimationsActivityBonus
import com.lyapkov.pictureoftheday.ui.recycler.RecyclerActivity
import kotlinx.android.synthetic.main.bottom_navigation_layout.*

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_navigation_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_one -> activity?.let {
                    startActivity(
                        Intent(
                            it,
                            AnimationsActivity::class.java
                        )
                    )
                }
                R.id.navigation_two -> activity?.let {
                    startActivity(
                        Intent(
                            it,
                            AnimationsActivityBonus::class.java
                        )
                    )
                }
                R.id.navigation_three -> activity?.let {
                    startActivity(
                        Intent(
                            it,
                            RecyclerActivity::class.java
                        )
                    )
                }
            }
            dismiss()
            true
        }
    }
}
