package com.example.cocktailapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CocktailListAdapter(private val list: ArrayList<Cocktails>,
                          private val context: Context): RecyclerView.Adapter<CocktailListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.list_row, p0, false)
return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindView(list[p1])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var des = itemView.findViewById<TextView>(R.id.des)
        var id = itemView.findViewById<TextView>(R.id.id)
        var img = itemView.findViewById<ImageView>(R.id.img)

        fun bindView(cocktails: Cocktails){

            des.text = cocktails.des
            id.text = cocktails.id

            if (!TextUtils.isEmpty(cocktails.img)){
                Picasso.get()
                    .load(cocktails.img)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(img)
        }else{
                Picasso.get()
                    .load(R.mipmap.ic_launcher)
                    .into(img)
            }
    }
}}