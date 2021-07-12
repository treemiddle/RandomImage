package com.jay.randomimage.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("blur_hash")
    val blurHash: String? = null,

    @SerializedName("color")
    val color: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any>? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("downloads")
    val downloads: Int? = null,

    @SerializedName("exif")
    val exif: Exif? = null,

    @SerializedName("height")
    val height: Int,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("liked_by_user")
    val likedByUser: Boolean? = null,

    @SerializedName("likes")
    val likes: Int? = null,

    @SerializedName("links")
    val links: Links? = null,

    @SerializedName("location")
    val location: Location? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("urls")
    val urls: Urls? = null,

    @SerializedName("user")
    val user: User? = null,

    @SerializedName("width")
    val width: Int
)

data class Exif(
    @SerializedName("aperture")
    val aperture: String? = null,

    @SerializedName("exposure_time")
    val exposureTime: String? = null,

    @SerializedName("focal_length")
    val focalLength: String? = null,

    @SerializedName("iso")
    val iso: Int? = null,

    @SerializedName("make")
    val make: String? = null,

    @SerializedName("model")
    val model: String? = null
)

data class Links(
    @SerializedName("download")
    val download: String? = null,

    @SerializedName("download_location")
    val downloadLocation: String? = null,

    @SerializedName("html")
    val html: String? = null,

    @SerializedName("self")
    val self: String? = null
)

data class LinksX(
    @SerializedName("html")
    val html: String? = null,

    @SerializedName("likes")
    val likes: String? = null,

    @SerializedName("photos")
    val photos: String? = null,

    @SerializedName("portfolio")
    val portfolio: String? = null,

    @SerializedName("self")
    val self: String? = null
)

data class Location(
    @SerializedName("city")
    val city: String? = null,

    @SerializedName("country")
    val country: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("position")
    val position: Position? = null
)

data class Position(
    @SerializedName("latitude")
    val latitude: Double? = null,

    @SerializedName("longitude")
    val longitude: Double? = null
)

data class ProfileImageUrls(
    @SerializedName("large")
    val large: String? = null,

    @SerializedName("medium")
    val medium: String? = null,

    @SerializedName("small")
    val small: String? = null
)

data class Urls(
    @SerializedName("full")
    val full: String? = null,

    @SerializedName("raw")
    val raw: String? = null,

    @SerializedName("regular")
    val regular: String? = null,

    @SerializedName("small")
    val small: String? = null,

    @SerializedName("thumb")
    val thumb: String? = null
)

data class User(
    @SerializedName("bio")
    val bio: String? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("instagram_username")
    val instagramUsername: String? = null,

    @SerializedName("links")
    val links: LinksX? = null,

    @SerializedName("location")
    val location: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("portfolio_url")
    val portfolioUrl: String? = null,

    @SerializedName("total_collections")
    val totalCollections: Int? = null,

    @SerializedName("total_likes")
    val totalLikes: Int? = null,

    @SerializedName("total_photos")
    val totalPhotos: Int? = null,

    @SerializedName("twitter_username")
    val twitterUsername: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("username")
    val username: String? = null,

    @SerializedName("profile_image")
    val profileImageUrls: ProfileImageUrls? = null
)