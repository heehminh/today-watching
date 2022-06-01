package org.app.project.home

import android.content.Context.MODE_PRIVATE
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


        // TODO: 2022-05-27 넷플릭스 api 연동해서 수정하기
//        movieDatas.apply {
//            add(Movie("겨울왕국", R.drawable.data_movie_image, false))
//            add(Movie("김민희", R.drawable.data_movie_image, false))
//            add(Movie("굿플레이스", R.drawable.data_movie_image, true))
//            add(Movie("네버해브아이에버", R.drawable.data_movie_image, false))
//            add(Movie("인터스텔라", R.drawable.data_movie_image, true))
//            add(Movie("안나", R.drawable.data_movie_image, false))
//        }

        // 더미데이터와 Adapter 연결
        val movieRVAdapter = MovieRVAdapter(movieDatas)
        binding.homeRecyclerview.adapter = movieRVAdapter
        binding.homeRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        movieRVAdapter.setMyItemClickListener(object: MovieRVAdapter.MyItemClickListener{
            override fun onItemClick(movie: Movie) {
                changeMoreFragment(movie)
                // changeWishFragment(movie)
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

            override fun onRemoveAlbum(position: Int) {
                movieRVAdapter.removeItem(position)
            }
        })


        return binding.root
    }


}



