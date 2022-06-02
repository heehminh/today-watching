package org.app.project.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import org.app.project.MainActivity
import org.app.project.R
import org.app.project.databinding.FragmentMoreBinding

class MoreFragment: Fragment() {
    lateinit var binding: FragmentMoreBinding
    private var gson: Gson = Gson()

    private var movies = ArrayList<Movie>()
    private var nowPos = 0
    private lateinit var movieDB: MovieDatabase

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
            movie.islike = false
        }
        binding.moreLikeOffIv.setOnClickListener {
            binding.moreLikeOnIv.visibility = View.VISIBLE
            binding.moreLikeOffIv.visibility = View.INVISIBLE
            movie.islike = true
        }

        binding.moreBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        setInit(movie)
        clickShareBtn(movie)

//        initMovieList()
//        initMovie()
//        initClickListener()

        return binding.root
    }

    private fun setInit(movie: Movie) {
        Log.d("MYTAG", "title $movie.title like $movie.like")
        binding.moreMovieImageIv.setImageResource(movie.image!!)
        binding.moreMovieTitleTv.text = movie.title

        if (movie.islike) {
            binding.moreLikeOnIv.visibility = View.VISIBLE
            binding.moreLikeOffIv.visibility = View.INVISIBLE
        } else {
            binding.moreLikeOnIv.visibility = View.INVISIBLE
            binding.moreLikeOffIv.visibility = View.VISIBLE
        }

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
}


//    override fun onPause() {
//        super.onPause()
//
//        val sharedPreferences = getSharedPreferences("movie", AppCompatActivity.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//        editor.putInt("movieId", movies[nowPos].id)
//        editor.apply()
//    }
//
//    private fun initMovie() {
//        val spf = getSharedPreferences("movie", AppCompatActivity.MODE_PRIVATE)
//        val movieId = spf.getInt("movieId", 0)
//
//        nowPos = getPlayingMoviePosition(movieId)
//        setMovie(movies[nowPos])
//    }
//
//    private fun getPlayingMoviePosition(movieId: Int): Int {
//        for (i in 0 until movies.size) {
//            if (movies[i].id == movieId) {
//                return 1
//            }
//        }
//        return 0
//    }
//
//    private fun setMovie(movie: Movie) {
//        val content = resources.getIdentifier(movie.content, "raw", this.packageName)
//
//        binding.moreMovieTitleTv.text = movie.title
//        binding.moreMovieImageIv.setImageResource(movie.image!!)
//        binding.moreMovieTextTv.text = movie.text
//
//        if (movie.islike) {
//            binding.moreLikeOnIv.setImageResource(R.drawable.icon_like)
//        } else {
//            binding.moreLikeOnIv.setImageResource(R.drawable.icon_like_off)
//        }
//    }
//
//    private fun initClickListener() {
//        binding.moreLikeOnIv.setOnClickListener {
//            setLike(movies[nowPos].islike)
//        }
//    }
//
//    private fun setLike(islike: Boolean) {
//        movies[nowPos].islike = !islike
//        movieDB.MovieDao().updateIsLikeById(!islike, movies[nowPos].id)
//    }
//
//    private fun initMovieList() {
//        movieDB = MovieDatabase.getInstance(this)!!
//        movies.addAll(movieDB.MovieDao().getMovies())
//
//    }
//
//}
