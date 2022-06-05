package org.app.project

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.app.project.databinding.ItemMovieMoreBinding

class MovieRVAdapter(private val movies: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(movie: Movie)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieRVAdapter.ViewHolder {
        val binding: ItemMovieMoreBinding = ItemMovieMoreBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieRVAdapter.ViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(movies[position]) }
    }

    override fun getItemCount(): Int = movies.size

    @SuppressLint("NotifyDataSetChanged")
    fun addSongs(movies: ArrayList<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)

        notifyDataSetChanged()
    }

    fun removeMovie(position: Int){
        movies.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemMovieMoreBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            binding.moreText.text = movie.title
            binding.moreImage.setImageResource(movie.image!!)
            if (movie.islike) {
                binding.moreLike.setImageResource(R.drawable.icon_like)
            } else {
                binding.moreLike.setImageResource(R.drawable.icon_like_off)
            }
        }
    }

}