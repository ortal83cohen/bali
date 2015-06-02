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
import java.util.List;

import apps.cohen.bali.R;
import apps.cohen.bali.model.Category;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ItemHolder> {

    private Context mContext;

    private ArrayList<apps.cohen.bali.model.List> mLists;

    private AdapterView.OnItemClickListener mOnItemClickListener;

    public ListsAdapter(Context context) {
        mContext = context;
        mLists = new ArrayList<apps.cohen.bali.model.List>();
    }


    public void addItem(apps.cohen.bali.model.List listItem) {
mLists.add(0,listItem);
notifyDataSetChanged();
    }



    @Override
    public ItemHolder onCreateViewHolder(ViewGroup container, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View root = inflater.inflate(R.layout.view_list, container, false);

        return new ItemHolder(root, this);
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public void setLists(ArrayList<apps.cohen.bali.model.List> categories) {
        mLists.clear();
        mLists = categories;

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
        apps.cohen.bali.model.List item = mLists.get(position);

        itemHolder.setName(String.valueOf(item.getName()));
        itemHolder.setImage(String.valueOf(item.getImage()));

        final View itemView = itemHolder.itemView;

    }

    public static class ItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mName;

        private ImageView mImage;


        private ListsAdapter mAdapter;

        public ItemHolder(View itemView, ListsAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);

            mAdapter = adapter;

            ViewCompat.setElevation(itemView, 16);
            mName = (TextView) itemView.findViewById(R.id.text_category);
            mImage = (ImageView) itemView.findViewById(R.id.image);

        }

        @Override
        public void onClick(View v) {
            mAdapter.onItemHolderClick(this);
        }


        public void setName(String s) {
            mName.setText(s);
        }

        public void setImage(String s) {
            Picasso.with(mAdapter.mContext)
                    .load(s)
                    .placeholder(R.drawable.ic_abstract)
                    .resize(200, 200)
                    .centerCrop()
                    .into(mImage);
        }
    }

    //    @Override
//    public void onBindViewHolder(VerticalItemHolder itemHolder, int position) {
//        Item item = mLists.get(position);
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

