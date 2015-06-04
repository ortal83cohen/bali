package apps.cohen.bali.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;

import java.util.ArrayList;

import apps.cohen.bali.InsetDecoration;
import apps.cohen.bali.MyApplication;
import apps.cohen.bali.R;
import apps.cohen.bali.activities.ActivityMain;
import apps.cohen.bali.adapters.AdapterLists;
import apps.cohen.bali.utils.Apis;

public class FragmentItemsInList extends Fragment {

    private RecyclerView mRecyclerLists;

    private Apis api;

    private ArrayList<apps.cohen.bali.model.List> mLists;

    private AdapterLists mListsAdapter;

    public static FragmentItemsInList newInstance() {
        FragmentItemsInList fragment = new FragmentItemsInList();
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
        View rootView = inflater.inflate(R.layout.fragment_items_lists, container, false);

        api = new Apis(getActivity());

        mRecyclerLists = (RecyclerView) rootView.findViewById(R.id.lists);
        mRecyclerLists.setLayoutManager(
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerLists.addItemDecoration(new InsetDecoration(getActivity()));
        mListsAdapter = new AdapterLists(getActivity());

        mListsAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mRecyclerLists.setAdapter(mListsAdapter);
        ImageButton mFabButton = (ImageButton) getActivity().findViewById(R.id.fab_button);
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityMain) getActivity()).openFragmentEditList();

            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLists = MyApplication.provide(getActivity()).getMyLists();
        mListsAdapter.setLists(mLists);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.grid_options, menu);
    }

    public void notifyListChanged() {
        mListsAdapter.notifyDataSetChanged();
    }
}
