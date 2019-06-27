package br.ufjf.a2019_1dcc196trb2lucasmargato;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
    private Cursor cursor;
    private TagAdapter.OnItemClickListener listener;

    public TagAdapter(Cursor c) {
        this.cursor = c;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setCursor(Cursor c){
        this.cursor = c;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(TagAdapter.OnItemClickListener listener) { this.listener = listener; }

    @NonNull
    @Override
    public TagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.layout_tags, viewGroup, false);
        TagAdapter.ViewHolder viewHolder = new TagAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TagAdapter.ViewHolder viewHolder, int i) {
        int idxTag = cursor.getColumnIndexOrThrow(TarefasContract.Tag.COLLUMN_TAG);

        cursor.moveToPosition(i);

        viewHolder.itemTag.setText(cursor.getString(idxTag));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView itemTag;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemTag = itemView.findViewById(R.id.textTag);

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
