package br.ufjf.a2019_1dcc196trb2lucasmargato;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class DetalhesTarefaActivity extends AppCompatActivity {
    TarefasDBHelper dbhelper;
    Cursor cursor;
    public static final int TAGS_TAREFA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_tarefa);

        dbhelper = new TarefasDBHelper(this);
        final SQLiteDatabase db = dbhelper.getWritableDatabase();

        final String[] visao = {
                TarefasContract.Tarefa._ID,
                TarefasContract.Tarefa.COLLUMN_TITULO,
                TarefasContract.Tarefa.COLLUMN_DESCRICAO,
                TarefasContract.Tarefa.COLLUMN_LIMITE,
                TarefasContract.Tarefa.COLLUMN_DIFICULDADE,
                TarefasContract.Tarefa.COLLUMN_ULTIMAMODIFICACAO,
                TarefasContract.Tarefa.COLLUMN_ESTADO
        };

        final EditText titulo = findViewById(R.id.editDetTarefa);
        final EditText descricao = findViewById(R.id.editDetDescricao);
        final Spinner dificuldade = findViewById(R.id.spinnerDetDificuldade);
        final EditText limite = findViewById(R.id.editDetLimite);
        final Spinner estado = findViewById(R.id.spinnerDetEstado);
        Button btnConfirmar = findViewById(R.id.buttonDetConfirma);
        Button btnVerTags = findViewById(R.id.buttonDetTagsTarefa);

        dificuldade.setAdapter(new ArrayAdapter<Dificuldade>(this, android.R.layout.simple_selectable_list_item, Dificuldade.values()));
        estado.setAdapter(new ArrayAdapter<Estado>(this, android.R.layout.simple_selectable_list_item, Estado.values()));


        final String select = TarefasContract.Tarefa.COLLUMN_TITULO+" = ?";
        final String[] selectArgs = {getIntent().getStringExtra("name")};
        cursor = db.query(TarefasContract.Tarefa.TABLE_NAME, visao, select, selectArgs, null, null, null);

        int idxID = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa._ID);
        int idxTitulo = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_TITULO);
        int idxDescricao = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_DESCRICAO);
        int idxDificuldade = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_DIFICULDADE);
        int idxLimite = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_LIMITE);
        int idxModificacao = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_ULTIMAMODIFICACAO);
        int idxEstado = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_ESTADO);

        cursor.moveToFirst();
        titulo.setText(cursor.getString(idxTitulo));
        descricao.setText(cursor.getString(idxDescricao));
        dificuldade.setSelection(Dificuldade.getDificuldade(cursor.getString(idxDificuldade)).getValor());
        limite.setText(cursor.getString(idxLimite));
        estado.setSelection(Estado.getEstado(cursor.getString(idxEstado)).getValor());


        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(TarefasContract.Tarefa.COLLUMN_TITULO, titulo.getText().toString());
                values.put(TarefasContract.Tarefa.COLLUMN_DESCRICAO, descricao.getText().toString());
                values.put(TarefasContract.Tarefa.COLLUMN_DIFICULDADE, dificuldade.getSelectedItem().toString());
                values.put(TarefasContract.Tarefa.COLLUMN_LIMITE, limite.getText().toString());
                values.put(TarefasContract.Tarefa.COLLUMN_ULTIMAMODIFICACAO, Calendar.getInstance().getTime().toString());
                values.put(TarefasContract.Tarefa.COLLUMN_ESTADO, estado.getSelectedItem().toString());

                long id = db.update(TarefasContract.Tarefa.TABLE_NAME, values, select, selectArgs);

                finish();
            }
        });

        btnVerTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalhesTarefaActivity.this, TagsTarefaActivity.class);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndexOrThrow(TarefasContract.Tarefa._ID)));
                startActivityForResult(intent, TAGS_TAREFA);
            }
        });
    }
}
