package com.github.chizoba.alcchallenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chizoba.alcchallenge.utilities.GithubJsonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chizoba on 3/8/2017.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<User> list;

//    HashMap<String, String> result = new HashMap<>();

    Context context;

//    final private ListItemClickListener mOnClickListener;


    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public RecyclerViewAdapter(Context context, ArrayList<User> list) {
        this.list = list;
        this.context = context;
//        this.mOnClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.avatar.setImageResource(list.get(position)); list.get()
//        imageLoader.DisplayImage(list.get(position).get("avatar"));
//        String result = list.get(position);
        Picasso.with(context).load(list.get(position).avatarUrl).into(holder.avatar);
        holder.usernameTextView.setText(list.get(position).username);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView avatar;
        TextView usernameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            avatar = (ImageView) itemView.findViewById(R.id.avatar_img);
            usernameTextView = (TextView) itemView.findViewById(R.id.username_tv);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            Intent intent = new Intent(context, ProfileDetails.class);
            intent.putExtra(Intent.EXTRA_TEXT, list.get(clickedPosition).username);
            intent.putExtra("image", list.get(clickedPosition).avatarUrl);
            intent.putExtra("url", list.get(clickedPosition).profileUrl);
//            intent.putExtra(Intent.EXTRA_TEXT, list.toString());

            context.startActivity(intent);
//            mOnClickListener.onListItemClick(clickedPosition);
        }

    }
}
