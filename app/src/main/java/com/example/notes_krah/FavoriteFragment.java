package com.example.notes_krah;

import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

public class FavoriteFragment extends Fragment {

    public static final String ARG_NOTE = "note";
    private Note note;

    public static FavoriteFragment newInstance(Note note) {
        FavoriteFragment f = new FavoriteFragment();    // создание

        // Передача параметра
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        EditText editTextViewName = view.findViewById(R.id.EditTextViewName);
        EditText editTextViewBody = view.findViewById(R.id.EditTextViewBody);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        DatePicker datePicker = view.findViewById(R.id.datePicker);
        editTextViewName.setText(note.getNoteName(getContext()));
        editTextViewBody.setText(note.getNoteBody(getContext()));

        textViewDate.setText(note.getNoteDateYear(getContext())+" "+ note.getNoteDateMonth(getContext())+" "+ note.getNoteDateDay(getContext()));
        datePicker.init(note.getNoteDateYear(getContext()), note.getNoteDateMonth(getContext()), note.getNoteDateDay(getContext()), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //сохраним полученные данные
                note.setNoteDate(getContext(),year, monthOfYear,dayOfMonth);
                // обновим поле даты
                textViewDate.setText(new StringBuilder().append(note.getNoteDateYear(getContext())).append(" ").append(note.getNoteDateMonth(getContext())).append(" ").append(note.getNoteDateDay(getContext())).toString());
            }
        });
        return view;
    }
}