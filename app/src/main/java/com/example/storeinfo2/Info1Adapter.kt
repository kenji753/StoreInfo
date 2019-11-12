package com.example.storeinfo2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*
class Info1Adapter (private val context: Context) :
        RecyclerView.Adapter<Info1Adapter.Info1ViewHolder>() {
        var stores: MutableList<Data> = arrayListOf()


    //private val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItemCount(): Int = stores.size

    class Info1ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ShopImageView: ImageView = view.findViewById(R.id.shop_img)
        val ShopNameView: TextView = view.findViewById(R.id.shop_name)
        val PostCountView: TextView = view.findViewById(R.id.postCount)
        val ShopRuntimeView: TextView = view.findViewById(R.id.shop_runtime)
        val contentPictureView: ImageView = view.findViewById(R.id.iview)


    }

    override fun getItemId(p0: Int): Long = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Info1ViewHolder =
        Info1ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.info1,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: Info1ViewHolder, position: Int) {
        val store = stores[position]
        holder.apply {
            userIconImageView.setImageResource(R.mipmap.ic_launcher_round)
            userNameTextView.text = store.store.name
            dateTextView.text = store.store.businessHours
            contentTextView.text = store.store.content
            contentPictureView.setImageResource(R.color.colorPrimary)
            LikeNum.text = stores[position].post.likeCount.toString()
            store.post.likeState?.let { state ->
                if (state) {
                    return@let heartView.setImageResource(R.mipmap.heart2)
                }
                heartView.setImageResource(R.mipmap.heart1)
            }
        }

        holder.heartView.setOnClickListener {
            store.post.likeState?.let { state ->
                if (!state) {
                    store.post.likeCount = store.post.likeCount?.run { this + 1 }
                    holder.heartView.setImageResource(R.mipmap.heart2)
                    holder.LikeNum.text = store.post.likeCount.toString()
                } else {
                    store.post.likeCount = store.post.likeCount?.run { this - 1 }
                    holder.heartView.setImageResource(R.mipmap.heart1)
                    holder.LikeNum.text = store.post.likeCount.toString()
                }
                store.post.likeState = !state
            }
        }
    }


}



*/