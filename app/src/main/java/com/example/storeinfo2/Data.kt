package com.example.storeinfo2

import android.R
import java.io.Serializable


data class Restaurants(
    val business_hours: String,
    val id: Int,
    val image: String,
    val name: String
)

data class poRestaurants(
    val name: String,
    val business_hours: String,
    val image: String
) : Serializable

data class poPosts(
    val user_id: Int,
    val restaurant_id: Int,
    val image: String,
    val genre: String,
    val comment: String
) : Serializable


data class Posts(
    val comment: String,
    //val created_at: CreatedAt,
    //val deleted_at: DeletedAt,
    val genre: String,
    val good: Int,
    val id: Int,
    val image: String,
    val restaurant_id: Int,
    //val updated_at: UpdatedAt,
    val user_id: Int
) : Serializable

data class Recognize(
    //val created_at: CreatedAt,
    //val deleted_at: DeletedAt,
    val id: Int,
    val restaurant_id: Int
    //val updated_at: UpdatedAt
)

