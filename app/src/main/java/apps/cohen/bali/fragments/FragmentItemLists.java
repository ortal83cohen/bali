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
import apps.cohen.bali.R;
import apps.cohen.bali.adapters.AdapterLists;
import apps.cohen.bali.utils.Apis;

public class FragmentItemLists extends Fragment {

    private RecyclerView mRecyclerLists;

    private Apis api;

    private ArrayList<apps.cohen.bali.model.List> mLists;

    private AdapterLists mListsAdapter;

    public static FragmentItemLists newInstance() {
        FragmentItemLists fragment = new FragmentItemLists();
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
        mLists = api.getLists();
        mRecyclerLists = (RecyclerView) rootView.findViewById(R.id.lists);
        mRecyclerLists.setLayoutManager(
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerLists.addItemDecoration(new InsetDecoration(getActivity()));
        mListsAdapter = new AdapterLists(getActivity());
        mListsAdapter.setLists(mLists);
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
                mListsAdapter.addItem(
                        new apps.cohen.bali.model.List(0, getActivity().getString(R.string.mangal), "",
                                "http://shtieble.net/news/images/posts/2013/02/header_5312.jpg"));
            }
        });

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.grid_options, menu);
    }
}
