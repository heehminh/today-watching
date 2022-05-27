package org.app.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.app.project.databinding.FragmentHomeBinding

//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [HomeFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
class HomeFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
    lateinit var binding: FragmentHomeBinding
    private var movieDatas = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
        binding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val movie = arrayOf(
//            Movie("겨울왕국", R.drawable.data_movie_image, false),
//            Movie("김민희", R.drawable.data_movie_image, false),
//            Movie("굿플레이스", R.drawable.data_movie_image, false),
//            Movie("네버해브아이에버", R.drawable.data_movie_image, false),
//            Movie("엘사", R.drawable.data_movie_image, false),
//            Movie("올라프", R.drawable.data_movie_image, false),
//        )

        // TODO: 2022-05-27 넷플릭스 api 연동해서 수정하기 
        movieDatas.apply {
            add(Movie("겨울왕국", R.drawable.data_movie_image, false))
            add(Movie("김민희", R.drawable.data_movie_image, false))
            add(Movie("굿플레이스", R.drawable.data_movie_image, true))
            add(Movie("네버해브아이에버", R.drawable.data_movie_image, false))
            add(Movie("인터스텔라", R.drawable.data_movie_image, true))
            add(Movie("안나", R.drawable.data_movie_image, false))
        }
        
        


        return inflater.inflate(R.layout.fragment_home, container, false)
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