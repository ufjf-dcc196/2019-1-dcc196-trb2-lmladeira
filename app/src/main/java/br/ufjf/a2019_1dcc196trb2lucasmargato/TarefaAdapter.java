package br.ufjf.a2019_1dcc196trb2lucasmargato;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder> {
    private Cursor cursor;
    private OnItemClickListener listener;

    public TarefaAdapter(Cursor c) {
        this.cursor = c;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setCursor(Cursor c){
        this.cursor = c;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener listener) { this.listener = listener; }

    @NonNull
    @Override
    public TarefaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.layout_tarefas, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaAdapter.ViewHolder viewHolder, int i) {
        int idxTitulo = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_TITULO);
        int idxDescricao = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_DESCRICAO);
        int idxDificuldade = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_DIFICULDADE);
        int idxLimite = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_LIMITE);
        int idxModificacao = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_ULTIMAMODIFICACAO);
        int idxEstado = cursor.getColumnIndexOrThrow(TarefasContract.Tarefa.COLLUMN_ESTADO);

        cursor.moveToPosition(i);

        viewHolder.itemTitulo.setText(cursor.getString(idxTitulo));
        viewHolder.itemLimite.setText(cursor.getString(idxLimite));
        viewHolder.itemDescricao.setText(cursor.getString(idxDescricao));
        viewHolder.itemEstado.setText(cursor.getString(idxEstado));
        viewHolder.itemDificuldade.setText(cursor.getString(idxDificuldade));
        viewHolder.itemModificacao.setText(cursor.getString(idxModificacao));
        viewHolder.itemTitulo.setText(cursor.getString(idxTitulo));

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView itemTitulo, itemLimite, itemDescricao, itemEstado, itemModificacao, itemDificuldade;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemTitulo = itemView.findViewById(R.id.textTitulo);
            itemLimite = itemView.findViewById(R.id.textLimite);
            itemDescricao = itemView.findViewById(R.id.textDescricao);
            itemEstado = itemView.findViewById(R.id.textEstado);
            itemModificacao = itemView.findViewById(R.id.textModificacao);
            itemDificuldade = itemView.findViewById(R.id.textDificuldade);

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
