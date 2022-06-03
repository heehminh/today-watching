package org.app.project

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieTable")
data class Movie(
    var title: String,
    var image: Int? = null,
    var islike: Boolean,
    var text: String?= null,
    var content: String? = null
){
    @PrimaryKey(autoGenerate = true)var id: Int = 0
}

