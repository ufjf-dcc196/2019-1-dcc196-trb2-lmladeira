package br.ufjf.a2019_1dcc196trb2lucasmargato;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class NovaTarefaActivity extends AppCompatActivity {
    TarefasDBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);

        dbhelper = new TarefasDBHelper(this);

        final EditText titulo = findViewById(R.id.editNovaTarefa);
        final EditText descricao = findViewById(R.id.editNovaDescricao);
        final Spinner dificuldade = findViewById(R.id.spinnerNovaDificuldade);
        final EditText limite = findViewById(R.id.editNovaLimite);
        Button btnConfirmar = findViewById(R.id.buttonNovaConfirma);

        dificuldade.setAdapter(new ArrayAdapter<Dificuldade>(this, android.R.layout.simple_selectable_list_item, Dificuldade.values()));

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultado = new Intent();
                Tarefa tarefa = new Tarefa(titulo.getText().toString(), descricao.getText().toString(),
                        Dificuldade.getDificuldade(dificuldade.getSelectedItem().toString()), limite.getText().toString());

                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(TarefasContract.Tarefa.COLLUMN_TITULO, tarefa.getTitulo());
                values.put(TarefasContract.Tarefa.COLLUMN_DESCRICAO, tarefa.getDescricao());
                values.put(TarefasContract.Tarefa.COLLUMN_DIFICULDADE, tarefa.getDificuldade().toString());
                values.put(TarefasContract.Tarefa.COLLUMN_LIMITE, tarefa.getLimite());
                values.put(TarefasContract.Tarefa.COLLUMN_ULTIMAMODIFICACAO, Calendar.getInstance().getTime().toString());
                values.put(TarefasContract.Tarefa.COLLUMN_ESTADO, Estado.AFAZER.toString());

                long id = db.insert(TarefasContract.Tarefa.TABLE_NAME, null, values);
                Toast.makeText(NovaTarefaActivity.this, "id: "+id, Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK, resultado);
                finish();
            }
        });
    }
}
