package com.example.searchserials


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchserials.ItemFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_item.view.*


class MyItemRecyclerViewAdapter(
    private val listener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {
    private val values = ArrayList<Serial>()
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Serial
            listener?.onListFragmentInteraction(item)
        }
    }

    fun updateValue(list: ArrayList<Serial>){
        values.clear()
        values.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.name
        holder.genres.text = item.genres?.joinToString(",")
        item.image?.let { Picasso.get().load(it).into(holder.image); }

        with(holder.view) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.name
        val genres: TextView = view.genres
        val image: ImageView = view.image

        override fun toString(): String {
            return super.toString() + " '" + genres.text + "'"
        }
    }
}
