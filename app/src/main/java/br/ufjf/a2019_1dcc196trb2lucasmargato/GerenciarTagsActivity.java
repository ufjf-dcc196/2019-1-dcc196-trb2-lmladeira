package br.ufjf.a2019_1dcc196trb2lucasmargato;

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

public class GerenciarTagsActivity extends AppCompatActivity {
    TarefasDBHelper dbhelper;
    TagAdapter adapter;
    Cursor c;
    public static final int NOVA_TAG = 1;
    public static final int VER_TAREFAS = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_tags);

        RecyclerView rv = findViewById(R.id.rvTags);
        Button btnNovo = findViewById(R.id.buttonNovaTag);

        dbhelper = new TarefasDBHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();

        String[] visao = {
                TarefasContract.Tag._ID,
                TarefasContract.Tag.COLLUMN_TAG,
        };
        c = db.query(TarefasContract.Tag.TABLE_NAME, visao, null, null, null, null, null);
        adapter = new TagAdapter(c);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GerenciarTagsActivity.this, NovaTagActivity.class);
                startActivityForResult(intent, NOVA_TAG);
            }
        });

        adapter.setOnItemClickListener(new TagAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(GerenciarTagsActivity.this, TarefasTagActivity.class);
                c.moveToPosition(position);
                intent.putExtra("id", c.getInt(c.getColumnIndexOrThrow(TarefasContract.Tag._ID)));
                startActivityForResult(intent, VER_TAREFAS);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        adapter.notifyDataSetChanged();
        switch (requestCode) {
            case NOVA_TAG:
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

}
