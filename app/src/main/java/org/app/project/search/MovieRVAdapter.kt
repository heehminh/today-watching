package org.app.project.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.app.project.databinding.ItemMovieMoreBinding

class MovieRVAdapter(private val movieList: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(movie: Movie)
        fun onRemoveAlbum(position: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMovieMoreBinding = ItemMovieMoreBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
        // itemView가 클릭되었을때 이벤트 처리
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(movieList[position]) }
    }

    fun addItems(movies: ArrayList<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }
    fun addItem(movie: Movie) {
        movieList.add(movie)
        notifyDataSetChanged()
    }
    fun removeItems() {
        movieList.clear()
        notifyDataSetChanged()
    }
    fun removeItem(position: Int) {
        movieList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movieList.size

    // ViewHolder
    inner class ViewHolder(val binding: ItemMovieMoreBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.moreText.text = movie.title
            binding.moreImage.setImageResource(movie.image!!)
            if (movie.like==true) {
                binding.moreLikeOn.visibility = View.VISIBLE
                binding.moreLikeOff.visibility = View.GONE
            } else {
                binding.moreLikeOn.visibility = View.GONE
                binding.moreLikeOff.visibility = View.VISIBLE
            }
        }
    }





}


