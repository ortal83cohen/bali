package apps.cohen.bali.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

import apps.cohen.bali.InsetDecoration;
import apps.cohen.bali.MyApplication;
import apps.cohen.bali.R;
import apps.cohen.bali.activities.ActivityMain;
import apps.cohen.bali.adapters.AdapterItems;
import apps.cohen.bali.model.Item;
import apps.cohen.bali.model.List;
import apps.cohen.bali.utils.Apis;

public class FragmentItemsInList extends Fragment {

    private RecyclerView mRecyclerLists;

    private Apis api;

    private ArrayList<apps.cohen.bali.model.List> mLists;

    private AdapterItems mItemsAdapter;
    private Toolbar mToolbar;
    private ViewGroup mContainerToolbar;
    private Context mContext;
    private View mRootView;
    private int mSelectedItem;
    private List mItem;

    public static FragmentItemsInList newInstance(Context context, int position) {
        FragmentItemsInList fragment = new FragmentItemsInList();
        fragment.setContext(context);
        fragment.setSelectedItem(position);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_items_in_list, container, false);
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });
        mItem = MyApplication.provide(getActivity()).getMyLists().get(mSelectedItem);
        setupToolbar(mRootView);
        mRecyclerLists = (RecyclerView) mRootView.findViewById(R.id.items_in_list);
        mRecyclerLists.setLayoutManager(
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerLists.addItemDecoration(new InsetDecoration(getActivity()));
        mItemsAdapter = new AdapterItems(getActivity());
        mLists = MyApplication.provide(getActivity()).getMyLists();
        api = new Apis(getActivity());
        ArrayList<Item> lists = api
                .getPopularItemsForCategory(mSelectedItem);
        mItemsAdapter.setItems(lists);
        mItemsAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mRecyclerLists.setAdapter(mItemsAdapter);
//        ImageButton mFabButton = (ImageButton) getActivity().findViewById(R.id.fab_button);
//        mFabButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });

        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_items_in_list, menu);
    }
    private void setupToolbar(View rootView) {
        mToolbar = (Toolbar) rootView.findViewById(R.id.app_bar);
        mContainerToolbar = (ViewGroup) rootView.findViewById(R.id.container_app_bar);
        mToolbar.setTitle(getActivity().getString(R.string.items_in_list));
        mToolbar.setNavigationIcon(R.drawable.back_arrow);
        mToolbar.setBackgroundColor(mContext.getResources().getColor(mItem.getColor()));
        mToolbar.setElevation(12);
        ((ActivityMain) getActivity()).setSupportActionBar(mToolbar);
        ((ActivityMain) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_next) {
//            EditText listName = (EditText) mRootView.findViewById(R.id.list_name);
//            if(listName.getText().toString().equals("")){
//                Toast.makeText(mContext, mContext.getString(R.string.list_must_have_name), Toast.LENGTH_SHORT).show();
//            }else {
//                Apis apis = new Apis(mContext);
//                MyApplication.provide(mContext).getMyLists().add(0,new List(0, listName.getText().toString(), mSelectedCategory.getId()));
//                ((ActivityMain)mContext).closeFragmentEditList();
//            }
        }else {
            ((ActivityMain)mContext).closeFragmentItemInList();
        }

        return super.onOptionsItemSelected(item);
    }
    public void notifyListChanged() {
        mItemsAdapter.notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setSelectedItem(int selectedItem) {
        this.mSelectedItem = selectedItem;
    }

}
