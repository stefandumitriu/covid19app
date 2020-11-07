package com.codeeina.app_covid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ButtonListAdapter extends RecyclerView.Adapter<ButtonListAdapter.ButtonViewHolder> {
    private final LinkedList<String> mButtonList;
    private LayoutInflater mInflater;

    public ButtonListAdapter(Context context, LinkedList<String> ButtonList) {
        this.mInflater = LayoutInflater.from(context);
        this.mButtonList = ButtonList;
    }

    //Ne creem ViewHolderul
    @NonNull
    @Override
    public ButtonListAdapter.ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.friend_button, parent, false);

        return new ButtonViewHolder(mItemView, this);
    }
    //Setam textul pentru buton
    @Override
    public void onBindViewHolder(@NonNull ButtonListAdapter.ButtonViewHolder holder, int position) {
        holder.friendName.setText(mButtonList.get(position));
    }

    @Override
    public int getItemCount() {
        return mButtonList.size();
    }

    class ButtonViewHolder extends RecyclerView.ViewHolder {
        public final TextView friendName;
        final ButtonListAdapter mAdapter;

        public ButtonViewHolder(View itemView, ButtonListAdapter adapter) {
            super(itemView);
            friendName = itemView.findViewById(R.id.FriendName);
            this.mAdapter = adapter;
        }
    }

}
