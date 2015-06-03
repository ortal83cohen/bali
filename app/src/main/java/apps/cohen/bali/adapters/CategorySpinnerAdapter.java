package apps.cohen.bali.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import apps.cohen.bali.R;
import apps.cohen.bali.activities.ActivityMain;
import apps.cohen.bali.model.Category;

public class CategorySpinnerAdapter extends BaseAdapter {

    private final Context mContext;

    private List<Category> mItems = new ArrayList<>();

    public CategorySpinnerAdapter(
            Context context) {
        mContext = context;
    }


    public void addItems(List<Category> CategoryList) {
        mItems.addAll(CategoryList);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
            view = ((ActivityMain)mContext).getLayoutInflater().inflate(R.layout.toolbar_spinner_item_dropdown, parent, false);
            view.setTag("DROPDOWN");
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));
        ImageView image = (ImageView) view.findViewById(R.id.category_image);
        image.setImageDrawable(mContext.getResources().getDrawable(mItems.get(position).getImage()));
        return view;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
            view = ((ActivityMain)mContext).getLayoutInflater().inflate(R.layout.
                    toolbar_spinner_item_selected, parent, false);
            view.setTag("NON_DROPDOWN");
        }
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));

        return view;
    }

    private String getTitle(int position) {
        return position >= 0 && position < mItems.size() ? mItems.get(position).getName() : "";
    }
}