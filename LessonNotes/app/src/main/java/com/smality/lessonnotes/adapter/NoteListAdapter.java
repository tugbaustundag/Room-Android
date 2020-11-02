package com.smality.lessonnotes.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.smality.lessonnotes.R;
import com.smality.lessonnotes.activity.EditNoteActvity;
import com.smality.lessonnotes.activity.MainActivity;
import com.smality.lessonnotes.model.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyHolder> {

    public interface OnDeleteClickListener{
        void OnDeleteClickListener(Note myNote);
    }
    private OnDeleteClickListener onDeleteClickListener;

    private Context context;
    private List<Note> list;

    public NoteListAdapter(Context context, OnDeleteClickListener listener) {
        this.context = context;
        this.onDeleteClickListener= listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        if (list != null){
            Note note = list.get(position);
            holder.setData(note.getmNote(), position);
            holder.setListeners();
        } else {
            holder.tvItem.setText(R.string.no_note);
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        else return 0;
    }

    public void setNotes(List<Note> notes) {
        list = notes;
        notifyDataSetChanged();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tvItem;
        private int position;
        private ImageView imgEdit;
        private ImageView imgDelete;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
            imgEdit = itemView.findViewById(R.id.btn_edit);
            imgDelete = itemView.findViewById(R.id.btn_delete);
        }

        public void setData(String getmNote, int position) {
            tvItem.setText(getmNote);
            this.position = position;
        }

        public void setListeners() {
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EditNoteActvity.class);
                    intent.putExtra(EditNoteActvity.NOTE_ID, list.get(position).getId());
                    ((Activity)context).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null){
                        onDeleteClickListener.OnDeleteClickListener(list.get(position));
                    }
                }
            });
        }
    }


}
