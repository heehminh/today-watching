package org.app.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import org.app.project.databinding.FragmentWishBinding

class WishFragment : Fragment() {
    lateinit var binding: FragmentWishBinding
    private var gson: Gson = Gson()
    lateinit var movieDB: MovieDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishBinding.inflate(inflater, container, false)
        movieDB = MovieDatabase.getInstance(requireContext())!!

//        val movieData = arguments?.getString("title")
//        val movie = gson.fromJson(movieData, Movie::class.java)

        // setInit(movie)

        binding.wishBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, SettingFragment())
                .commitAllowingStateLoss()
        }
        movieDB = MovieDatabase.getInstance(requireContext())!!
//
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerview()
    }

    private fun initRecyclerview() {
        binding.wishRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val wishRVAdpater = WishRVAdapter()

        wishRVAdpater.setMyItemClickListener(object: WishRVAdapter.MyItemClickListener {
            override fun onRemoveItem(movieId: Int) {
               movieDB.MovieDao().updateIsLikeById(false, movieId)
            }
        })

        binding.wishRecyclerview.adapter = wishRVAdpater
        wishRVAdpater.addMovies(movieDB.MovieDao().getLikedMovies(true) as ArrayList)

    }

//    private fun setInit(movie: Movie) {
//        binding.moreMovieImageIv.setImageResource(movie.image)
//        binding.moreMovieTitleTv.text = movie.title.toString()
//        if (movie.like == true) {
//            binding.moreLikeOnIv.visibility = View.VISIBLE
//            binding.moreLikeOffIv.visibility = View.INVISIBLE
//        } else {
//            binding.moreLikeOnIv.visibility = View.INVISIBLE
//            binding.moreLikeOffIv.visibility = View.VISIBLE
//        }
//        binding.moreMovieTextTv.text = "일단은 입력없나봄 나도 어떻게 하는지 모르겠고 내가 할 수 있는 한 최선을 다해서 해볼게.. 일단은 입력없나봄 나도 어떻게 하는지 모르겠고 내가 할 수 있는 한 최선을 다해서 해볼게..일단은 입력없나봄 나도 어떻게 하는지 모르겠고 내가 할 수 있는 한 최선을 다해서 해볼게..일단은 입력없나봄 나도 어떻게 하는지 모르겠고 내가 할 수 있는 한 최선을 다해서 해볼게.."
//    }

}

