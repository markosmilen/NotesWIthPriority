package com.marko.NotesWithPriority.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.marko.NotesWithPriority.R;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.marko.roomdatabase8th.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.marko.roomdatabase8th.EXTRA_TITLE";
    public static final String EXTRA_INFO = "com.marko.roomdatabase8th.EXTRA_INFO";
    public static final String EXTRA_PRIOR = "com.marko.roomdatabase8th.EXTRA_PRIOR";
    TextView title, info;
    NumberPicker prior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        title = findViewById(R.id.title_edit_text);
        info = findViewById(R.id.description_edit_text);
        prior = findViewById(R.id.number_picker);
        prior.setMinValue(1);
        prior.setMaxValue(10);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent data = getIntent();
        if (data.hasExtra(EXTRA_ID)){
            setTitle("EDIT NOTE");
            title.setText(data.getStringExtra(EXTRA_TITLE));
            info.setText(data.getStringExtra(EXTRA_INFO));
            prior.setValue(data.getIntExtra(EXTRA_PRIOR, 1));
        }
        setTitle("ADD NOTE");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_note, menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String noteTitle = title.getText().toString();
        String noteInfo = info.getText().toString();
        int notePrior = prior.getValue();

        if (noteTitle.trim().isEmpty() || noteInfo.trim().isEmpty()){
            Toast.makeText(this, "Insert Title and Info", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Intent data = new Intent();
            data.putExtra(EXTRA_TITLE, noteTitle);
            data.putExtra(EXTRA_INFO, noteInfo);
            data.putExtra(EXTRA_PRIOR, notePrior);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);

            if (id != -1){
                data.putExtra(EXTRA_ID, id);
            }
            setResult(RESULT_OK, data);
            finish();
        }
    }
}
