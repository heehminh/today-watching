package org.app.project.search

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.app.project.R
import org.app.project.Review
import org.app.project.ReviewDatabase
import org.app.project.ReviewFragment
import org.app.project.databinding.FragmentWriteBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WrtieFragment : Fragment() {
    private lateinit var binding: FragmentWriteBinding
    private lateinit var reviewDB: ReviewDatabase
    private var reviews = ArrayList<Review>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteBinding.inflate(layoutInflater)

        reviewDB = ReviewDatabase.getInstance(requireContext())!!
        reviews.addAll(reviewDB.ReviewDao().getReviews())

        getRate()
        binding.wrtieCompleteBtn.setOnClickListener {
            initialize()
            contentsClear()
        }

        val titles = resources.getStringArray(R.array.titles_array)
        val adapter = activity?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, titles) }
        binding.writeMovietitleEt.setAdapter(adapter)


        return binding.root
    }

    fun initialize() {
        val reviewDB = ReviewDatabase.getInstance(requireContext())!!
        val now = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val formatDate = DateTimeFormatter.ISO_DATE
        val nowDate = now.format(formatDate)

        reviewDB.ReviewDao().insert(Review(binding.writeMovietitleEt.toString(), binding.wrtieRateTv.toString(), binding.writeTextTv.toString(), nowDate))

        val _reviewDB = reviewDB.ReviewDao().getReviews()
        Log.d("MY REVIEW LOG", _reviewDB.toString())

        ReviewFragment().apply {
            arguments = Bundle().apply {
                val gson = Gson()
                val writeJson = gson.toJson(_reviewDB)
                putString("write", writeJson)
            }
        }
    }

    fun contentsClear() {
        binding.writeMovietitleEt.setText(" ")
        binding.wrtieTextEt.setText(" ")
        clearStar()
    }

    // 다시 누르면 다시 돌아오게
    // 줄 간격 안맞음

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

        binding.wrtieStarOff01Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff02Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff03Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff04Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff05Iv.visibility = View.INVISIBLE

        binding.wrtieRateTv.text = (5.0).toString()
    }

    private fun clickStar04() {
        binding.wrtieStar01Iv.visibility = View.VISIBLE
        binding.wrtieStar02Iv.visibility = View.VISIBLE
        binding.wrtieStar03Iv.visibility = View.VISIBLE
        binding.wrtieStar04Iv.visibility = View.VISIBLE
        binding.wrtieStar05Iv.visibility = View.INVISIBLE

        binding.wrtieStarOff01Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff02Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff03Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff04Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff05Iv.visibility = View.VISIBLE

        binding.wrtieRateTv.text = (4.0).toString()
    }

    private fun clickStar03() {
        binding.wrtieStar01Iv.visibility = View.VISIBLE
        binding.wrtieStar02Iv.visibility = View.VISIBLE
        binding.wrtieStar03Iv.visibility = View.VISIBLE
        binding.wrtieStar04Iv.visibility = View.INVISIBLE
        binding.wrtieStar05Iv.visibility = View.INVISIBLE

        binding.wrtieStarOff01Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff02Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff03Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff04Iv.visibility = View.VISIBLE
        binding.wrtieStarOff05Iv.visibility = View.VISIBLE

        binding.wrtieRateTv.text = (3.0).toString()
    }

    private fun clickStar02() {
        binding.wrtieStar01Iv.visibility = View.VISIBLE
        binding.wrtieStar02Iv.visibility = View.VISIBLE
        binding.wrtieStar03Iv.visibility = View.INVISIBLE
        binding.wrtieStar04Iv.visibility = View.INVISIBLE
        binding.wrtieStar05Iv.visibility = View.INVISIBLE

        binding.wrtieStarOff01Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff02Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff03Iv.visibility = View.VISIBLE
        binding.wrtieStarOff04Iv.visibility = View.VISIBLE
        binding.wrtieStarOff05Iv.visibility = View.VISIBLE

        binding.wrtieRateTv.text = (2.0).toString()
    }

    private fun clickStar01() {
        binding.wrtieStar01Iv.visibility = View.VISIBLE
        binding.wrtieStar02Iv.visibility = View.INVISIBLE
        binding.wrtieStar03Iv.visibility = View.INVISIBLE
        binding.wrtieStar04Iv.visibility = View.INVISIBLE
        binding.wrtieStar05Iv.visibility = View.INVISIBLE

        binding.wrtieStarOff01Iv.visibility = View.INVISIBLE
        binding.wrtieStarOff02Iv.visibility = View.VISIBLE
        binding.wrtieStarOff03Iv.visibility = View.VISIBLE
        binding.wrtieStarOff04Iv.visibility = View.VISIBLE
        binding.wrtieStarOff05Iv.visibility = View.VISIBLE

        binding.wrtieRateTv.text = (1.0).toString()
    }

    private fun clearStar() {
        binding.wrtieStar01Iv.visibility = View.INVISIBLE
        binding.wrtieStar02Iv.visibility = View.INVISIBLE
        binding.wrtieStar03Iv.visibility = View.INVISIBLE
        binding.wrtieStar04Iv.visibility = View.INVISIBLE
        binding.wrtieStar05Iv.visibility = View.INVISIBLE

        binding.wrtieStarOff01Iv.visibility = View.VISIBLE
        binding.wrtieStarOff02Iv.visibility = View.VISIBLE
        binding.wrtieStarOff03Iv.visibility = View.VISIBLE
        binding.wrtieStarOff04Iv.visibility = View.VISIBLE
        binding.wrtieStarOff05Iv.visibility = View.VISIBLE

        binding.wrtieRateTv.text = (1.0).toString()
    }


}