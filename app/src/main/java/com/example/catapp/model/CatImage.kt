package com.example.catapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="cat_image_table")
data class CatImage(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "image_url") val url: String)
