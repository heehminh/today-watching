package org.app.project

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_movie_more.view.*
import org.app.project.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var movie: Movie
    private var movies = ArrayList<Movie>()
    private var nowPos = 0
    private lateinit var movieDB: MovieDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        inputDummyMovies()

        movieDB = MovieDatabase.getInstance(requireContext())!!
        movies.addAll(movieDB.MovieDao().getMovies())

        // 더미데이터와 Adapter 연결
        val movieRVAdapter = MovieRVAdapter(movies)
        binding.homeRecyclerview.adapter = movieRVAdapter
        binding.homeRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        movieRVAdapter.setMyItemClickListener(object: MovieRVAdapter.MyItemClickListener {
            override fun onItemClick(movie: Movie) {
                changeMoreFragment(movie)
            }

            private fun changeMoreFragment(movie: Movie) {
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, MoreFragment().apply {
                        arguments = Bundle().apply {
                            val gson = Gson()
                            val movieJson = gson.toJson(movie)
                            putString("title", movieJson)
                        }
                    })
                    .commitAllowingStateLoss()
            }
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
//        val editor = this.requireActivity().getSharedPreferences("movieSpf", MODE_PRIVATE).edit()
//        editor.putInt("movieId", movie.id)
//        editor.apply()
        val spf = this.requireActivity().getSharedPreferences("movieSpf", MODE_PRIVATE)
        val movieId = spf.getInt("movieId", 0)

        val movieDB = MovieDatabase.getInstance(requireContext())!!
        movie = if (movieId == 0) {
            movieDB.MovieDao().getMovie(1)
        } else {
            movieDB.MovieDao().getMovie(movieId)
        }

        Log.d("Home_MovieId", movie.id.toString())
    }

    private fun inputDummyMovies(){
        val movieDB = MovieDatabase.getInstance(requireContext())!!
        val movies = movieDB.MovieDao().getMovies()

        if (movies.isNotEmpty()) return

        movieDB.MovieDao().insert(Movie("콜", R.drawable.movie01, false, "2020년 11월 27일에 넷플릭스를 통해 공개된 한국 영화. 서로 다른 시간에 살고 있는 두 여자가 한 통의 전화로 연결되며 벌어지는 이야기를 그린 스릴러물. 2015년에 몸 값이라는 단편 영화로 단편영화제에서 두각을 나타낸 이충현 감독의 장편 데뷔작이다.원래 2020년 3월 개봉 예정이었으나, 코로나19 사태로 인해 극장에서의 개봉을 취소하고 넷플릭스에서 오리지널 영화로 독점 공개했다.# 사냥의 시간 이후 두 번째로 극장 개봉 없이 넷플릭스에서 독점공개되는 국내 영화다."))
        movieDB.MovieDao().insert(Movie("모가디슈", R.drawable.movie02, false, "\"제 42회 청룡영화상 올해 가장 흥행한 영화 및 미술 부분, 연출 부분, 최우수 작품 부분 수상작\"\\n" +"2021년 7월 28일에 개봉한 한국 영화. 류승완의 11번째 연출작이기도 하다. 1991년 소말리아 내전 당시, 대한민국과 북한의 대사관 공관원들이 고립된 뒤 함께 목숨을 걸고 소말리아의 수도인 모가디슈를 탈출했던 실제 사건을 모티브로 제작되었으며, 대한민국 외교공관 철수를 주제로 촬영한 한국 최초의 영화이기도 하다."))
        movieDB.MovieDao().insert((Movie("사이버 지옥: n번방을 무너뜨려라", R.drawable.movie03, false, "n번방 성착취물 제작 및 유포 사건을 소재로 한 넷플릭스 오리지널 다큐멘터리")))
        movieDB.MovieDao().insert((Movie("기생충", R.drawable.movie04, false, "\"이 영화는 악인이 없으면서도 비극이고, 광대가 없는데도 희극이다.\n" + "2019년에 개봉된 봉준호 감독의 7번째 장편 영화. 상류층과 하류층. 두 가족의 만남을 다룬 대한민국의 블랙 코미디 가족 드라마 영화다.")))
        movieDB.MovieDao().insert((Movie("엑시트", R.drawable.movie05, false, "짠내 폭발 청년백수, 전대미문의 진짜 재난을 만나다!\n" + "대학교 산악 동아리 에이스 출신이지만\n" + "졸업 후 몇 년째 취업 실패로 눈칫밥만 먹는 용남은\n" + "온 가족이 참석한 어머니의 칠순 잔치에서\n" + "연회장 직원으로 취업한 동아리 후배 의주를 만난다\n" + "어색한 재회도 잠시, 칠순 잔치가 무르익던 중\n" + "의문의 연기가 빌딩에서 피어 오르며\n" + "피할 새도 없이 순식간에 도심 전체는 유독가스로 뒤덮여 일대혼란에 휩싸이게 된다.\n" + "용남과 의주는 산악 동아리 시절 쌓아 뒀던 모든 체력과 스킬을 동원해\n" + "탈출을 향한 기지를 발휘하기 시작하는데…")))
        movieDB.MovieDao().insert((Movie("시동", R.drawable.movie06, false, "정체불명 단발머리 주방장 ‘거석이형’(마동석)을 만난 어설픈 반항아 ‘택일’(박정민)과\n" + "무작정 사회로 뛰어든 의욕충만 반항아 ‘상필’(정해인)이 진짜 세상을 맛보는 유쾌한 이야기를 그린 작품이다.")))
        movieDB.MovieDao().insert((Movie("D.P.", R.drawable.movie07, false, "탈영병들을 잡는 군무 이탈 체포조(D.P.) 준호와 호열이 다양한 사연을 가진 이들을 쫓으며 미처 알지 못했던 현실을 마주하는 이야기")))
        movieDB.MovieDao().insert((Movie("나의 해방일지",R.drawable.movie08, false,"살면서 마음이 정말로 편하고 좋았던 적이 얼마나 있었나?\n" + "항상 무언가 해야 한다는 생각에,\n" + "어떻게든 하루를 알차게 살아내야 한다는 강박에 시달리면서도,\n" + "몸은 움직여주지 않고, 상황은 뜻대로 돌아가지 않고...\n" + "지리한 나날들의 반복. 딱히 큰 문제가 있는 것도 아닌데 왜 행복하지 않을까?\n" + "그렇다고 문제가 없다는 말도 못 한다. 문제가 있는 것도 아니고, 문제가 없는 것도 아니고.\n" + "\n" + "정확하게 말할 수 있는 한 가지는, 행복하지 않다는 것.\n" + "\n" + "해방. 해갈. 희열.\n" + "그런 걸 느껴본 적이 있던가?\n" + "‘아, 좋다. 이게 인생이지.’라고 진심으로 말했던 적이 있던가?\n" + "긴 인생을 살면서 그런 감정을 한 번도 느껴보지 못했다는 게 이상하지 않은가?\n" + "이렇게 지지부진하게 살다가는 게 인생일 리는 없지 않은가?\n" + "어떻게 해야 그런 감정을 느낄 수 있을까?\n" + "\n" + "혹시 아무것도 계획하지 말고 그냥 흘러가 보면 어떨까?\n" + "혹시 아무나 사랑해보면 어떨까?\n" + "관계에서 한 번도 채워진 적이 없기에 이렇게 무기력한 것 아닐까?\n" + "\n" + "시골과 다를 바 없는 경기도의 끝,\n" + "한 구석에 살고 있는, 평범에서도 조금 뒤처져 있는\n" + "삼남매는 어느 날 답답함의 한계에 다다라 길을 찾아나서기로 한다.\n" + "\n" + "각자의 삶에서 해방하기로!")))
        movieDB.MovieDao().insert((Movie("엘리트들", R.drawable.movie09, false,"애초부터 어울리지 않는 옷이었을까. 스페인 최고의 명문 사립 학교에 전학 오게 된 세 명의 평범한 아이들. 행운을 붙잡은 줄 알았던 이들은 살인 사건에 깊이 연루된다.\n" +"넷플릭스에서 2018년 10월 5일에 공개된 스페인 드라마. 장르는 하이틴, 스릴러 드라마이며 시청 등급은 청소년 관람불가. 시즌 1, 2, 3, 4, 5로 방영되었으며 각 시즌마다 8부작이다.\n" + "\n" + "인기가 상당해서 2018년부터 매해 1시즌씩 공개되었고, 시즌 5가 2022년 4월 공개되었으며 . 캐릭터들의 인기 역시 많다보니 정규 시즌 외에 <엘리트들, 못다 한 이야기>로 캐릭터들 사이의 단편이 꾸준히 공개되고 있다. 2022년 시즌 6의 제작이 확정되었다. 시즌 6에서는 새로운 캐릭터로 다섯명이 추가되고, 기존 멤버 몇 명이 하차하는 것으로 발표되었다." )))
        movieDB.MovieDao().insert((Movie("기묘한이야기 3", R.drawable.movie10, false, "1985년, 인디애나주 호킨스. 여름이 후끈 달아오른다. 방학이 시작되고, 동네에 새 쇼핑몰이 개장했다. 호킨스 아이들은 성인이 되는 문턱에 서 있다. 풋사랑이 싹트고 친구들 사이의 우정은 꼬이기 시작한다. 이제 아이들은 우정도 지키면서 성장할 방법을 찾아야만 한다. 한편, 또다시 위험이 다가온다. 오래된 적과 새로 등장한 적의 위협에 맞서며, 일레븐과 친구들은 다시금 되새긴다. 악은 결코 끝나지 않고 진화한다는 것을. 이제 생존을 위해 다시 한번 뭉쳐야 할 때다. 우정은 공포보다 강하다는 걸 마음에 새기며.")))

        val _movies = movieDB.MovieDao().getMovies()
        Log.d("MYHOMETAG", _movies.toString())
    }
}




