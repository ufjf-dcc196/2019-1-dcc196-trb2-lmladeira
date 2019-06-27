package br.ufjf.a2019_1dcc196trb2lucasmargato;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TarefasDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Tarefas.db";

    public TarefasDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TarefasContract.Tarefa.CREATE_TABLE);
        db.execSQL(TarefasContract.Tag.CREATE_TABLE);
        db.execSQL(TarefasContract.TagsTarefa.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TarefasContract.Tarefa.DROP_TABLE);
        db.execSQL(TarefasContract.Tag.DROP_TABLE);
        db.execSQL(TarefasContract.TagsTarefa.DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
