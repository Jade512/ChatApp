package com.example.chatapp3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp3.R
import com.example.chatapp3.user.Message
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter (
    private val context: Context,
    private val messList : ArrayList<Message>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2

    inner class SentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val sentMess : TextView = itemView.findViewById(R.id.tvMessageSend)
    }

    inner class ReceiveViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val receiveMess : TextView = itemView.findViewById(R.id.tvMessageReceive)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1){
            ReceiveViewHolder(LayoutInflater.from(context).inflate(R.layout.receive_message,parent,false))
        }
        else {
            SentViewHolder(LayoutInflater.from(context).inflate(R.layout.send_message,parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.javaClass == SentViewHolder::class.java) {
            val viewHolder = holder as SentViewHolder
            viewHolder.sentMess.text = messList[position].message
        }
        else
        {
            val viewHolder = holder as ReceiveViewHolder
            viewHolder.receiveMess.text = messList[position].message
        }
    }

    override fun getItemCount(): Int {
        return messList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (FirebaseAuth.getInstance().currentUser?.uid == messList[position].sendId) {
            ITEM_SENT
        }
        else {
            ITEM_RECEIVE
        }
    }
}