package apps.cohen.bali.adapters;


import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import apps.cohen.bali.R;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ItemHolder> {


    private ArrayList<Item> mItems;

    private AdapterView.OnItemClickListener mOnItemClickListener;

    public CategoriesAdapter() {
        mItems = new ArrayList<Item>();
    }

    public static Item generateDummyItem() {
        Random random = new Random();
        return new Item("Upset Home", "Upset Away",
                random.nextInt(100),
                random.nextInt(100));
    }

    public static List<Item> generateDummyData(int count) {
        ArrayList<Item> items = new ArrayList<Item>();

        for (int i = 0; i < count; i++) {
            items.add(new Item("Losers", "Winners", i, i + 5));
        }

        return items;
    }

    public void addItem(int position) {
        if (position > mItems.size()) {
            return;
        }

        mItems.add(position, generateDummyItem());
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        if (position >= mItems.size()) {
            return;
        }

        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup container, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View root = inflater.inflate(R.layout.view_category, container, false);

        return new ItemHolder(root, this);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItemCount(int count) {
        mItems.clear();
        mItems.addAll(generateDummyData(count));

        notifyDataSetChanged();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(ItemHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    public void onBindViewHolder(ItemHolder itemHolder, int position) {
        Item item = mItems.get(position);

        itemHolder.setAwayScore(String.valueOf(item.awayScore));

        final View itemView = itemHolder.itemView;

    }

    public static class Item {

        public String homeTeam;

        public String awayTeam;

        public int homeScore;

        public int awayScore;

        public Item(String homeTeam, String awayTeam, int homeScore, int awayScore) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.homeScore = homeScore;
            this.awayScore = awayScore;
        }
    }

    public static class ItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mAwayScore;


        private CategoriesAdapter mAdapter;

        public ItemHolder(View itemView, CategoriesAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);

            mAdapter = adapter;

            ViewCompat.setElevation(itemView, 16);
            mAwayScore = (TextView) itemView.findViewById(R.id.text_category);

        }

        @Override
        public void onClick(View v) {
            mAdapter.onItemHolderClick(this);
        }


        public void setAwayScore(CharSequence awayScore) {
            mAwayScore.setText(awayScore);
        }


    }

    //    @Override
//    public void onBindViewHolder(VerticalItemHolder itemHolder, int position) {
//        Item item = mItems.get(position);
//
//        itemHolder.setAwayScore(String.valueOf(item.awayScore));
//        if (position == 0 || position == 1) {
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            lp.setMargins(0, 280, 0, 0);
//            itemHolder.itemView.setLayoutParams(lp);
//        }
//
//    }
}
