package com.newsapp.mopsnews

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val newsList: ArrayList<News>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener
    private lateinit var mContext: Context

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listen: onItemClickListener){
        mListener=listen
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)


        return MyViewHolder(itemView,mListener)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]

        holder.hendler.text = currentItem.hendler
        holder.author.text = currentItem.author
        holder.category.text = currentItem.category



        Glide.with(holder.image).load(currentItem.image).into(holder.image)

        //holder.image.setImageBitmap(BitmapFactory.decodeStream(java.net.URL(currentItem.image).openStream()))
        holder.link.text = currentItem.link
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class MyViewHolder(itemView: View, listen: onItemClickListener) :RecyclerView.ViewHolder(itemView){
        val hendler : TextView = itemView.findViewById(R.id.newsHendler)
        val image : ImageView =itemView.findViewById(R.id.newsImage)
        val author : TextView = itemView.findViewById(R.id.author)
        val category : TextView = itemView.findViewById(R.id.category)
        val link : TextView = itemView.findViewById(R.id.link)

        init{
            itemView.setOnClickListener{
                listen.onItemClick(adapterPosition)

            }
        }
    }

}