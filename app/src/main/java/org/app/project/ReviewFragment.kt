package org.app.project

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity.apply
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat.apply
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import org.app.project.databinding.FragmentReviewBinding
import org.app.project.setting.ReviewRVAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReviewFragment: Fragment() {
    lateinit var binding: FragmentReviewBinding
    private var reviewDatas = ArrayList<Review>()
    lateinit var reviewDB: ReviewDatabase

    private var gson: Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater, container, false)

        reviewDB = ReviewDatabase.getInstance(requireContext())!!
        reviewDatas.addAll(reviewDB.ReviewDao().getReviews())


        binding.reviewBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, SettingFragment())
                .commitAllowingStateLoss()
        }


        // 더미데이터와 Adapter 연결
        val reviewRVAdapter = ReviewRVAdapter(reviewDatas)
        binding.reviewRecyclerview.adapter = reviewRVAdapter
        binding.reviewRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        reviewRVAdapter.setMyItemClickListener(object: ReviewRVAdapter.MyItemClickListener {
            override fun onItemClick(review: Review) {
                changeMyFragment(review)
            }

            private fun changeMyFragment(review: Review) {
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, MyFragment().apply {
                        arguments = Bundle().apply {
                            val gson = Gson()
                            val reviewJson = gson.toJson(review)
                            putString("review", reviewJson)
                        }
                    })
                    .commitAllowingStateLoss()
            }

            override fun onRemoveMovie(position: Int) {
                reviewRVAdapter.removeItem(position)
            }
        })


        return binding.root
    }

}