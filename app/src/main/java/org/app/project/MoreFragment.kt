package org.app.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.app.project.databinding.FragmentMoreBinding
import org.app.project.search.Movie
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier

class MoreFragment: Fragment() {
    lateinit var binding: FragmentMoreBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreBinding.inflate(inflater, container, false)

        val movieData = arguments?.getString("movie")
        // val movie = gson.fromJson(movieData, Movie::class.java)

        // setViews(movie)

        binding.moreBackIv.setOnClickListener { 
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }


        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setViews(movie: Movie) {
        binding.moreMovieTitleTv.text = movie.title.toString()
        binding.moreMovieImageIv.setImageResource(movie.image)
        if (movie.like == true) {
            binding.moreLikeOnIv.visibility = View.VISIBLE
            binding.moreLikeOffIv.visibility = View.GONE
        } else {
            binding.moreLikeOnIv.visibility = View.GONE
            binding.moreLikeOffIv.visibility = View.VISIBLE
        }
    }
}