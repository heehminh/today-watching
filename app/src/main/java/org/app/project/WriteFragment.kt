package org.app.project.search

import android.os.Bundle
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

//        fetchReviewList()

        return binding.root
    }

    fun fetchReviewList(){
        CoroutineScope(Dispatchers.Main).launch {
            val load = async(Dispatchers.IO) {
                val reviewList = reviewDB.ReviewDao().getReviews()

            }
        }
    }


}