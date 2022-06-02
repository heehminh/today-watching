package org.app.project

import androidx.room.*
import org.app.project.home.Movie

@Dao
interface ReviewDao {
    @Insert
    fun insert(review: Review)

    @Update
    fun update(review: Review)

    @Delete
    fun delete(review: Review)

    @Query("SELECT * FROM ReviewTable")
    fun getReviews(): List<Review>

    @Query("SELECT * FROM ReviewTable WHERE id= :id")
    fun getReview(id: Int?): Review
}
