package com.example.storeinfo2


data class Restaurants(
    val business_hours: String,
    //val created_at: CreatedAt,
    //val deleted_at: DeletedAt,
    val id: Int,
    val image: String,
    val name: String
    //val updated_at: UpdatedAt
)

data class PoRestaurants(
    val name: String,
    val business_hours: String,
    val image: String
)


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
)

data class Recognize(
    //val created_at: CreatedAt,
    //val deleted_at: DeletedAt,
    val id: Int,
    val restaurant_id: Int
    //val updated_at: UpdatedAt
)

