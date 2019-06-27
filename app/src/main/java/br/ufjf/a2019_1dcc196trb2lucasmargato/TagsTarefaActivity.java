package br.ufjf.a2019_1dcc196trb2lucasmargato;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class TagsTarefaActivity extends AppCompatActivity {
    TarefasDBHelper dbhelper;
    TagsTarefaAdapter adapter;
    SQLiteDatabase db;
    String[] visao = {
            TarefasContract.Tag._ID,
            TarefasContract.Tag.COLLUMN_TAG
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags_tarefa);
        RecyclerView rv = findViewById(R.id.rvTagsTarefas);

        dbhelper = new TarefasDBHelper(this);
        db = dbhelper.getWritableDatabase();

        Cursor c = db.query(TarefasContract.Tag.TABLE_NAME, visao, null, null, null, null, null);
        adapter = new TagsTarefaAdapter(c, getIntent().getIntExtra("id", -1), this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));



    }
}
