package org.app.project.setting

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import org.app.project.R
import org.app.project.Review
import org.app.project.ReviewDatabase
import org.app.project.databinding.ItemReviewBinding
import android.content.Context as Context1

class ReviewRVAdapter(private val reviewList: ArrayList<Review>): RecyclerView.Adapter<ReviewRVAdapter.ViewHolder>() {
    private var gson: Gson = Gson()
    lateinit var reviewDB: ReviewDatabase

    interface MyItemClickListener {
        fun onItemClick(review: Review)
        fun onRemoveMovie(position: Int)
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemReviewBinding =
            ItemReviewBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reviewList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(reviewList[position]) }
    }

    fun addItems(reviews: ArrayList<Review>) {
        reviewList.clear()
        reviewList.addAll(reviews)
        notifyDataSetChanged()
    }

    fun addItem(review: Review) {
        reviewList.add(review)
        notifyDataSetChanged()
    }

    fun removeItems() {
        reviewList.clear()
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        reviewList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = reviewList.size

    inner class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(review: Review) {

            binding.itemTitleTv.setText("${review.title}")
            // binding.itemTitleTv.text = review.title
            binding.itemRatingTv.text = review.rate
            binding.itemDateTv.text = review.date

            if (review.rate == "5.0") {
                binding.itemTitleTv.setTextColor(R.color.main_red)
                binding.itemRatingTv.setTextColor(R.color.main_red)
            }
        }
    }
}
