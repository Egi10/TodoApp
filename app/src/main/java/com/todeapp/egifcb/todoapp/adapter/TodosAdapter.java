package com.todeapp.egifcb.todoapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.todeapp.egifcb.todoapp.R;
import com.todeapp.egifcb.todoapp.model.Todos;

import java.util.ArrayList;


public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.ViewHolder> {
    private ArrayList<Todos> listTodos;
    private Context context;

    public TodosAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Todos> getTodos() {
        return listTodos;
    }

    public void setTodos(ArrayList<Todos> listTodos) {
        this.listTodos = listTodos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_todos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Todos todos = getTodos().get(position);

        holder.tvText.setText(todos.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, todos.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getTodos().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvText = itemView.findViewById(R.id.tv_text);
        }
    }
}
