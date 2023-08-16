package com.example.snsproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.snsproject.R
import com.example.snsproject.model.Member
import com.example.snsproject.model.Post

class MainPageItemAdapter(val context: Context,val postList:List<Post>) : BaseAdapter() {
    override fun getCount(): Int {
        return postList.size
    }

    override fun getItem(position: Int): Any {
        return postList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_mainpage, parent,false)

        val profileImageView = view.findViewById<ImageView>(R.id.main_page_profile_pic)
        val nameTextView = view.findViewById<TextView>(R.id.mainpage_item_name)
        val feedImageView = view.findViewById<ImageView>(R.id.mainpage_item_feed)
        val contentTextView = view.findViewById<TextView>(R.id.mainpage_item_content)

        val data = postList[position]
        val profileId = context.resources.getIdentifier(data.author, "drawable", context.packageName)
        profileImageView.setImageResource(profileId)

        val data1=postList[position]
        val feedId = context.resources.getIdentifier(data1.postImg, "drawable", context.packageName)
        feedImageView.setImageResource(feedId)

        nameTextView.text=data.author
        val post = data.content
        contentTextView.text = post

        return view
    }
}
