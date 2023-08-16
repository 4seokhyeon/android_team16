package com.example.snsproject.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.snsproject.R

// Glide.with(this).load(R.drawable.myimage).circleCrop().into(myimageview);
class MyPageItemAdapter (val context: Context, val itemList: ArrayList<Any>) : BaseAdapter() {
    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItem(position: Int): Any {
        return itemList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_mypage, null)

        val profile = view.findViewById<ImageView>(R.id.mypage_profile_pic)
        val name = view.findViewById<TextView>(R.id.mypage_item_name)
        val feed = view.findViewById<ImageView>(R.id.mypage_item_feed)
        val content = view.findViewById<TextView>(R.id.mypage_item_content)

        val data = itemList[position]

        /**
        val profileId = context.resources.getIdentifier(data.profile, "drawable", context.packageName)
        val feedId = context.resources.getIdentifier(data.feed, "drawable", context.packageName)

        profile.setImageResource(profileId)
        feedId.setImageResource(feedId)
        name.text = data.name
        content.text = data.text
        **/

        return view
    }
}