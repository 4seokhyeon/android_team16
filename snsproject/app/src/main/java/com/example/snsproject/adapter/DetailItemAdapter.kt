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

class DetailItemAdapter(val context: Context, val itemList:MutableList<Post>) : BaseAdapter() {//Post형식의 리스트를 받아옴
    override fun getCount(): Int { // 아이템의 개수
        return itemList.size
    }

    override fun getItem(position: Int): Any {//메소드
        return itemList[position]
    }

    override fun getItemId(position: Int): Long {//아이템의 ID
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {//뷰에 데이터 연결
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_detail, null)

        val profile = view.findViewById<ImageView>(R.id.detail_profile_pic)//리스트뷰 프로필 사진
        val name = view.findViewById<TextView>(R.id.detail_item_name)//리스트뷰 이름
        val feed = view.findViewById<ImageView>(R.id.detail_item_feed)//리스트뷰 피드사진
        val content = view.findViewById<TextView>(R.id.detail_item_content)//리스트뷰 컨텐츠 글
        val viewMoreContent = view.findViewById<TextView>(R.id.detail_content_view_more)//더보기 활성화 버튼(TextView로 작성)
        val viewMoreContentExit = view.findViewById<TextView>(R.id.detail_content_view_more_Exit)//더보기 비활성화 버튼(TextView로 작성)

        val data = itemList[position]//데이터



        val profileId = context.resources.getIdentifier(data.author, "drawable", context.packageName)//프로필 사진 인덱스 생성
        profile.setImageResource(profileId)//프로필 사진 출력
        name.text = data.author//이름 텍스트박스에 작성자 입력
        val feedImgId = context.resources.getIdentifier(data.postImg,"drawable",context.packageName)//feed 이미지 인덱스 생성
        feed.setImageResource(feedImgId)//feed사진 출력
//        content.text = data.content //컨텐츠 글 출력
        setViewMore(content,viewMoreContent,viewMoreContentExit)// 컨텐츠 글 더보기 기능 실행




        return view
    }
}
private fun setViewMore(contentTextView: TextView, viewMoreTextView:TextView,viewMoreTextViewExit: TextView){//더보기 기능 함수
    contentTextView.post{// Content의 내용이 들어있는 글 수정
        val lineCount = contentTextView.layout.lineCount//content의 줄 수 지정
        if(lineCount>0){//줄 수가 0 보다 크면(작으면 실행 X)
            if(contentTextView.layout.getEllipsisCount(lineCount -1) > 0){//content길이가 길어 Ellips 상태라면
                viewMoreTextView.visibility = View.VISIBLE// 더보기 텍스트뷰 보이게 설정
            }
            viewMoreTextView.setOnClickListener{//더보기 텍스트뷰가 클릭이 된다면
                contentTextView.maxLines = Int.MAX_VALUE// 텍스트뷰의 최대길이로 확장
                viewMoreTextView.visibility= View.GONE//더보기 버튼 출력 비활성화(안보이게 수정)
                viewMoreTextViewExit.visibility = View.VISIBLE//숨기기 버튼 출력

            }
            viewMoreTextViewExit.setOnClickListener(){//숨기기 버튼이 눌렸을 때
                contentTextView.maxLines = 1// 최대길이 1로 지정
                viewMoreTextView.visibility=View.VISIBLE//더보기 뷰 보이게 활성화
                viewMoreTextViewExit.visibility=View.GONE// 숨기기 뷰 비활성화
            }
        }
    }
}