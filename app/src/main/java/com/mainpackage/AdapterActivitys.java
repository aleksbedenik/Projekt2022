package com.mainpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//extends RecyclerView.Adapter<AdapterActivitys.ViewHolder>
public class AdapterActivitys  {/*
    ApplicationMy app;
    private OnItemClickListener listener;
    public AdapterLibrary(ApplicationMy app, OnItemClickListener listener){
        this.app = app;                               //dostop do podatkov
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout, parent, false);
        //ViewHolder viewHolder = new ViewHolder(view);
        AdapterActivitys.ViewHolder viewHolder = new AdapterActivitys.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // Book tmp = app.getLibrary().getBookAtPos(position);
        //holder.header.setText(tmp.getNaslov()+ "  Price: " + tmp.getCena() + "€  Pages: "+ tmp.getStrani());
    }

    @Override
    public int getItemCount() {
        return app.getLibrary().size();
   // }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView header;
        public View background;                                 //kaj bo v recycleview
        public ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.firstLine);
            iv = (ImageView) itemView.findViewById(R.id.icon);
            background = itemView.findViewById(R.id.mylayoutrow);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(itemView, position );
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(listener !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemLongClick(itemView, position );
                        }
                    }
                    return false;
                }

            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
        void onItemLongClick(View itemView, int position);
    }*/
}
