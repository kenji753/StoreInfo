package com.example.storeinfo2


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.info3.*
import android.widget.Toast
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.info1.*
import kotlinx.android.synthetic.main.info3.imageButton
import okhttp3.OkHttpClient
import okio.Utf8
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue


class Info3Activity : AppCompatActivity() {

    private val retrofit: Retrofit by lazy { createRetrofit() }
    private val getClient: Client by lazy { retrofit.create(Client::class.java) }
    private val disposable = CompositeDisposable()
    private var postID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info3)


        //val photo = getDrawable(intent.getIntExtra(PHOTO, 0))
        val photo = "https://storage.cloud.google.com/pbl-lookin/image/jpeg/post/mood/mood1.jpg"
        //val photo = "https://www.pakutaso.com/shared/img/thumb/nuko-8_TP_V1.jpg"
        val number = intent.getIntExtra(PHOTO, 0)
        val imageView = findViewById<ImageView>(R.id.imageView)
        //Glide.with(this).load(photo).into(imageView)
        Glide.with(this ).load(photo).override(400,400).centerCrop().into(imageView)
            /*
        intent.getStringExtra(PHOTO).let {
            when(it){
                "mood" -> {
                    postID = 1
                }
                "food" ->{
                    postID = 3
                }
                "drink" -> {
                    postID = 4
                }
                else -> {
                    postID = 6
                }
            }

        }
*/
        imageButton.setOnClickListener {
            finish()
        }

        favButton.setOnClickListener {
           // getClient.postRestaurants(poRestaurants("potato","12:00-13:00","TEST.png"))
        }



        getClient.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                content.text = data[0].comment
                like_count.text = data[0].good.toString()
                //tagView1.text = post.tag
                /*post.likeState?.let { state ->
                    if(state) {//色変更処理ココに書く
                        return@let favButton.setColorFilter(0)
                    }
                    favButton.setColorFilter(0)
                }

                 */
            }){
                Log.e(Info3Activity::class.java.simpleName, it.toString())
                Log.e("ABABABABABABABABA", it.toString())
            }.addTo(disposable)
    }

    companion object {
        private const val PHOTO: String = "photo"


        fun createIntent2(activity: Activity, @DrawableRes photo: Int) =
            Intent(activity, Info3Activity::class.java).apply {
                putExtra(PHOTO, photo)
            }
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
            .baseUrl(Info1Activity.BASE_URL)
            .build()
    }


    // メモリリーク対策
    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }


}