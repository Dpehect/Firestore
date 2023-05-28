package com.example.d8.ui.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.d8.databinding.ActivityMainBinding
import com.example.d8.ui.adapters.NoteListAdapter
import com.example.d8.ui.models.Note
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddNote.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

        // Firestore'dan verileri getirme
        firestore.collection("notes")
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                val notes = querySnapshot.toObjects(Note::class.java)

                // RecyclerView için adapter oluşturma
                val adapter = NoteListAdapter(notes)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
            }
            .addOnFailureListener {
                // Veri getirme başarısız olduğunda yapılacak işlemler
            }
    }
}
