package com.smality.lessonnotes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.smality.lessonnotes.R;
import com.smality.lessonnotes.model.Note;
import com.smality.lessonnotes.viewModel.EditNoteViewModel;

public class EditNoteActvity extends AppCompatActivity {

    public static final String NOTE_ID = "note_id";
    public static final String UPDATE_NOTE = "updated_note";
    private EditText edNama;
    private Bundle bundle;
    private String noteId;
    private LiveData<Note> note;

    EditNoteViewModel noteModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note_actvity);

        edNama = findViewById(R.id.ed_text);

        bundle = getIntent().getExtras();

        if (bundle != null){
            noteId = bundle.getString(NOTE_ID);
        }

        noteModel = ViewModelProviders.of(this).get(EditNoteViewModel.class);
        note = noteModel.getNote(noteId);
        note.observe(this, new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                edNama.setText(note.getmNote());
            }
        });
    }

    public void cancleUpdate(View view) {
        finish();
    }

    public void updateNote(View view) {
//        if (!TextUtils.isEmpty(edNama.getText())){
//
//        }
        String updatedNote = edNama.getText().toString();
        Intent resIntent = new Intent();
        resIntent.putExtra(NOTE_ID, noteId);
        resIntent.putExtra(UPDATE_NOTE, updatedNote);
        setResult(RESULT_OK, resIntent);
        finish();
    }
}
