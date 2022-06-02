package org.app.project.home
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.google.gson.Gson
//import org.app.project.R
//import org.app.project.databinding.ActivityMoreBinding
//
//class MoreActivity: AppCompatActivity() {
//    lateinit var binding: ActivityMoreBinding
//    private var gson: Gson = Gson()
//
//    private var movies = ArrayList<Movie>()
//    private var nowPos = 0
//    private lateinit var movieDB: MovieDatabase
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMoreBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        initMovieList()
//        initMovie()
//        initClickListener()
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//        val sharedPreferences = getSharedPreferences("movie", MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//        editor.putInt("movieId", movies[nowPos].id)
//        editor.apply()
//    }
//
//    private fun initMovie() {
//        val spf = getSharedPreferences("movie", MODE_PRIVATE)
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
//}