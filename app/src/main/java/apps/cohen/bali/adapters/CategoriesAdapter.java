package apps.cohen.bali.adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import apps.cohen.bali.model.Category;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ItemHolder> {

    private Context mContext;

    private ArrayList<Category> mCategories;

    private AdapterView.OnItemClickListener mOnItemClickListener;

    public CategoriesAdapter(Context context) {
        mContext = context;
        mCategories = new ArrayList<Category>();
    }


    public void addItem(int position) {
        if (position > mCategories.size()) {
            return;
        }

//        mCategories.add(position, new Item());
        notifyItemInserted(position);
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup container, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View root = inflater.inflate(R.layout.view_category, container, false);

        return new ItemHolder(root, this);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void setCategories(ArrayList<Category> categories) {
        mCategories.clear();
        mCategories = categories;

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
        Category item = mCategories.get(position);

        itemHolder.setName(String.valueOf(item.getName()));
        itemHolder.setImage(item.getImage());

        final View itemView = itemHolder.itemView;

    }

    public static class ItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mName;

        private ImageView mImage;


        private CategoriesAdapter mAdapter;

        public ItemHolder(View itemView, CategoriesAdapter adapter) {
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

        public void setImage(int s) {
            mImage.setImageDrawable(mAdapter.mContext.getResources().getDrawable(s));
        }
    }

    //    @Override
//    public void onBindViewHolder(VerticalItemHolder itemHolder, int position) {
//        Item item = mCategories.get(position);
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
