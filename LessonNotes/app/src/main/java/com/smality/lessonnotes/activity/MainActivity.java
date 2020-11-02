package com.smality.lessonnotes.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smality.lessonnotes.adapter.NoteListAdapter;
import com.smality.lessonnotes.model.Note;
import com.smality.lessonnotes.viewModel.NoteViewModel;
import com.smality.lessonnotes.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NoteListAdapter.OnDeleteClickListener{

    private static final String TAG = "MainActivity";
    private static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2;
    private NoteViewModel noteViewModel;

    private NoteListAdapter noteListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        RecyclerView rv = findViewById(R.id.rv_data);

        noteListAdapter = new NoteListAdapter(this, this);
        FloatingActionButton fab = findViewById(R.id.fab);
        rv.setAdapter(noteListAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 4
                startActivityForResult(new Intent(MainActivity.this, NewNoteActivity.class), NEW_NOTE_ACTIVITY_REQUEST_CODE);
            }
        });

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        noteViewModel.getAllNotesC().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteListAdapter.setNotes(notes);
            }
        });
    }

    //todo 6
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){

            final String note_id = UUID.randomUUID().toString();
            Note note = new Note(note_id, data.getStringExtra(NewNoteActivity.NOTE_ADDED));
            noteViewModel.insertC(note);

            Toast.makeText(this, "Add note", Toast.LENGTH_SHORT).show();
        } else if(requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Note note = new Note(
                    data.getStringExtra(EditNoteActvity.NOTE_ID),
                    data.getStringExtra(EditNoteActvity.UPDATE_NOTE));
            noteViewModel.updateC(note);
            Toast.makeText(this, "Note update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Close", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnDeleteClickListener(Note myNote) {
        noteViewModel.deleteC(myNote);
    }
}
