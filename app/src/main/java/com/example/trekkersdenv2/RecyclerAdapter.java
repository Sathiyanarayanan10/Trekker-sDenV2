package com.example.trekkersdenv2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE=1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public RecyclerAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView state;
        private String location;

        public ItemViewHolder(@NonNull  View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name);
            state=(TextView) itemView.findViewById(R.id.state);
            location="";
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.list_item, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                treklocations trekloc = (treklocations) listRecyclerItem.get(i);

                itemViewHolder.name.setText(trekloc.getName());
                itemViewHolder.state.setText(trekloc.getState());

                // added this
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String location= trekloc.getLocation();;
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(location));
                        intent.setPackage("com.google.android.apps.maps");

                        context.startActivity(intent);
                    }
                });
        }
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}
