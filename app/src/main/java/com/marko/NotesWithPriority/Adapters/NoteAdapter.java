package com.marko.NotesWithPriority.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.marko.NotesWithPriority.Entities.Note;
import com.marko.NotesWithPriority.R;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {

    NoteClickListener mNoteClickListener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    public static DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getInfo().equals(newItem.getInfo()) &&
                    oldItem.getPrior() == newItem.getPrior();
        }
    };

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = getItem(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getInfo());
        holder.priority.setText(String.valueOf(note.getPrior()));
    }


    public Note getNote(int position){
        return getItem(position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, priority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            description = itemView.findViewById(R.id.text_view_description);
            priority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNoteClickListener.onNoteClicked(getItem(getAdapterPosition()));
                }
            });
        }
    }

    public interface NoteClickListener{
        void onNoteClicked(Note note);
    }

    public void setListener(NoteClickListener listener){
        this.mNoteClickListener = listener;
    }
}
