package com.example.notes_krah;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainFragment extends Fragment {
    private boolean isLandscape;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        initPopupMenu(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
       LinearLayout linearView = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.note_name);

        for (int i = 0; i < notes.length; i++) {
            String note = notes[i];
            TextView tv = new TextView(getContext());
            tv.setText(note);
            tv.setTextSize(30);
            linearView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //, getResources().getStringArray(R.array.note_name)[fi]
                    ((MainActivity)getActivity()).currentNote = new Note(fi);
                    showNoteDescription(((MainActivity)getActivity()).currentNote);
                    tv.setTextColor(Color.BLUE);
                }
            });
        }
    }



    private void initPopupMenu(View view) {
        TextView tv = new TextView(getContext());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = requireActivity();
                PopupMenu popupMenu = new PopupMenu(activity, v);
                activity.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                Menu menu = popupMenu.getMenu();
                menu.findItem(R.id.item2_popup).setVisible(false);
                menu.add(0, 123456, 12, R.string.new_menu_item_added);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.item1_popup:
                                Toast.makeText(getContext(), "Chosen popup item 1", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.item2_popup:
                                Toast.makeText(getContext(), "Chosen popup item 2", Toast.LENGTH_SHORT).show();
                                return true;
                            case 123456:
                                Toast.makeText(getContext(), "Chosen new item added", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add){
            Toast.makeText(getContext(), "Chosen add", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showNoteDescription(Note currentNote) {
        if (isLandscape) {
            showLandNoteDescription(currentNote);
        } else {
            showPortNoteDescription(currentNote);
        }
    }

    private void showLandNoteDescription(Note currentNote) {
        FavoriteFragment detail = FavoriteFragment.newInstance(currentNote);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, detail);  // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNoteDescription(Note currentNote) {
        Toast.makeText(getActivity()," Выбрана заметка # "+currentNote.getNoteName(getActivity()) + " #",Toast.LENGTH_SHORT).show();
    }
}