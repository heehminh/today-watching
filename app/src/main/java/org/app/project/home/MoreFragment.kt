package org.app.project.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.app.project.MainActivity
import org.app.project.R
import org.app.project.databinding.FragmentMoreBinding

class MoreFragment: Fragment() {
    lateinit var binding: FragmentMoreBinding
    private var gson: Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreBinding.inflate(inflater, container, false)

        val movieData = arguments?.getString("title")
        val movie = gson.fromJson(movieData, Movie::class.java)



        binding.moreLikeOnIv.setOnClickListener {
            binding.moreLikeOnIv.visibility = View.INVISIBLE
            binding.moreLikeOffIv.visibility = View.VISIBLE
            movie.like = false
        }
        binding.moreLikeOffIv.setOnClickListener {
            binding.moreLikeOnIv.visibility = View.VISIBLE
            binding.moreLikeOffIv.visibility = View.INVISIBLE
            movie.like = true
        }

        binding.moreBackIv.setOnClickListener { 
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        setInit(movie)

        return binding.root
    }

    private fun setInit(movie: Movie) {
        Log.d("MYTAG", "title $movie.title like $movie.like")
        binding.moreMovieImageIv.setImageResource(movie.image)
        binding.moreMovieTitleTv.text = movie.title

        if (movie.like == true) {
            binding.moreLikeOnIv.visibility = View.VISIBLE
            binding.moreLikeOffIv.visibility = View.INVISIBLE
        } else {
            binding.moreLikeOnIv.visibility = View.INVISIBLE
            binding.moreLikeOffIv.visibility = View.VISIBLE
        }

        binding.moreMovieTextTv.text = "일단은 입력없나봄 나도 어떻게 하는지 모르겠고 내가 할 수 있는 한 최선을 다해서 해볼게.. 일단은 입력없나봄 나도 어떻게 하는지 모르겠고 내가 할 수 있는 한 최선을 다해서 해볼게..일단은 입력없나봄 나도 어떻게 하는지 모르겠고 내가 할 수 있는 한 최선을 다해서 해볼게..일단은 입력없나봄 나도 어떻게 하는지 모르겠고 내가 할 수 있는 한 최선을 다해서 해볼게.."
    }

}