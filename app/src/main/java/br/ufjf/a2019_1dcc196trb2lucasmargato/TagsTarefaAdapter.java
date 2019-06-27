package br.ufjf.a2019_1dcc196trb2lucasmargato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class TagsTarefaAdapter extends RecyclerView.Adapter<TagsTarefaAdapter.ViewHolder>{
    private Cursor cursor;
    private TagsTarefaAdapter.OnItemClickListener listener;
    private int tarefaID;
    TarefasDBHelper dbhelper;
    SQLiteDatabase db;
    Context context;

    public TagsTarefaAdapter(Cursor c, int tid, Context con) {
        this.cursor = c;
        this.tarefaID = tid;
        this.context = con;
        dbhelper = new TarefasDBHelper(this.context);
        db = dbhelper.getWritableDatabase();
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setCursor(Cursor c){
        this.cursor = c;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(TagsTarefaAdapter.OnItemClickListener listener) { this.listener = listener; }

    @NonNull
    @Override
    public TagsTarefaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.layout_tags_tarefa, viewGroup, false);
        TagsTarefaAdapter.ViewHolder viewHolder = new TagsTarefaAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TagsTarefaAdapter.ViewHolder viewHolder, final int i) {
        int idxID = cursor.getColumnIndexOrThrow(TarefasContract.Tag._ID);
        int idxTag = cursor.getColumnIndexOrThrow(TarefasContract.Tag.COLLUMN_TAG);
        String[] visao = {TarefasContract.TagsTarefa.COLLUMN_TAG, TarefasContract.TagsTarefa.COLLUMN_TAREFA};

        cursor.moveToPosition(i);

        viewHolder.checkboxTag.setText(cursor.getString(idxTag));

        String select = TarefasContract.TagsTarefa.COLLUMN_TAG + " = ? AND " +
                TarefasContract.TagsTarefa.COLLUMN_TAREFA + " = ?";
        String[] selectArgs = {cursor.getString(idxID), String.valueOf(tarefaID)};
        Cursor c = db.query(TarefasContract.TagsTarefa.TABLE_NAME, visao, select, selectArgs, null, null, null);
        //Toast.makeText(context, cursor.getString(idxID) + " " + String.valueOf(tarefaID), Toast.LENGTH_SHORT).show();

        if (c.getCount() != 0){
            viewHolder.checkboxTag.setChecked(true);
        }

        viewHolder.checkboxTag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Toast.makeText(context, cursor.getString(cursor.getColumnIndexOrThrow(TarefasContract.Tag._ID)) + " " + String.valueOf(tarefaID), Toast.LENGTH_SHORT).show();

                String[] visao = {TarefasContract.Tag._ID, TarefasContract.Tag.COLLUMN_TAG};
                Cursor inside = db.query(TarefasContract.Tag.TABLE_NAME, visao, null, null, null, null, null);
                inside.moveToPosition(i);
                //Toast.makeText(context, inside.getString(inside.getColumnIndexOrThrow(TarefasContract.Tag._ID)) + " " + String.valueOf(tarefaID), Toast.LENGTH_SHORT).show();

                if (isChecked){
                    ContentValues values = new ContentValues();
                    values.put(TarefasContract.TagsTarefa.COLLUMN_TAG, inside.getString(inside.getColumnIndexOrThrow(TarefasContract.Tag._ID)));
                    values.put(TarefasContract.TagsTarefa.COLLUMN_TAREFA, String.valueOf(tarefaID));

                    db.insert(TarefasContract.TagsTarefa.TABLE_NAME, null, values);
                } else {
                    String select = TarefasContract.TagsTarefa.COLLUMN_TAG + " = ? AND " +
                            TarefasContract.TagsTarefa.COLLUMN_TAREFA + " = ?";
                    String[] selectArgs = {inside.getString(inside.getColumnIndexOrThrow(TarefasContract.Tag._ID)), String.valueOf(tarefaID)};
                    db.delete(TarefasContract.TagsTarefa.TABLE_NAME, select, selectArgs);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CheckBox checkboxTag;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            checkboxTag = itemView.findViewById(R.id.checkboxTagTarefa);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position !=RecyclerView.NO_POSITION){
                listener.onItemClick(v, position);
            }
        }

    }
}
