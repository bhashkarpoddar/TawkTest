package com.example.tawktest.ui.users.usersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tawktest.R
import com.example.tawktest.data.model.User
import com.example.tawktest.databinding.UserviewItemviewBinding

class UsersAdapter(private var items: MutableList<User>, private val interaction: Interaction) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding: UserviewItemviewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.userview_itemview, parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(items[holder.adapterPosition], interaction, holder.adapterPosition)
        /*holder.itemView.setOnClickListener {
            interaction.onItemSelected(holder.adapterPosition, items[holder.adapterPosition])
        }*/
    }


    override fun getItemCount(): Int {
        return items.size
    }

    class UsersViewHolder(private val binding: UserviewItemviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User, interaction: Interaction, adapterPosition: Int){
            binding.user = item
            binding.executePendingBindings()
        }
    }

    interface Interaction {
        fun onItemClick(position: Int, item: User)
        fun onEdit(position: Int, item: User)
    }

    /*fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }*/

    /*fun insertAt(addressDetail: AddressDetail) {
        items.add(addressDetail)
        notifyItemChanged(items.size - 2)
        notifyItemInserted(items.size - 1)
    }*/

    fun setData(list: MutableList<User>){
        items = list
        notifyDataSetChanged()
    }

}