package org.app.project.search

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import org.app.project.R
import org.app.project.databinding.FragmentSearchResultBinding
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class SearchResultFragment(searchMovie: String) : Fragment() {
    lateinit var binding: FragmentSearchResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
//        MoviesRespository.getPopularMovies()

        return binding.root
    }

}
//
//data class Movie(
//    @SerializedName("id") val id : Long,
//    @SerializedName("title") val title : String,
//    @SerializedName("overview") val overview : String,
//    @SerializedName("poster_path") val poster_path : String
//)
//
//data class GetMoviesResponse(
//    @SerializedName("page") val page: Int,
//    @SerializedName("results") val movies: List<Movie>,
//    @SerializedName("totla_pages") val pages: Int
//
//)
//
//interface Api {
//    @GET("movie/popular")
//    fun getPopularMovies(
//        @Query("api_key") apiKey: String = "Your TMDB API Key",
//        @Query("page") page: Int
//    ): Call<GetMoviesResponse>
//}
//
//object MoviesRespository {
//    private val api: Api
//
//    init {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.themoviedb.org/3/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        api = retrofit.create(Api::class.java)
//    }
//
//    fun getPopularMovies(page: Int = 1){
//        api.getPopularMovies(page = page)
//            .enqueue(object : Callback<GetMoviesResponse> {
//                override fun onResponse(
//                    call: Call<GetMoviesResponse>,
//                    response: Response<GetMoviesResponse>
//                ) {
//                    if(response.isSuccessful) {
//                        val responseBody = response.body()
//                        if(responseBody != null) {
//                            Log.d("Repository", "Movies: ${responseBody.movies}")
//                        } else {
//                            Log.d("Repository", "Failed to get response")
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
//                    Log.e("Repository", "onFailure", t)
//                }
//            })
//    }
//
//
//}
//
