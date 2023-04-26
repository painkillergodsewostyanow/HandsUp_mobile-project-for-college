package com.example.handsup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AdapterForListView(private val context: Context, private val wordDataList: List<ResultWord>): BaseAdapter() {
    override fun getCount(): Int {
        return wordDataList.size
    }

    override fun getItem(position: Int): ResultWord {
        return wordDataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView = convertView
        if (myConvertView == null){
            myConvertView = LayoutInflater.from(context).inflate(R.layout.my_list_item, parent, false)
        }

        val currentItem = getItem(position)
        val imageItem = myConvertView?.findViewById<ImageView>(R.id.list_item_image)
        val wordItem = myConvertView?.findViewById<TextView>(R.id.list_item_word)
        val numberItem = myConvertView?.findViewById<TextView>(R.id.list_item_number)

        imageItem?.setImageResource(currentItem.image)
        wordItem?.text = currentItem.word
        numberItem?.text = currentItem.number

        return myConvertView!!

    }
}