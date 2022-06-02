package org.app.project.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.app.project.MainActivity
import org.app.project.R
import org.app.project.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)


        // review. 내가 남긴 후기
        binding.settingReviewTv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ReviewFragment())
                .commitAllowingStateLoss()
        }

        // wish. 내가 찜한 작품
        binding.settingWishTv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, WishFragment())
                .commitAllowingStateLoss()
        }

        // clause. 약관
        binding.settingClauseTv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ClauseFragment())
                .commitAllowingStateLoss()
        }

        // notify. 공지사항
        binding.settingNotifyTv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, NotifyFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }


}