package com.example.storeinfo2
/*
data class Data(
    val Post: List<Post>,
    val Store: Store
)

data class Post(
    val category: String,
    val likeCount: Int,
    val likeState: Boolean,
    val picture: String,
    val tag: String,
    val text: String
)

data class Store(
    val businessHours: String,
    val name: String,
    val picture: String
)
*/

data class Data(
    val post_id: Int,
    val post_user_id: Int,
    val post_restaurant_id: Int,
    val post_image: String,
    val good: Int,
    val text: String,
    val created_at: String,
    val updated_at: String,
    val deleted_at: String
)


data class Restaurants(
    val business_hours: String,
    val created_at: CreatedAt,
    val deleted_at: DeletedAt,
    val id: Int,
    val image: String,
    val name: String,
    val updated_at: UpdatedAt
)


data class Posts(
    val comment: String,
    val created_at: CreatedAt,
    val deleted_at: DeletedAt,
    val genre: String,
    val good: Int,
    val id: Int,
    val image: String,
    val restaurant_id: Int,
    val updated_at: UpdatedAt,
    val user_id: Int
)

data class Recognize(
    val created_at: CreatedAt,
    val deleted_at: DeletedAt,
    val id: Int,
    val restaurant_id: Int,
    val updated_at: UpdatedAt
)

data class CreatedAt(
    val Time: String,
    val Valid: Boolean
)

data class DeletedAt(
    val Time: String,
    val Valid: Boolean
)

data class UpdatedAt(
    val Time: String,
    val Valid: Boolean
)
