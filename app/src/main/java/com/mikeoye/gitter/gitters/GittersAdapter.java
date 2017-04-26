package com.mikeoye.gitter.gitters;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikeoye.gitter.R;
import com.mikeoye.gitter.data.model.Gitter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by lami on 4/22/2017.
 */

public class GittersAdapter extends RecyclerView.Adapter<GittersAdapter.GittersViewHolder> {

    private Context context;

    private List<Gitter> gitters;

    private OnItemClickListener itemClickListener;

    public GittersAdapter(Context context, List<Gitter> gitters) {
        this.context = context;
        this.gitters = new ArrayList<>(gitters);
    }

    @Override
    public GittersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View gitterItemView = LayoutInflater.from(context).inflate(R.layout.gitters_list_item, parent, false);
        GittersViewHolder viewHolder = new GittersViewHolder(gitterItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GittersViewHolder holder, int position) {
        Gitter gitter = gitters.get(position);
        holder.usernameTextView.setText(gitter.getUsername());

        Glide.with(context)
                .load(gitter.getAvatarUrl())
                .centerCrop()
                .into(holder.avatarPlaceholderImageView);
    }

    @Override
    public int getItemCount() {
        return gitters.size();
    }

    public class GittersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView usernameTextView;
        ImageView avatarPlaceholderImageView;

        public GittersViewHolder(View itemView) {
            super(itemView);
            usernameTextView = ButterKnife.findById(itemView, R.id.gitter_username_tv);
            avatarPlaceholderImageView =  ButterKnife.findById(itemView, R.id.avatar_image_iv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                int itemPosition = getAdapterPosition();
                if (itemPosition != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(itemView, itemPosition);
                }
            }
        }
    }

    public List<Gitter> getGitters() {
        return new ArrayList<>(gitters);
    }

    public void replaceData(List<Gitter> gitters) {
        clearList();
        setList(gitters);
        notifyDataSetChanged();
    }

    public void appendData(List<Gitter> gitters) {
        setList(gitters);
        notifyDataSetChanged();
    }

    private void clearList() {
        gitters.clear();
    }

    private void setList(List<Gitter> data) {
        gitters.addAll(data);
    }

    public static interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}
