package org.app.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.app.project.databinding.ActivityMainBinding
import org.app.project.search.WrtieFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
        inputDummyDatas()
    }

    private fun initNavigation() {
        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.icon_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.icon_writing -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, WrtieFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.icon_setting -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SettingFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

            }
            false
        }
    }

    fun callFragment(fragment: Fragment) {
        if (supportFragmentManager.backStackEntryCount != 1) { // BackStack에 있는 Fragment 모두 제거
            supportFragmentManager.popBackStackImmediate("${fragment}", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_bnv, fragment).addToBackStack("${fragment}")
        transaction.commit()
    }

    override fun onStart() {
        super.onStart()

        val spf = getSharedPreferences("movie", MODE_PRIVATE)
        val movieId = spf?.getInt("movieId", 0)

        val movieDB = MovieDatabase.getInstance(this)!!
        movie = if (movieId == 0) {
            movieDB.MovieDao().getMovie(1)
        }else {
            movieDB.MovieDao().getMovie(movieId)
        }

        // title, image, like, text
//        val temp = Movie(
//            "겨울왕국",
//            R.drawable.data_movie_image,
//            false,
//            "하기싫다 진짜로 쉬마렵다 지금 시간은 9시 56분 근데 이거 길게 쓰면 스크롤뷰 되는거 맞나? 그리고 이렇게 더미데이터로 넣어주는것도 ㅁ맞나? 원래 넷플릭스 api로 연결해야되는데 그것까지 하면 너무 일커질까봐 걱정되네"
//        )
//
//        temp.id = 1

//        setMovieData()
    }

    private fun inputDummyDatas() {
        val movieDB = MovieDatabase.getInstance(this)!!
        val movies = movieDB.MovieDao().getMovies()

        if (movies.isNotEmpty()) return

        movieDB.MovieDao().insert(Movie("겨울왕국", R.drawable.data_movie_image, false, "귀찮고 집에가 고싶다 근데 이 텍스트는 어케받지?", null))
        movieDB.MovieDao().insert(Movie("김민희", R.drawable.data_movie_image, false, null))
        movieDB.MovieDao().insert(Movie("굿플레이스", null, true, null))
        movieDB.MovieDao().insert(Movie("네버해브아이에버", R.drawable.data_movie_image, false, "에에엥?", null))
        movieDB.MovieDao().insert(Movie("인터스텔라", R.drawable.data_movie_image, true, null))
        movieDB.MovieDao().insert(Movie("안나", R.drawable.data_movie_image, false, "내 UMC 닉네임 안나 ^^안녕하세요 김안나입니다", null))
    }



}