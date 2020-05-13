package com.marko.NotesWithPriority.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.marko.NotesWithPriority.Dao.NoteDao;
import com.marko.NotesWithPriority.Database.NoteDatabase;
import com.marko.NotesWithPriority.Entities.Note;

import java.util.List;

public class NoteRepository {

    private NoteDatabase mNoteDatabase;
    private NoteDao mNoteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        mNoteDatabase = NoteDatabase.getInstance(application.getApplicationContext());
        mNoteDao = mNoteDatabase.noteDao();
        allNotes = mNoteDao.allNotes();
    }

    public void insert(Note note){
        new InsertNoteAsynTask(mNoteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsynTask(mNoteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsynTask(mNoteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNotesAsynTask(mNoteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public static class InsertNoteAsynTask extends AsyncTask<Note, Void, Void>{

        private NoteDao mNoteDao;

        public InsertNoteAsynTask(NoteDao noteDao) {
            mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);
            return null;
        }
    }

    public static class UpdateNoteAsynTask extends AsyncTask<Note, Void, Void>{

        private NoteDao mNoteDao;

        public UpdateNoteAsynTask(NoteDao noteDao) {
            mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.update(notes[0]);
            return null;
        }
    }

    public static class DeleteNoteAsynTask extends AsyncTask<Note, Void, Void>{

        private NoteDao mNoteDao;

        public DeleteNoteAsynTask(NoteDao noteDao) {
            mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.delete(notes[0]);
            return null;
        }
    }

    public static class DeleteAllNotesAsynTask extends AsyncTask<Void, Void, Void>{

        private NoteDao mNoteDao;

        public DeleteAllNotesAsynTask(NoteDao noteDao) {
            mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.deleteAllNotes();
            return null;
        }
    }
}
