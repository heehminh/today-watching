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

    }




}