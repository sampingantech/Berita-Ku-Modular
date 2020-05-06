package com.anangkur.beritaku.data.model.news

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Article(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "author")
    @SerializedName("author")
    val author: String? = "",

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String? = "",

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String? = "",

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String? = "",

    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    val urlToImage: String? = "",

    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    val publishedAt: String? = "",

    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: String? = "",

    @ColumnInfo(name = "category")
    val category: String? = ""

): Parcelable