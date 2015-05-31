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
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import apps.cohen.bali.InsetDecoration;
import apps.cohen.bali.NumberPickerDialog;
import apps.cohen.bali.R;
import apps.cohen.bali.adapters.CategoriesAdapter;
import apps.cohen.bali.adapters.ItemsAdapter;
import apps.cohen.bali.model.Category;
import apps.cohen.bali.model.Item;
import apps.cohen.bali.utils.Apis;

public class PopularItemsFragment extends Fragment {

    private RecyclerView mItemsList;

    private RecyclerView mCaragroiesList;

    private ItemsAdapter mItemsAdapter;

    private CategoriesAdapter mCategoriesAdapter;

    private ArrayList<Category> mCategories;

    private Apis api;

    public static PopularItemsFragment getInstance() {
        return new PopularItemsFragment();
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
        mCaragroiesList = (RecyclerView) rootView.findViewById(R.id.category_list);
        mCaragroiesList.setLayoutManager(getCategoriesLayoutManager());
        mCaragroiesList.addItemDecoration(getItemDecoration());
        mCategoriesAdapter = getCategoriesAdapter();
        mCategoriesAdapter.setCategories(mCategories);
        mCategoriesAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Category category = mCategories.get(position);
                ArrayList<Item> lists = api
                        .getPopularItemsForCategory(category.getId());
                mItemsAdapter.setItems(lists);
            }
        });
        mCaragroiesList.setAdapter(mCategoriesAdapter);

        mItemsList = (RecyclerView) rootView.findViewById(R.id.items_list);
        mItemsList.setLayoutManager(getItemsLayoutManager());
        mItemsList.addItemDecoration(getItemDecoration());

//        mItemsList.getItemAnimator().setAddDuration(1000);
//        mItemsList.getItemAnimator().setChangeDuration(1000);
//        mItemsList.getItemAnimator().setMoveDuration(1000);
//        mItemsList.getItemAnimator().setRemoveDuration(1000);

        mItemsAdapter = getItemsAdapter();
        ArrayList<Item> lists = api
                .getPopularItemsForCategory(0);
        mItemsAdapter.setItems(lists);
        mItemsAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mItemsAdapter.removeItem(position);
                Toast.makeText(getActivity(),
                        "Clicked: " + position + ", index " + mItemsList.indexOfChild(view),
                        Toast.LENGTH_SHORT).show();

            }
        });
        mItemsList.setAdapter(mItemsAdapter);

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
//                dialog.setPickerRange(0, mItemsAdapter.getItemCount());
//                dialog.setOnNumberSelectedListener(new NumberPickerDialog.OnNumberSelectedListener() {
//                    @Override
//                    public void onNumberSelected(int value) {
//                        mItemsAdapter.addItem(value);
//                    }
//                });
//                dialog.show();
//
//                return true;
//            case R.id.action_remove:
//                dialog = new NumberPickerDialog(getActivity());
//                dialog.setTitle("Position to Remove");
//                dialog.setPickerRange(0, mItemsAdapter.getItemCount()-1);
//                dialog.setOnNumberSelectedListener(new NumberPickerDialog.OnNumberSelectedListener() {
//                    @Override
//                    public void onNumberSelected(int value) {
//                        mItemsAdapter.removeItem(value);
//                    }
//                });
//                dialog.show();
//
//                return true;
//            case R.id.action_empty:
//                mItemsAdapter.setItemCount(0);
//                return true;
//            case R.id.action_small:
//                mItemsAdapter.setItemCount(5);
//                return true;
//            case R.id.action_medium:
//                mItemsAdapter.setItemCount(25);
//                return true;
//            case R.id.action_large:
//                mItemsAdapter.setItemCount(196);
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


    protected ItemsAdapter getItemsAdapter() {
        return new ItemsAdapter(getActivity());
    }

    protected CategoriesAdapter getCategoriesAdapter() {
        return new CategoriesAdapter(getActivity());
    }
}
