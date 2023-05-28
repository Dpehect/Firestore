package com.example.d8.ui.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.d8.databinding.ActivityAddNoteBinding
import com.example.d8.ui.models.Note
import com.google.firebase.firestore.FirebaseFirestore

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val content = binding.editTextContent.text.toString()
            val note = Note(title, content, System.currentTimeMillis())

            // Firestore'a veri ekleme
            firestore.collection("notes")
                .add(note)
                .addOnSuccessListener {
                    // Veri ekleme başarılı olduğunda yapılacak işlemler
                    finish()
                }
                .addOnFailureListener {
                    // Veri ekleme başarısız olduğunda yapılacak işlemler
                }
        }
    }
}
