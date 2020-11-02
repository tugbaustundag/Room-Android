package com.smality.lessonnotes.viewModel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.smality.lessonnotes.model.Note;
import com.smality.lessonnotes.helper.NoteDao;
import com.smality.lessonnotes.helper.NoteRoomDatabase;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private static final String TAG = "NoteViewModel";
    private NoteRoomDatabase noteRoomDatabase;
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;


    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRoomDatabase = NoteRoomDatabase.getDatabase(application);
        noteDao = noteRoomDatabase.noteDao();
        allNotes = noteDao.getAllNote();
    }

    public void insertC(Note note){
        new InsertAsyncTask(noteDao).execute(note);
    }

    public LiveData<List<Note>> getAllNotesC(){
        return allNotes;
    }

    public void updateC(Note note){
        new UpdateAsyncTask(noteDao).execute(note);
    }

    public void deleteC(Note note){
        new DeleteAsyncTask(noteDao).execute(note);
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        Log.i(TAG, "onCleared: Viewmodel Destroyed");
    }

    private class OperationAsyncTask extends AsyncTask<Note, Void, Void>{
        NoteDao noteDao;

        public OperationAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            return null;
        }
    }
    private class InsertAsyncTask extends OperationAsyncTask{

        public InsertAsyncTask(NoteDao noteDao){
            super(noteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends OperationAsyncTask{
        public UpdateAsyncTask(NoteDao noteDao) {
            super(noteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends OperationAsyncTask{

        public DeleteAsyncTask(NoteDao noteDao) {
            super(noteDao);
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
}
