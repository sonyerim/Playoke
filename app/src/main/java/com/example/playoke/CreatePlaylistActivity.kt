package com.example.playoke

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.playoke.databinding.ActivityCreatePlaylistBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CreatePlaylistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Firebase.firestore
        val binding = ActivityCreatePlaylistBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cancelBtn.setOnClickListener{
            finish()
        }
        binding.confirmBtn.setOnClickListener{
            val playlistName = binding.playlistName.text?.toString()
            if (!playlistName.isNullOrEmpty()){
                db.collection("users")
                    .document(UserInfo.key)
                    .collection("playlists")
                    .document(playlistName) // Use playlistName as the document ID
                    .set(emptyMap<String, Any>()) // Empty map to create an empty document
                    .addOnSuccessListener { finish() }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this,
                            "Failed To Add Playlist ${e}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
        }
    }
}