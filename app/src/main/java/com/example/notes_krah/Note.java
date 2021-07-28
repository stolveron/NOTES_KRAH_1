package com.example.notes_krah;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {


    //так как у нас все данные заметок(кроме даты) хранятся в массивах, нам нужен будет только индекс заметки
    private int notetIndex;
    /* ключи для хранения даты в SharedPreferences */
    /**********/
    private static final String KEY_NOTE_YEAR= "key_note_year_";
    private static final String KEY_NOTE_MONTH= "key_note_monthOfYear_";
    private static final String KEY_NOTE_DAY= "";
    private static final String KEY_PREF= "note_date";
    /**********/


    public Note(int contentIndex){
        this.notetIndex = contentIndex;
    }

    public int getNotetIndex() {
        return notetIndex;
    }

    public String getNoteName(Context mContext) {
        return mContext.getResources().getStringArray(R.array.note_name)[notetIndex];
    }

    public String getNoteBody(Context mContext) {
        return mContext.getResources().getStringArray(R.array.note_body)[notetIndex];
    }

    public int getNoteDateYear(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sp.getInt(KEY_NOTE_YEAR+notetIndex, -1);
    }
    public int getNoteDateMonth(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sp.getInt(KEY_NOTE_MONTH+notetIndex, -1);
    }
    public int getNoteDateDay(Context mContext) {
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        return sp.getInt(KEY_NOTE_DAY+notetIndex, -1);
    }


    // так как мы получаем год, месяц, день в одну операцию, то и сохраним их сразу
    public void setNoteDate(Context mContext, int year, int monthOfYear, int dayOfMonth) {
        // для хранения даты заметки, будем использовать SharedPreferences
        SharedPreferences sp = mContext.getApplicationContext().getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_NOTE_YEAR+notetIndex, year);
        editor.putInt(KEY_NOTE_MONTH+notetIndex, monthOfYear);
        editor.putInt(KEY_NOTE_DAY+notetIndex, dayOfMonth);
        editor.apply();
    }

    /*  блок сохранения состояния*/
    protected Note(Parcel in) {
        notetIndex = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getNotetIndex());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public  Note[] newArray(int size) {
            return new Note[size];
        }
    };
    /*  блок сохранения состояния*/
}
