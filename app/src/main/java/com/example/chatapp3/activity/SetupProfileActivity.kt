package com.example.chatapp3.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp3.databinding.ActivitySetupProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SetupProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivitySetupProfileBinding
    lateinit var mAuth : FirebaseAuth
    lateinit var mDbRef : DatabaseReference
    lateinit var selectImage : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mDbRef = FirebaseDatabase.getInstance().reference
    }
}