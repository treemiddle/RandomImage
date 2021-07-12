package com.jay.randomimage.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("blur_hash")
    val blur_hash: String,

    @SerializedName("color")
    val color: String,

    @SerializedName("created_at")
    val created_at: String,

    @SerializedName("current_user_collections")
    val current_user_collections: List<Any>? = null,

    @SerializedName("description")
    val description: String,

    @SerializedName("downloads")
    val downloads: Int,

    @SerializedName("exif")
    val exif: Exif,

    @SerializedName("height")
    val height: Int,

    @SerializedName("id")
    val id: String,

    @SerializedName("liked_by_user")
    val liked_by_user: Boolean,

    @SerializedName("likes")
    val likes: Int,

    @SerializedName("links")
    val links: Links,

    @SerializedName("location")
    val location: Location,

    @SerializedName("updated_at")
    val updated_at: String,

    @SerializedName("urls")
    val urls: Urls,

    @SerializedName("user")
    val user: User,

    @SerializedName("width")
    val width: Int
)