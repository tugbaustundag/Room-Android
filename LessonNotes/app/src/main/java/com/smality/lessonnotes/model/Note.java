package com.smality.lessonnotes.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//todo 1
@Entity(tableName = "notes")
public class Note {

    //todo 7
    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    @ColumnInfo(name = "note")
    public String mNote;

    public Note( String id,  String mNote) {
        this.id = id;
        this.mNote = mNote;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getmNote() {
        return mNote;
    }
}
