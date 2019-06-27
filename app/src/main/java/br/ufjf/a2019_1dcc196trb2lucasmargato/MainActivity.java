package br.ufjf.a2019_1dcc196trb2lucasmargato;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
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
    public static final int NOVA_TAREFA = 1;
    public static final int DETALHES_TAREFA = 2;
    public static final int GERENCIAR_TAGS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.rvTarefas);
        Button btnNovo = findViewById(R.id.buttonNovo);
        Button btnGerenciarTags = findViewById(R.id.buttonGerenciarTags);

        dbhelper = new TarefasDBHelper(this);
        db = dbhelper.getReadableDatabase();

        Cursor c = db.query(TarefasContract.Tarefa.TABLE_NAME, visao, null, null, null, null, null);
        adapter = new TarefaAdapter(c);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener(new TarefaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                TextView txtTitulo = (TextView) itemView.findViewById(R.id.textTitulo);
                Intent intent = new Intent(MainActivity.this, DetalhesTarefaActivity.class);
                intent.putExtra("name", txtTitulo.getText().toString());

                startActivityForResult(intent, DETALHES_TAREFA);

            }
        });

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NovaTarefaActivity.class);
                startActivityForResult(intent, NOVA_TAREFA);

            }
        });

        btnGerenciarTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GerenciarTagsActivity.class);
                startActivityForResult(intent, GERENCIAR_TAGS);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        adapter.setCursor(db.query(TarefasContract.Tarefa.TABLE_NAME, visao, null, null, null, null, null));
        adapter.notifyDataSetChanged();
        switch (requestCode) {
            case NOVA_TAREFA:
                adapter.notifyDataSetChanged();
                break;
            case DETALHES_TAREFA:
                break;
            default:
                break;
        }
    }
}
