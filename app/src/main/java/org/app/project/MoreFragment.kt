package org.app.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.app.project.databinding.FragmentMoreBinding
import org.app.project.search.Movie
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier

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

        setInit(movie)

        binding.moreBackIv.setOnClickListener { 
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }

    private fun setInit(movie: Movie) {
        binding.moreMovieImageIv.setImageResource(movie.image)
        binding.moreMovieTitleTv.text = movie.title.toString()
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