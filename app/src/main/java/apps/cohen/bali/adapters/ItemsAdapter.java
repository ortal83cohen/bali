package apps.cohen.bali.adapters;


import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import apps.cohen.bali.R;
import apps.cohen.bali.model.Item;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.VerticalItemHolder> {

    private Context mContext;

    private ArrayList<Item> mItems;

    private AdapterView.OnItemClickListener mOnItemClickListener;

    public ItemsAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<Item>();
    }


    public void setItems(ArrayList<Item> items) {

        mItems = items;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        if (position >= mItems.size()) {
            return;
        }

        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public VerticalItemHolder onCreateViewHolder(ViewGroup container, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View root = inflater.inflate(R.layout.view_tem, container, false);

        return new VerticalItemHolder(root, this);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(VerticalItemHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }

    public void onBindViewHolder(VerticalItemHolder itemHolder, int position) {
        Item item = mItems.get(position);

        itemHolder.setBackgroundColor(item.getCategoryId());
        itemHolder.setName(String.valueOf(item.getName()));
        itemHolder.setImage(String.valueOf(item.getImage()));

        final View itemView = itemHolder.itemView;
        if (position % 4 == 0) {
            int height = itemView.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.card_staggered_height);
            itemView.setMinimumHeight(height);
        } else {
            itemView.setMinimumHeight(itemView.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.card_min_width));
        }
    }


    public static class VerticalItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private final View mItemView;

        private TextView mName;

        private ImageView mImage;

        private TextView mAwayScore;


        private ItemsAdapter mAdapter;

        public void setName(String s) {
            mName.setText(s);
        }

        public void setImage(String s) {
            Picasso.with(mAdapter.mContext)
                    .load(s)
                    .placeholder(R.drawable.ic_abstract)
                    .resize(500, 500)
                    .centerCrop()
                    .into(mImage);
        }

        public VerticalItemHolder(View itemView, ItemsAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);

            mAdapter = adapter;

            ViewCompat.setElevation(itemView, 16);
            mItemView = itemView;
            mName = (TextView) itemView.findViewById(R.id.text_item);
            mImage = (ImageView) itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View v) {
            mAdapter.onItemHolderClick(this);
        }


        public void setBackgroundColor(int id) {
            switch (id) {
                case 0:
                    mItemView.setBackgroundColor(
                            mAdapter.mContext.getResources().getColor(R.color.colorCategory0));
                    break;
                case 1:
                    mItemView.setBackgroundColor(
                            mAdapter.mContext.getResources().getColor(R.color.colorCategory1));
                    break;
                case 2:
                    mItemView.setBackgroundColor(
                            mAdapter.mContext.getResources().getColor(R.color.colorCategory2));
                    break;
                case 3:
                    mItemView.setBackgroundColor(
                            mAdapter.mContext.getResources().getColor(R.color.colorCategory3));
                    break;
                case 4:
                    mItemView.setBackgroundColor(
                            mAdapter.mContext.getResources().getColor(R.color.colorCategory4));
                    break;
                case 5:
                    mItemView.setBackgroundColor(
                            mAdapter.mContext.getResources().getColor(R.color.colorCategory5));
                    break;
            }

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
