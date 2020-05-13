package com.marko.NotesWithPriority.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "note_table")
public class Note {

    @PrimaryKey (autoGenerate = true)
    private int id;

    private String title;
    private String info;
    private int prior;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public int getPrior() {
        return prior;
    }

    public Note(String title, String info, int prior) {
        this.title = title;
        this.info = info;
        this.prior = prior;
    }
}
