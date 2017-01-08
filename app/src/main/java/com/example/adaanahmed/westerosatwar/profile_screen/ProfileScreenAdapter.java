package com.example.adaanahmed.westerosatwar.profile_screen;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adaanahmed.westerosatwar.ProfileImageMapper;
import com.example.adaanahmed.westerosatwar.R;
import com.example.adaanahmed.westerosatwar.UIWidget.ProximaTextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


class ProfileScreenAdapter extends RecyclerView.Adapter<ProfileScreenAdapter.ViewHolder> {


    private ArrayList<ProfileScreenListModel> profileScreenListModels = new ArrayList<>();

    ProfileScreenAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ProfileScreenListModel.HEADER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.profile_screen_list_header_item, parent, false);
                return new HeaderViewHolder(view);
            case ProfileScreenListModel.BATTLE_LOST_HEADER:
            case ProfileScreenListModel.BATTLE_WON_HEADER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.profile_screen_list_item_battles_header, parent, false);
                return new BattleHeaderViewHolder(view);
            case ProfileScreenListModel.BATTLE_WON_CONTENT:
            case ProfileScreenListModel.BATTLE_LOST_CONTENT:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.profile_screen_list_item_battles_body, parent, false);
                return new BattleContentViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updateView(profileScreenListModels.get(position));
    }

    @Override
    public int getItemCount() {
        return profileScreenListModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return profileScreenListModels.get(position).getType();
    }

    void swapData(ArrayList<ProfileScreenListModel> profileScreenListModels) {
        this.profileScreenListModels.clear();
        this.profileScreenListModels.addAll(profileScreenListModels);
        notifyDataSetChanged();
    }

    abstract class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(View itemView) {
            super(itemView);
        }

        abstract void updateView(ProfileScreenListModel profileScreenListModel);
    }

    private class HeaderViewHolder extends ViewHolder implements ProfileImageMapper {

        private ImageView imageView;
        private ProximaTextView title, subtitle, strength, battleStrength;
        private Random random = new Random();


        HeaderViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.profile_screen_image);
            title = (ProximaTextView) itemView.findViewById(R.id.profile_screen_title);
            subtitle = (ProximaTextView) itemView.findViewById(R.id.profile_screen_subtitle);
            strength = (ProximaTextView) itemView.findViewById(R.id.profile_screen_strength);
            battleStrength = (ProximaTextView) itemView.findViewById(R.id.profile_screen_strength_battle);
        }

        @Override
        void updateView(ProfileScreenListModel profileScreenListModel) {
            Context context = itemView.getContext();
            int imageId = context.getResources().getIdentifier(PROFILE_PHOTOS[random.nextInt(50)],
                    "drawable", context.getPackageName());
            imageView.setImageDrawable(ContextCompat.getDrawable(context, imageId));
            title.setText(profileScreenListModel.getKingName());
            subtitle.setText(context.getString(R.string.rating, profileScreenListModel.getRating()));
            strength.setText(context.getString(R.string.strength, profileScreenListModel.getStrength()));
            battleStrength.setText(context.getString(R.string.battle_strength, profileScreenListModel.getBattleStrength()));
        }
    }

    private class BattleHeaderViewHolder extends ViewHolder {

        private ProximaTextView headerTitle, headerSubtitle;

        BattleHeaderViewHolder(View itemView) {
            super(itemView);
            headerTitle = (ProximaTextView) itemView.findViewById(R.id.profile_screen_item_title);
            headerSubtitle = (ProximaTextView) itemView.findViewById(R.id.profile_screen_item_subtitle);
        }

        @Override
        void updateView(ProfileScreenListModel profileScreenListModel) {
            Context context = itemView.getContext();
            if (profileScreenListModel.getType() == ProfileScreenListModel.BATTLE_WON_HEADER) {
                headerTitle.setText(context.getString(R.string.battle_won));
            } else {
                headerTitle.setText(context.getString(R.string.battle_lost));

            }

            headerSubtitle.setText(String.valueOf(profileScreenListModel.getCount()));
        }
    }

    private class BattleContentViewHolder extends ViewHolder {

        private ProximaTextView battleName, year;

        BattleContentViewHolder(View itemView) {
            super(itemView);
            battleName = (ProximaTextView) itemView.findViewById(R.id.profile_screen_item_content);
            year = (ProximaTextView) itemView.findViewById(R.id.profile_screen_item_year);
        }

        @Override
        void updateView(ProfileScreenListModel profileScreenListModel) {
            battleName.setText(profileScreenListModel.getName());
            year.setText(itemView.getContext().getString(R.string.year, profileScreenListModel.getYear()));
        }
    }
}
