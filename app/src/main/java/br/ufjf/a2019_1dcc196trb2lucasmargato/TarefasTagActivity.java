package br.ufjf.a2019_1dcc196trb2lucasmargato;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class TarefasTagActivity extends AppCompatActivity {
    TarefasDBHelper dbhelper;
    TarefaAdapter adapter;
    SQLiteDatabase db;
    String[] visao = {
            TarefasContract.Tarefa._ID,
            TarefasContract.Tarefa.COLLUMN_TITULO,
            TarefasContract.Tarefa.COLLUMN_DESCRICAO,
            TarefasContract.Tarefa.COLLUMN_LIMITE,
            TarefasContract.Tarefa.COLLUMN_DIFICULDADE,
            TarefasContract.Tarefa.COLLUMN_ULTIMAMODIFICACAO,
            TarefasContract.Tarefa.COLLUMN_ESTADO
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas_tag);

        RecyclerView rv = findViewById(R.id.rvTarefasTag);

        dbhelper = new TarefasDBHelper(this);
        db = dbhelper.getReadableDatabase();

        int tagID = getIntent().getIntExtra("id", -1);


        Cursor c = db.query(TarefasContract.Tarefa.TABLE_NAME, visao, null, null, null, null, null);
        String tarefas = "";
        while (c.moveToNext()) {
            String select = TarefasContract.TagsTarefa.COLLUMN_TAG + " = ? AND" +
                    TarefasContract.TagsTarefa.COLLUMN_TAREFA + " = ?";
            String[] selectArgs = {String.valueOf(tagID), c.getString(c.getColumnIndexOrThrow(TarefasContract.TagsTarefa.COLLUMN_TAREFA))};
            Cursor c2 = db.query(TarefasContract.TagsTarefa.TABLE_NAME, visao, select, selectArgs, null, null, null);
            if (c2.getCount() > 0){
                tarefas += c.getString(c.getColumnIndexOrThrow(TarefasContract.TagsTarefa.COLLUMN_TAREFA))+", ";
            }
        }

        String select = TarefasContract.Tarefa._ID + " IN (?)";
        String[] selectArgs = {tarefas};
        c = db.query(TarefasContract.Tarefa.TABLE_NAME, visao, select, selectArgs, null, null, null);

        adapter = new TarefaAdapter(c);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
