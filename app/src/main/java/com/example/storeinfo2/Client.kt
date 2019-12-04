package com.example.storeinfo2

import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface Client {
    // APIのエンドポイントごとにメソッドを定義する
    @GET("/Lookin/posts/")
    fun getPosts(): Observable<Array<Posts>>
    @GET("/Lookin/posts/{id}")
    fun getPosts(@Path("id") getID: Int): Observable<Posts>

    @GET("/Lookin/restaurants/")
    fun getRestaurants(): Observable<Array<Restaurants>>
    @GET("/Lookin/restaurants/{id}")
    fun getRestaurants(@Path("id") getID: Int): Observable<Restaurants>

    @GET("/Lookin/recognize/")
    fun getRecognize(): Observable<Array<Recognize>>

    @POST("/Lookin/restaurants/")
    @Headers("Content-Type: application/json")
    fun postRestaurants(@Body po: poRestaurants): Completable

    @POST("/Lookin/posts/")
    @Headers("Content-Type: application/json")
    fun postPosts(@Body po: poPosts): Completable


}

