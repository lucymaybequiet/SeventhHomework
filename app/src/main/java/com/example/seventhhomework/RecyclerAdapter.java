package com.example.seventhhomework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    public List list = new List();
    private Context context1;
    public RecyclerAdapter(Context context){
        this.context1 = context;

    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context1).inflate(R.layout.recycler_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerViewHolder holder, int position) {
        holder.textView_time.setText(list.getName(););
    }

    @Override
    public int getItemCount() {
        return list.size;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView textView_time,textView_name,textView_content;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_time = itemView.findViewById(R.id.tv_time);
            textView_name = itemView.findViewById(R.id.tv_name);
            textView_content = itemView.findViewById(R.id.tv_content);
        }
    }
}

