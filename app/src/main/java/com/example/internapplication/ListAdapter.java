package com.example.internapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.internapplication.Model.List;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.UserViewHolder> {
    private Context context;
    private java.util.ArrayList<List.Data> list;

    public ListAdapter(Context context, ArrayList<List.Data> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_lislayout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        com.example.internapplication.Model.List.Data data = list.get(position);
        holder.textViewId.setText(data.getId());
        holder.textViewEmail.setText(data.getEmail());
        holder.textViewFN.setText(data.getFirst_name());
        holder.textViewLN.setText(data.getLast_name());
        Glide.with(context)
                .load(data.getAvatar())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgViewAvatar);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewEmail, textViewFN, textViewLN;
        ImageView imgViewAvatar;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.tvId);
            textViewEmail = itemView.findViewById(R.id.tvEmail);
            textViewFN = itemView.findViewById(R.id.tvFN);
            textViewLN = itemView.findViewById(R.id.tvLN);
            imgViewAvatar = itemView.findViewById(R.id.tvImg);
        }
    }
}
