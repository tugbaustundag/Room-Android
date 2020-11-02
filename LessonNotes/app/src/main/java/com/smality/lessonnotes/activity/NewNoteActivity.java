package com.smality.lessonnotes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smality.lessonnotes.R;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NOTE_ADDED = "new_note";

    private EditText edNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        edNewNote = findViewById(R.id.ed_text);

        Button btn = findViewById(R.id.btn_add);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 5
                Intent resultIntent = new Intent();

                if (TextUtils.isDigitsOnly(edNewNote.getText())){
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String note = edNewNote.getText().toString();
                    resultIntent.putExtra(NOTE_ADDED, note);
                    setResult(RESULT_OK, resultIntent);
                }

                finish();
            }
        });
    }
}
