package com.example.adaanahmed.westerosatwar.search_screen;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adaanahmed.westerosatwar.R;
import com.example.adaanahmed.westerosatwar.UIWidget.ProximaTextView;
import com.example.adaanahmed.westerosatwar.utils.ProfileImageMapper;

import java.util.ArrayList;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


class SearchScreenAdapter extends RecyclerView.Adapter<SearchScreenAdapter.ViewHolder> {

    private ArrayList<SearchModel> searchModels = new ArrayList<>();
    private OnSearchItemSelectListener onSearchItemSelectListener;

    public SearchScreenAdapter(OnSearchItemSelectListener onSearchItemSelectListener) {
        this.onSearchItemSelectListener = onSearchItemSelectListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updateView(searchModels.get(position));
    }

    @Override
    public int getItemCount() {
        return searchModels.size();
    }

    void swapData(ArrayList<SearchModel> searchModels) {
        this.searchModels = searchModels;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements ProfileImageMapper, View.OnClickListener {
        private ImageView imageView;
        private ProximaTextView title, rating;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.search_item_image);
            title = (ProximaTextView) itemView.findViewById(R.id.search_item_title);
            rating = (ProximaTextView) itemView.findViewById(R.id.search_item_rating);
            itemView.setOnClickListener(this);
        }

        void updateView(SearchModel searchModel) {
            Context context = itemView.getContext();
            int imgId = context.getResources().getIdentifier(PROFILE_PHOTOS[searchModel.getId()],
                    "drawable", context.getPackageName());

            imageView.setImageDrawable(ContextCompat.getDrawable(context, imgId));
            title.setText(searchModel.getKingName());
            rating.setText(context.getString(R.string.rating, searchModel.getRating()));
        }

        @Override
        public void onClick(View view) {
            if (null != onSearchItemSelectListener) {
                onSearchItemSelectListener.onSearchItemSelect(searchModels.get(getAdapterPosition()).getKingName());
            }
        }
    }

    interface OnSearchItemSelectListener {
        void onSearchItemSelect(String kingName);
    }
}
