package org.app.project

import androidx.room.Entity
import androidx.room.PrimaryKey

// 후기를 작성할 때, 내가 남긴 후기 가져올 때
// 타이틀, 별개수, text

@Entity(tableName = "ReviewTable")
data class Review(
    var title: String,
    var rate: String,
    var text: String?= null
){
    @PrimaryKey(autoGenerate = true)var id: Int = 0
}
