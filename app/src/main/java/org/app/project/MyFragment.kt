package org.app.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.app.project.databinding.FragmentMyBinding

class MyFragment: Fragment() {
    lateinit var binding: FragmentMyBinding
    private var gson: Gson = Gson()
    private lateinit var review: Review
    private var nowPos = 0

    private lateinit var reviewDB: ReviewDatabase
    private var reviews = ArrayList<Review>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBinding.inflate(inflater, container, false)

        val reviewData = arguments?.getString("review")
        review = gson.fromJson(reviewData, Review::class.java)

        binding.myBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ReviewFragment())
                .commitAllowingStateLoss()
        }

        //initReview()
        setReview(review)
        initReviewList()

        return binding.root
    }

    private fun setReview(review: Review) {
        binding.myTitleTv.text = review.title
        binding.myTextTv.text = review.text
        binding.myDateTv.text = review.date
        binding.myRateTv.text = review.rate
    }

    private fun initReviewList() {
        reviewDB = ReviewDatabase.getInstance(requireContext())!!
        reviews.addAll(reviewDB.ReviewDao().getReviews())
    }

    private fun initReview() {
        nowPos = review.id
        setReview(reviews[nowPos])
    }
}