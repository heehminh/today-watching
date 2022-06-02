package org.app.project.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import org.app.project.R
import org.app.project.databinding.ItemMovieMoreBinding
import org.app.project.home.Movie
import org.app.project.home.MovieRVAdapter

class WishRVAdapter():
    RecyclerView.Adapter<WishRVAdapter.ViewHolder>(){

    private val items = ArrayList<Movie>()

    interface MyItemClickListener{
        fun onRemoveItem(movieId: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WishRVAdapter.ViewHolder {
        val binding: ItemMovieMoreBinding = ItemMovieMoreBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishRVAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])

    }

    override fun getItemCount(): Int = items.size

    fun addMovies(movies: ArrayList<Movie>){
        this.items.clear()
        this.items.addAll(items)

        notifyDataSetChanged()
    }

    fun removeMovie(position: Int){
        items.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemMovieMoreBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            binding.moreText.text = movie.title
            binding.moreImage.setImageResource(movie.image!!)
            if (movie.islike) {
                binding.moreLikeOn.setImageResource(R.drawable.icon_like_off)
            } else {
                binding.moreLikeOn.setImageResource(R.drawable.icon_like)
            }

        }
    }


}