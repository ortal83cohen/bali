package apps.cohen.bali.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import apps.cohen.bali.InsetDecoration;
import apps.cohen.bali.NumberPickerDialog;
import apps.cohen.bali.R;
import apps.cohen.bali.adapters.AdapterCategories;
import apps.cohen.bali.adapters.AdapterItems;
import apps.cohen.bali.model.Category;
import apps.cohen.bali.model.HidingScrollListener;
import apps.cohen.bali.model.Item;
import apps.cohen.bali.utils.Apis;

public class FragmentPopularItems extends Fragment {

    private RecyclerView mItemsList;

    private RecyclerView mCategoriesList;

    private AdapterItems mAdapterItems;

    private AdapterCategories mAdapterCategories;

    private ArrayList<Category> mCategories;

    private Apis api;

    public static FragmentPopularItems getInstance() {
        return new FragmentPopularItems();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_popular, container, false);

        api = new Apis(getActivity());
        mCategories = api.getCategories();
        mCategoriesList = (RecyclerView) rootView.findViewById(R.id.category_list);
        mCategoriesList.setLayoutManager(getCategoriesLayoutManager());
        mCategoriesList.addItemDecoration(getItemDecoration());
        mAdapterCategories = getCategoriesAdapter();
        mAdapterCategories.setCategories(mCategories);
        mAdapterCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Category category = mCategories.get(position);
                ArrayList<Item> lists = api
                        .getPopularItemsForCategory(category.getId());
                mAdapterItems.setItems(lists);
            }
        });
        mCategoriesList.setAdapter(mAdapterCategories);

        mItemsList = (RecyclerView) rootView.findViewById(R.id.items_list);
        mItemsList.setLayoutManager(getItemsLayoutManager());
        mItemsList.addItemDecoration(getItemDecoration());


        mItemsList.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                mCategoriesList.animate().translationY(-mCategoriesList.getHeight())
                        .setInterpolator(
                                new AccelerateInterpolator(2));
            }

            @Override
            public void onShow() {
                mCategoriesList.animate().translationY(0)
                        .setInterpolator(
                                new DecelerateInterpolator(2));

            }
        });


//        mItemsList.getItemAnimator().setAddDuration(1000);
//        mItemsList.getItemAnimator().setChangeDuration(1000);
//        mItemsList.getItemAnimator().setMoveDuration(1000);
//        mItemsList.getItemAnimator().setRemoveDuration(1000);

        mAdapterItems = getItemsAdapter();
        ArrayList<Item> lists = api
                .getPopularItemsForCategory(0);
        mAdapterItems.setItems(lists);
        mAdapterItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapterItems.removeItem(position);
                Toast.makeText(getActivity(),
                        "Clicked: " + position + ", index " + mItemsList.indexOfChild(view),
                        Toast.LENGTH_SHORT).show();

            }
        });
        mItemsList.setAdapter(mAdapterItems);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.grid_options, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NumberPickerDialog dialog;
//        switch (item.getItemId()) {
//            case R.id.action_add:
//                dialog = new NumberPickerDialog(getActivity());
//                dialog.setTitle("Position to Add");
//                dialog.setPickerRange(0, mAdapterItems.getItemCount());
//                dialog.setOnNumberSelectedListener(new NumberPickerDialog.OnNumberSelectedListener() {
//                    @Override
//                    public void onNumberSelected(int value) {
//                        mAdapterItems.addItem(value);
//                    }
//                });
//                dialog.show();
//
//                return true;
//            case R.id.action_remove:
//                dialog = new NumberPickerDialog(getActivity());
//                dialog.setTitle("Position to Remove");
//                dialog.setPickerRange(0, mAdapterItems.getItemCount()-1);
//                dialog.setOnNumberSelectedListener(new NumberPickerDialog.OnNumberSelectedListener() {
//                    @Override
//                    public void onNumberSelected(int value) {
//                        mAdapterItems.removeItem(value);
//                    }
//                });
//                dialog.show();
//
//                return true;
//            case R.id.action_empty:
//                mAdapterItems.setItemCount(0);
//                return true;
//            case R.id.action_small:
//                mAdapterItems.setItemCount(5);
//                return true;
//            case R.id.action_medium:
//                mAdapterItems.setItemCount(25);
//                return true;
//            case R.id.action_large:
//                mAdapterItems.setItemCount(196);
//                return true;
//            case R.id.action_scroll_zero:
//                mItemsList.scrollToPosition(0);
//                return true;
//            case R.id.action_smooth_zero:
//                mItemsList.smoothScrollToPosition(0);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
        return super.onOptionsItemSelected(item);
    }


    protected RecyclerView.LayoutManager getItemsLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    protected RecyclerView.LayoutManager getCategoriesLayoutManager() {
        return new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new InsetDecoration(getActivity());
    }


    protected AdapterItems getItemsAdapter() {
        return new AdapterItems(getActivity());
    }

    protected AdapterCategories getCategoriesAdapter() {
        return new AdapterCategories(getActivity());
    }
}
