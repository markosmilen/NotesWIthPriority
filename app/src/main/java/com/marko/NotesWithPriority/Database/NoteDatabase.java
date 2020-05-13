package com.marko.NotesWithPriority.Database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.marko.NotesWithPriority.Dao.NoteDao;
import com.marko.NotesWithPriority.Entities.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context, NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(noteCallback)
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback noteCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new GenerateDatabaseAsynTask(instance).execute();
        }
    };

    public static class GenerateDatabaseAsynTask extends AsyncTask<Void, Void, Void>{
        private NoteDao mNoteDao;

        public GenerateDatabaseAsynTask(NoteDatabase db) {
            mNoteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.insert(new Note("Example TITLE 1", "Description Note 1", 1));
            mNoteDao.insert(new Note("Example TITLE 2", "Description Note 2", 2));
            mNoteDao.insert(new Note("Example TITLE 3", "Description Note 3", 3));
            return null;
        }
    }
}
