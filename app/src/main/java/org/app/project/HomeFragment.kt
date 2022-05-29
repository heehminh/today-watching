package org.app.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.app.project.databinding.FragmentHomeBinding
import org.app.project.search.Movie
import org.app.project.search.MovieRVAdapter
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private var movieDatas = ArrayList<Movie>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        // TODO: 2022-05-27 넷플릭스 api 연동해서 수정하기
        movieDatas.apply {
            add(Movie("겨울왕국", R.drawable.data_movie_image, false))
            add(Movie("김민희", R.drawable.data_movie_image, false))
            add(Movie("굿플레이스", R.drawable.data_movie_image, true))
            add(Movie("네버해브아이에버", R.drawable.data_movie_image, false))
            add(Movie("인터스텔라", R.drawable.data_movie_image, true))
            add(Movie("안나", R.drawable.data_movie_image, false))
        }

        // 더미데이터와 Adapter 연결
        val movieRVAdapter = MovieRVAdapter(movieDatas)
        binding.homeRecyclerview.adapter = movieRVAdapter
        binding.homeRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        movieRVAdapter.setMyItemClickListener(object: MovieRVAdapter.MyItemClickListener{
            override fun onItemClick(movie: Movie) {
                // startMoreFragment(movie)
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, MoreFragment())
                    .commitAllowingStateLoss()
            }

            override fun onRemoveAlbum(position: Int) {
                movieRVAdapter.removeItem(position)
            }
        })


        return binding.root
    }

    fun startMoreFragment(movie: Movie) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, MoreFragment().apply {
                arguments = Bundle().apply {
                    // val gson = Gson()
                    putString("movie", movie.title)
                }
            })
            .commitAllowingStateLoss()
    }


//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment HomeFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            HomeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}



