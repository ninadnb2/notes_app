package com.example.roomdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.roomdb.databinding.ActivityAddNoteBinding
import com.example.roomdb.db.NoteDatabase
import com.example.roomdb.db.NoteEntity
import com.example.roomdb.utils.Constants.NOTE_DATABASE

class AddNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddNoteBinding
    private val noteDB: NoteDatabase by lazy{
        Room.databaseBuilder(this,NoteDatabase::class.java, NOTE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private lateinit var noteEntity: NoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener {
                val title=edtTitle.text.toString()
                val desc= edtDesc.text.toString()

                if(title.isNotEmpty() || desc.isNotEmpty()){
                    noteEntity= NoteEntity(0,title,desc)
                    noteDB.doa().insertNote(noteEntity)
                    finish()
                }
                else{
                    Toast.makeText(this@AddNoteActivity, "Title and Description are Empty", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}