package org.app.project.search

data class Movie(
//    @SerialLizedName("id")
    val id: Long,
    val title: String,
    val overview: String,
    val poster_path: String
)

