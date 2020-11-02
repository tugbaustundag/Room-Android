package com.smality.lessonnotes.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.smality.lessonnotes.helper.NoteDao;
import com.smality.lessonnotes.helper.NoteRoomDatabase;
import com.smality.lessonnotes.model.Note;

public class EditNoteViewModel extends AndroidViewModel {
    private static final String TAG = "EditNoteViewModel";
    private NoteDao noteDao;
    private NoteRoomDatabase db;

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        Log.i(TAG, "EditNoteViewModel: Observer");
        db = NoteRoomDatabase.getDatabase(application);
        noteDao = db.noteDao();
    }

    public LiveData<Note> getNote(String noteId){
        return noteDao.getNote(noteId);
    }
}
