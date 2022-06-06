package org.app.project

import android.content.ActivityNotFoundException
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.app.project.databinding.FragmentMoreBinding

class MoreFragment: Fragment() {
    lateinit var binding: FragmentMoreBinding
    private var gson: Gson = Gson()

    private var movies = ArrayList<Movie>()
    private var nowPos = 0
    private lateinit var movieDB: MovieDatabase
    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreBinding.inflate(inflater, container, false)

        val movieData = arguments?.getString("title")
        movie = gson.fromJson(movieData, Movie::class.java)


        binding.moreBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        initMovieList()
        initMovie()
        initClickListenr()
        clickShareBtn(movie)

        return binding.root
    }

    private fun setInit(movie: Movie) {

        Log.d("MYTAG", "title $movie")
        binding.moreMovieImageIv.setImageResource(movie.image!!)
        binding.moreMovieTitleTv.text = movie.title

        binding.moreMovieTextTv.text = movie?.text
    }

    private fun clickShareBtn(movie: Movie) {
        binding.moreMovieSharingIv.setOnClickListener {
            try {
                val sendText = "오늘뭐보지의 추천작: " + movie.title + "-" + movie?.text.toString()
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, sendText)
                sendIntent.type = "text/plain"
                startActivity(Intent.createChooser(sendIntent, "share"))
            } catch (ignored: ActivityNotFoundException) {
                Log.d("MYTAG", "ignored: $ignored")
            }
        }
    }

    private fun initMovieList() {
        movieDB = MovieDatabase.getInstance(requireContext())!!
        movies.addAll(movieDB.MovieDao().getMovies())
    }

    private fun initMovie() {

        nowPos = movie.id -1
        Log.d("now Movie Id", nowPos.toString())
        setInit(movies[nowPos])
    }


    private fun initClickListenr() {
        binding.moreLikeIv.setOnClickListener {
            setLike(movies[nowPos].islike)
        }
    }

    private fun setLike(isLike: Boolean) {
        movies[nowPos].islike = !isLike
        movieDB.MovieDao().updateIsLikeById(!isLike, movies[nowPos].id)

        if (isLike) {
            binding.moreLikeIv.setImageResource(R.drawable.icon_like_off)
        } else {
            binding.moreLikeIv.setImageResource(R.drawable.icon_like)
        }
    }

}
