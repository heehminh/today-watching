package org.app.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.app.project.databinding.ItemWishMovieBinding

class WishRVAdapter():
    RecyclerView.Adapter<WishRVAdapter.ViewHolder>(){
    private val wishList = ArrayList<Movie>()

    interface MyItemClickListener{
        fun onRemoveItem(movieId: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemWishMovieBinding = ItemWishMovieBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(wishList[position])
        holder.binding.itemDeleteIv.setOnClickListener { mItemClickListener.onRemoveItem(position) }
    }

    override fun getItemCount(): Int = wishList.size

    fun addMovies(movies: ArrayList<Movie>){
        this.wishList.clear()
        this.wishList.addAll(movies)

        notifyDataSetChanged()
    }

    fun removeWish(position: Int) {
        wishList.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemWishMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            binding.itemWishTitleTv.text = movie.title
            binding.itemWishTextTv.text = movie.text
            binding.itemWishImageIv.setImageResource(movie.image!!)
        }
    }
}