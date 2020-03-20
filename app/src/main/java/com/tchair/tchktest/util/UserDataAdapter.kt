package com.tchair.tchktest.util


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tchair.tchktest.R
import com.tchair.tchktest.net.UserData


class UserDataAdapter() : PagedListAdapter<UserData, ViewHolder>(DiffUtilCallBack()){

    var data = listOf<UserData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Log.i("holder", "CREATED")
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.login.text = item?.login
        holder.type.text = item?.type
        holder.id.text = item?.id.toString()
        /*holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate()
        }*/
    }

    class ListItemListener(val clickListener: (userLogin: String) -> Unit){
        fun onClick(user: UserData) = clickListener(user.login)
    }

}



class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val login: TextView = itemView.findViewById(R.id.user_login)
    val type: TextView = itemView.findViewById(R.id.user_type)
    val id: TextView = itemView.findViewById(R.id.user_id)
}

class DiffUtilCallBack : DiffUtil.ItemCallback<UserData>() {
    override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
        return oldItem.login == newItem.login
                && oldItem.avatarUrl == newItem.avatarUrl
    }

}