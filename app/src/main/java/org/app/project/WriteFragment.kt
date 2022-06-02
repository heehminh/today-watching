package org.app.project.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.app.project.Review
import org.app.project.ReviewDatabase
import org.app.project.databinding.FragmentWriteBinding

class WrtieFragment : Fragment() {
    private lateinit var binding: FragmentWriteBinding
    private lateinit var reviewDB: ReviewDatabase
    private var reviews = ArrayList<Review>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteBinding.inflate(layoutInflater)

        // 1) 글쓰면 db에 저장 -> Review에 저장
        // 2) db에 저장된 글 ReviewFragment에서 불러오기

        reviewDB = ReviewDatabase.getInstance(requireContext())!!
        reviews.addAll(reviewDB.ReviewDao().getReviews())

        getRate()
        binding.wrtieCompleteBtn.setOnClickListener {
            initialize()
            contentsClear()
        }

        return binding.root
    }

    fun initialize() {
        val reviewDB = ReviewDatabase.getInstance(requireContext())!!
        reviewDB.ReviewDao().insert(Review(binding.writeMovietitleEt.toString(), binding.wrtieRateTv.toString(), binding.writeTextTv.toString()))

        val _reviewDB = reviewDB.ReviewDao().getReviews()
        Log.d("MY REVIEW LOG", _reviewDB.toString())
    }

    fun contentsClear() {
        binding.writeMovietitleEt.setText(" ")
        binding.writeTextTv.setText(" ")
        clickStar01()
    }

    fun getRate() {
        binding.wrtieStarOff01Iv.setOnClickListener {
            clickStar01()
        }

        binding.wrtieStarOff02Iv.setOnClickListener {
            clickStar02()
        }

        binding.wrtieStarOff03Iv.setOnClickListener {
            clickStar03()
        }

        binding.wrtieStarOff04Iv.setOnClickListener {
            clickStar04()
        }

        binding.wrtieStarOff05Iv.setOnClickListener {
            clickStar05()
        }
    }

    private fun clickStar05() {
        binding.wrtieStar01Iv.visibility = View.VISIBLE
        binding.wrtieStar02Iv.visibility = View.VISIBLE
        binding.wrtieStar03Iv.visibility = View.VISIBLE
        binding.wrtieStar04Iv.visibility = View.VISIBLE
        binding.wrtieStar05Iv.visibility = View.VISIBLE

        binding.wrtieStarOff01Iv.visibility = View.GONE
        binding.wrtieStarOff02Iv.visibility = View.GONE
        binding.wrtieStarOff03Iv.visibility = View.GONE
        binding.wrtieStarOff04Iv.visibility = View.GONE
        binding.wrtieStarOff05Iv.visibility = View.GONE

        binding.wrtieRateTv.text = (5.0).toString()
    }

    private fun clickStar04() {
        binding.wrtieStar01Iv.visibility = View.VISIBLE
        binding.wrtieStar02Iv.visibility = View.VISIBLE
        binding.wrtieStar03Iv.visibility = View.VISIBLE
        binding.wrtieStar04Iv.visibility = View.VISIBLE
        binding.wrtieStar05Iv.visibility = View.GONE

        binding.wrtieStarOff01Iv.visibility = View.GONE
        binding.wrtieStarOff02Iv.visibility = View.GONE
        binding.wrtieStarOff03Iv.visibility = View.GONE
        binding.wrtieStarOff04Iv.visibility = View.GONE
        binding.wrtieStarOff05Iv.visibility = View.VISIBLE

        binding.wrtieRateTv.text = (4.0).toString()
    }

    private fun clickStar03() {
        binding.wrtieStar01Iv.visibility = View.VISIBLE
        binding.wrtieStar02Iv.visibility = View.VISIBLE
        binding.wrtieStar03Iv.visibility = View.VISIBLE
        binding.wrtieStar04Iv.visibility = View.GONE
        binding.wrtieStar05Iv.visibility = View.GONE

        binding.wrtieStarOff01Iv.visibility = View.GONE
        binding.wrtieStarOff02Iv.visibility = View.GONE
        binding.wrtieStarOff03Iv.visibility = View.GONE
        binding.wrtieStarOff04Iv.visibility = View.VISIBLE
        binding.wrtieStarOff05Iv.visibility = View.VISIBLE

        binding.wrtieRateTv.text = (3.0).toString()
    }

    private fun clickStar02() {
        binding.wrtieStar01Iv.visibility = View.VISIBLE
        binding.wrtieStar02Iv.visibility = View.VISIBLE
        binding.wrtieStar03Iv.visibility = View.GONE
        binding.wrtieStar04Iv.visibility = View.GONE
        binding.wrtieStar05Iv.visibility = View.GONE

        binding.wrtieStarOff01Iv.visibility = View.GONE
        binding.wrtieStarOff02Iv.visibility = View.GONE
        binding.wrtieStarOff03Iv.visibility = View.VISIBLE
        binding.wrtieStarOff04Iv.visibility = View.VISIBLE
        binding.wrtieStarOff05Iv.visibility = View.VISIBLE

        binding.wrtieRateTv.text = (2.0).toString()
    }

    private fun clickStar01() {
        binding.wrtieStar01Iv.visibility = View.VISIBLE
        binding.wrtieStar02Iv.visibility = View.GONE
        binding.wrtieStar03Iv.visibility = View.GONE
        binding.wrtieStar04Iv.visibility = View.GONE
        binding.wrtieStar05Iv.visibility = View.GONE

        binding.wrtieStarOff01Iv.visibility = View.GONE
        binding.wrtieStarOff02Iv.visibility = View.VISIBLE
        binding.wrtieStarOff03Iv.visibility = View.VISIBLE
        binding.wrtieStarOff04Iv.visibility = View.VISIBLE
        binding.wrtieStarOff05Iv.visibility = View.VISIBLE

        binding.wrtieRateTv.text = (1.0).toString()
    }


}