package com.example.storeinfo2

import io.reactivex.Observable
import retrofit2.http.GET

interface Client {
    // APIのエンドポイントごとにメソッドを定義する
    @GET("/Lookin/posts/")
    fun getPosts(): Observable<Array<Posts>>

    @GET("/Lookin/restaurants/")
    fun getRestrants(): Observable<Array<Restaurants>>

    @GET("/Lookin/recognize/")
    fun getRecognize(): Observable<Array<Recognize>>
}
