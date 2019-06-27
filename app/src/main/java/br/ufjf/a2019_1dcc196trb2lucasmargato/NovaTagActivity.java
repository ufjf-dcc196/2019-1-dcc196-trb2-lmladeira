package br.ufjf.a2019_1dcc196trb2lucasmargato;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class NovaTagActivity extends AppCompatActivity {
    TarefasDBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tag);

        dbhelper = new TarefasDBHelper(this);

        final EditText tag = findViewById(R.id.editNovaTag);
        Button btnConfirmar = findViewById(R.id.buttonNovaTagConfirma);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(TarefasContract.Tag.COLLUMN_TAG, tag.getText().toString());

                long id = db.insert(TarefasContract.Tag.TABLE_NAME, null, values);
                Toast.makeText(NovaTagActivity.this, "id: "+id, Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
