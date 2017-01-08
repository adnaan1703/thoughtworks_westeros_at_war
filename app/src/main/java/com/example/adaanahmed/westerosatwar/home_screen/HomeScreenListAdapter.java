package com.example.adaanahmed.westerosatwar.home_screen;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adaanahmed.westerosatwar.R;
import com.example.adaanahmed.westerosatwar.UIWidget.ProximaTextView;
import com.example.adaanahmed.westerosatwar.dbUtil.models.King;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


class HomeScreenListAdapter extends RecyclerView.Adapter<HomeScreenListAdapter.ViewHolder> {

    private static final int DUMMY = 0;
    private static final int ACTUAL = 1;

    private ArrayList<King> kings = new ArrayList<>();
    private HomeScreenListingCallbacks homeScreenListingCallbacks;

    HomeScreenListAdapter(HomeScreenListingCallbacks homeScreenListingCallbacks) {
        this.homeScreenListingCallbacks = homeScreenListingCallbacks;
        this.kings.clear();
        King dummy = new King();
        dummy.setName("dummy");
        this.kings.add(dummy);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case DUMMY:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_screen_list_item_dummy_layout, parent, false);
                return new DummyViewHolder(view);
            case ACTUAL:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_screen_list_item_layout, parent, false);
                return new ActualViewHolder(view);
            default:
                Log.d(this.getClass().getName(), "unmatched view type");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updateView(kings.get(position));
    }

    @Override
    public int getItemCount() {
        return kings.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (kings.get(position).getName().equalsIgnoreCase("dummy")) {
            return DUMMY;
        } else {
            return ACTUAL;
        }
    }

    void addData(ArrayList<King> data) {
        int oldSize = kings.size();
        kings.addAll(data);
        notifyItemRangeInserted(oldSize, data.size());
    }

    void clearData() {
        kings.clear();
        King dummy = new King();
        dummy.setName("dummy");
        this.kings.add(dummy);
    }

    abstract class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }

        abstract void updateView(King king);
    }

    private class DummyViewHolder extends ViewHolder {

        DummyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void updateView(King king) {

        }
    }

    private class ActualViewHolder extends ViewHolder implements ProfileImageMapper, View.OnClickListener {

        private ImageView imageView;
        private ProximaTextView title, subtitle, rating;

        ActualViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.home_screen_list_item_image);
            title = (ProximaTextView) itemView.findViewById(R.id.home_screen_list_item_title);
            subtitle = (ProximaTextView) itemView.findViewById(R.id.home_screen_list_item_subtitle);
            rating = (ProximaTextView) itemView.findViewById(R.id.home_screen_list_item_rating);
        }

        @Override
        void updateView(King king) {
            Context context = itemView.getContext();
            Random random = new Random();
            int imageId = context.getResources().getIdentifier(PROFILE_PHOTOS[random.nextInt(50)],
                    "drawable", context.getPackageName());
            imageView.setImageDrawable(ContextCompat.getDrawable(context, imageId));
            title.setText(king.getName());
            subtitle.setText(context.getString(R.string.battle_strength, king.getBattleType()));
            rating.setText(context.getString(R.string.rating, king.getRating()));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            homeScreenListingCallbacks.onKingsItemClick(kings.get(getAdapterPosition()));
        }
    }

    interface HomeScreenListingCallbacks {
        void onKingsItemClick(King king);
    }
}
