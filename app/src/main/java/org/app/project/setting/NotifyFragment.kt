package org.app.project.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.app.project.MainActivity
import org.app.project.R
import org.app.project.databinding.FragmentNotifyBinding

class NotifyFragment: Fragment() {
    lateinit var binding: FragmentNotifyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotifyBinding.inflate(inflater, container, false)

        binding.notifyBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, SettingFragment())
                .commitAllowingStateLoss()
        }
        return binding.root
    }
}