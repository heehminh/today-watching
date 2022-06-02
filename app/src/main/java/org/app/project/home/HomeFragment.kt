package org.app.project.home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import org.app.project.MainActivity
import org.app.project.R
import org.app.project.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private var movieDatas = ArrayList<Movie>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        // 더미데이터와 Adapter 연결
        val movieRVAdapter = MovieRVAdapter(movieDatas)
        binding.homeRecyclerview.adapter = movieRVAdapter
        binding.homeRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        movieRVAdapter.setMyItemClickListener(object: MovieRVAdapter.MyItemClickListener{
            override fun onItemClick(movie: Movie) {
                changeMoreFragment(movie)
            }

            private fun changeMoreFragment(movie: Movie) {
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, MoreFragment().apply {
                        arguments = Bundle().apply {
            //                            putString("title", movie.title)
            //                            putInt("img", movie.image)
            //                            putBoolean("like", movie.like)
                            val gson = Gson()
                            val movieJson = gson.toJson(movie)
                            putString("title", movieJson)
                        }
                    })
                    .commitAllowingStateLoss()
            }

            override fun onRemoveMovie(position: Int) {
                movieRVAdapter.removeItem(position)
            }
        })

        return binding.root
    }


}



