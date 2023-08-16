package com.example.snsproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.snsproject.R
import com.example.snsproject.model.Post
import org.w3c.dom.Text

class DetailItemAdapter(val context: Context, val itemList:MutableList<Post>) : BaseAdapter() {
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
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_detail, null)

        val profile = view.findViewById<ImageView>(R.id.detail_profile_pic)
        val name = view.findViewById<TextView>(R.id.detail_item_name)
        val feed = view.findViewById<ImageView>(R.id.detail_item_feed)
        val content = view.findViewById<TextView>(R.id.detail_item_content)
        val viewMoreContent = view.findViewById<TextView>(R.id.detail_content_view_more)
        val viewMoreContentExit = view.findViewById<TextView>(R.id.detail_content_view_more_Exit)

        val data = itemList[position]



        val profileId = context.resources.getIdentifier(data.author, "drawable", context.packageName)
        profile.setImageResource(profileId)
        name.text = data.author
        val feedImgId = context.resources.getIdentifier(data.postImg,"drawable",context.packageName)
        feed.setImageResource(feedImgId)
//        content.text = data.content
        setViewMore(content,viewMoreContent,viewMoreContentExit)




        return view
    }
}
private fun setViewMore(contentTextView: TextView, viewMoreTextView:TextView,viewMoreTextViewExit: TextView){
    contentTextView.post{
        val lineCount = contentTextView.layout.lineCount
        if(lineCount>0){
            if(contentTextView.layout.getEllipsisCount(lineCount -1) > 0){
                viewMoreTextView.visibility = View.VISIBLE
            }
            viewMoreTextView.setOnClickListener{
                contentTextView.maxLines = Int.MAX_VALUE
                viewMoreTextView.visibility= View.GONE
                viewMoreTextViewExit.visibility = View.VISIBLE

            }
            viewMoreTextViewExit.setOnClickListener(){
                contentTextView.maxLines = 1
                viewMoreTextView.visibility=View.VISIBLE
                viewMoreTextViewExit.visibility=View.GONE
            }
        }
    }
}