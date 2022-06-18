package com.example.chatapp3.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatapp3.R
import com.example.chatapp3.activity.ChatActivity
import com.example.chatapp3.databinding.UserBinding
import com.example.chatapp3.user.User

class UserAdapter(
    val context: Context,
    private val list: ArrayList<User>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    inner class UserViewHolder(val binding: UserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.tvName.text = list[position].name
        holder.binding.root.setOnClickListener {
            val intent = Intent(context,ChatActivity::class.java)
            intent.putExtra("name",list[position].name)
            intent.putExtra("uid",list[position].uid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}