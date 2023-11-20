package com.example.codeforceapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.questionId.setText(items.get(position).getQuestionId());
        holder.questionName.setText(items.get(position).getQuestionName());
        holder.rating.setText(items.get(position).getRating());
        holder.pythonImage.setImageResource(items.get(position).getPythonImage());
        holder.pythonName.setText(items.get(position).getPython());
        holder.cppImage.setImageResource(items.get(position).getCppImage());
        holder.cppName.setText(items.get(position).getCpp());
        holder.javaImage.setImageResource(items.get(position).getJaveImage());
        holder.javaName.setText(items.get(position).getJava());
        holder.acceptance.setText(items.get(position).getAcceptance());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
