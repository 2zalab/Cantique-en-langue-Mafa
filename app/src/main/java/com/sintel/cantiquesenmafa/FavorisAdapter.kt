package com.sintel.cantiquesenmafa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class FavorisAdapter(var context: Context, val titre: Array<String?>): BaseAdapter() {
    private class ViewHolder(row:View?){
        var titre_cantique: TextView

        init {
            this.titre_cantique=row?.findViewById(R.id.titre_cantique) as TextView
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView==null){
            var layout=LayoutInflater.from(context)
            view=layout.inflate(R.layout.cantique_adapter,parent,false)
            viewHolder=ViewHolder(view)
            view.tag=viewHolder
        } else {
            view=convertView
            viewHolder=view.tag as ViewHolder
        }
       viewHolder.titre_cantique.setText(titre[position])

        return view as View
    }


    override fun getItem(position: Int): Any {
        return titre.get(position)!!
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getCount(): Int {
        return titre.size
    }

}
