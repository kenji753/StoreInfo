package com.example.storeinfo2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.info1.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class Info1Activity : AppCompatActivity() {

    private val retrofit: Retrofit by lazy { createRetrofit() }
    private val getClient: Client by lazy { retrofit.create(Client::class.java) }
    private val disposable = CompositeDisposable()
    private var posts: Array<Posts> = emptyArray()
    private var restaurants: Array<Restaurants> = emptyArray()

    //private lateinit var storeadapter: Info1Adapter

    private val onImageClick2 = { resId: Int ->
        val intent = Info3Activity.createIntent2(this, resId)
        startActivity(intent)
    }

    private val onImageClick = { phot: IntArray, category: String ->
        val intent = Info2Activity.createIntent(this, phot, category)
        startActivity(intent)
    }

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info1)

        // setUpOld()
        // setUpStore()
        val MoodView = findViewById<View>(R.id.MoodView) as RecyclerView
        val FoodView = findViewById<View>(R.id.FoodView) as RecyclerView
        val DrinkView = findViewById<View>(R.id.DrinkView) as RecyclerView
        val DessertView = findViewById<View>(R.id.FourView) as RecyclerView


        val MoodPhoto = intArrayOf(
            R.drawable.mood11, R.drawable.mood19, R.drawable.mood12, R.drawable.mood14,
            R.drawable.mood19, R.drawable.mood14, R.drawable.mood11
        )

        val FoodPhoto = intArrayOf(
            R.drawable.food15, R.drawable.food14, R.drawable.food13, R.drawable.food11,
            R.drawable.food14, R.drawable.food15, R.drawable.food14
        )

        val DrinkPhoto = intArrayOf(
            R.drawable.drink11, R.drawable.drink12, R.drawable.drink13, R.drawable.drink14,
            R.drawable.drink14, R.drawable.drink16, R.drawable.drink15, R.drawable.drink18,
            R.drawable.drink19, R.drawable.drink15, R.drawable.drink
        )

        val DessertPhoto = intArrayOf(
            R.drawable.dessert11, R.drawable.dessert12, R.drawable.dessert13, R.drawable.dessert14,
            R.drawable.dessert17, R.drawable.dessert18, R.drawable.dessert15, R.drawable.dessert16,
            R.drawable.dessert19
        )

        val MoodManager = GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false)
        val FoodManager = GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false)
        val DrinkManager = GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false)
        val DessertManager = GridLayoutManager(this, 1, RecyclerView.HORIZONTAL, false)

        MoodView.apply {
            layoutManager = MoodManager
            adapter = InfoAdapter(MoodPhoto, this@Info1Activity, onImageClick2)
        }
        FoodView.apply {
            layoutManager = FoodManager
            adapter = InfoAdapter(FoodPhoto, this@Info1Activity, onImageClick2)
        }

        DrinkView.apply {
            layoutManager = DrinkManager
            adapter = InfoAdapter(DrinkPhoto, this@Info1Activity, onImageClick2)
        }

        DessertView.apply {
            layoutManager = DessertManager
            adapter = InfoAdapter(DessertPhoto, this@Info1Activity, onImageClick2)
        }
        categoryMood.setOnClickListener {
            onImageClick(MoodPhoto, "雰囲気")
        }
        categoryFood.setOnClickListener {
            onImageClick(FoodPhoto, "食べ物")
        }

        categoryDrink.setOnClickListener {
            onImageClick(DrinkPhoto, "飲み物")
        }

        categoryDessert.setOnClickListener {
            onImageClick(DessertPhoto, "デザート")
        }

        requestRestaurant()


        test.setOnClickListener{
            post()
        }

    }

    private fun setUpStore() {
        //findViewById(R.id.store_date)
    }

    private fun setUpOld() {


    }


    private fun requestRestaurant() {
        getClient.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                restaurants = data
                shop_name.text = restaurants[0].name
                shop_runtime.text = restaurants[0].business_hours
            }) {
                Log.e(Info1Activity::class.java.simpleName, it.toString())
            }.addTo(disposable)

    }

    private fun requestPosts() {
        getClient.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                posts = data

            }) {
                Log.e(Info1Activity::class.java.simpleName, it.toString())
            }.addTo(disposable)

    }

    // retrofitでpostするサンプル
    private fun post() {
        // postするjsonに対応するクラスをインスタンス化
        val restaurant = PoRestaurants(
            "name",
            "business_hours",
            "image"
        )
        getClient.postRestaurants(restaurant)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(Info1Activity::class.simpleName, "post成功")
            }) {
                Log.d(Info1Activity::class.simpleName, "post失敗")
            }.addTo(disposable)
    }

    companion object {

        const val BASE_URL = "https://pbl-app1-api.appspot.com"
    }


    // retrofit(APIを叩くライブラリ)のインスタンス生成
    private fun createRetrofit(): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }


    // メモリリーク対策
    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }


}
