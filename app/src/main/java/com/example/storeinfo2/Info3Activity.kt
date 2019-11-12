package com.example.storeinfo2


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
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
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


class Info3Activity : AppCompatActivity() {

    private val retrofit: Retrofit by lazy { createRetrofit() }
    private val getClient: Info1Activity.Client by lazy { retrofit.create(Info1Activity.Client::class.java) }
    private val disposable = CompositeDisposable()
    private val BASEURL = "https://jsondata.okiba.me"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info3)
        
        val photo = getDrawable(intent.getIntExtra(PHOTO, 0))
        val number = intent.getIntExtra(PHOTO, 0)
        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(photo).into(imageView)


        imageButton.setOnClickListener {
            finish()
        }

        favButton.setOnClickListener {

        }

        getClient.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                val post = data.Post[9]
                content.text = post.text
                like_count.text = post.likeCount.toString()
                tagView1.text = post.tag
                post.likeState?.let { state ->
                    if(state) {//色変更処理ココに書く
                        return@let favButton.setColorFilter(0)
                    }
                    favButton.setColorFilter(0)
                }

            }){
                Log.e(Info1Activity::class.java.simpleName, it.toString())
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
            .baseUrl(BASEURL)
            .build()
    }


    // メモリリーク対策
    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }


}