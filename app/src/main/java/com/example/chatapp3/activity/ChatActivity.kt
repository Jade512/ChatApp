package com.example.chatapp3.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp3.MainActivity
import com.example.chatapp3.adapter.MessageAdapter
import com.example.chatapp3.databinding.ActivityChatBinding
import com.example.chatapp3.user.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    lateinit var messageAdapter: MessageAdapter
    lateinit var listMess : ArrayList<Message>
    lateinit var mDbRef : DatabaseReference

    var receiveRoom : String? = null
    var senderRoom : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val name = intent.getStringExtra("name")
        val uid = intent.getStringExtra("uid")
        val senderId = FirebaseAuth.getInstance().currentUser?.uid

        binding.tvName.text = name
        senderRoom = uid + senderId
        receiveRoom = senderId + uid

        mDbRef = FirebaseDatabase.getInstance().reference
        listMess = ArrayList()
        messageAdapter = MessageAdapter(this,listMess)
        binding.rvChat.layoutManager = LinearLayoutManager(this)
        binding.rvChat.adapter = messageAdapter

        mDbRef.child("chats").child(senderRoom!!).child("message")
            .addValueEventListener(object : ValueEventListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {
                    listMess.clear()
                    for (postSnapshot in snapshot.children){
                        val message = postSnapshot.getValue(Message::class.java)
                            listMess.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        binding.sent.setOnClickListener {
            val message = binding.edtMessage.text.toString()
            val messOb = Message(message,senderId!!)
            mDbRef.child("chats").child(senderRoom!!).child("message").push()
                .setValue(messOb).addOnSuccessListener {
                    mDbRef.child("chats").child(receiveRoom!!).child("message").push()
                        .setValue(messOb)
                }
            binding.edtMessage.setText("")
        }

        binding.imBack.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            overridePendingTransition(0,0)
            finish()
        }
    }
}