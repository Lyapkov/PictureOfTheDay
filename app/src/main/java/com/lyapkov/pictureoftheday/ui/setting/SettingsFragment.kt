package com.lyapkov.pictureoftheday.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lyapkov.pictureoftheday.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchMaterial.setOnClickListener {
            if (it.isClickable) {
                requireActivity().setTheme(R.style.DarkTheme)
                requireActivity().recreate()
            } else {
                requireActivity().setTheme(R.style.AppTheme)
                requireActivity().recreate()
            }
        }
    }
}