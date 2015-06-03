package apps.cohen.bali.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import apps.cohen.bali.R;
import apps.cohen.bali.activities.ActivityMain;


/**
 * @author user
 * @date 2015-06-03
 */
public class FragmentEditList extends Fragment {
    private Toolbar mToolbar;    private ViewGroup mContainerToolbar;
    public static FragmentEditList newInstance() {
        FragmentEditList fragment = new FragmentEditList();
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
        View rootView = inflater.inflate(R.layout.fragment_edit_list, container, false);
        setupDrawer(rootView);

        return rootView;
    }

    private void setupDrawer(View rootView) {
        mToolbar = (Toolbar) rootView.findViewById(R.id.app_bar);
        mContainerToolbar = (ViewGroup) rootView.findViewById(R.id.container_app_bar);
        mToolbar.setTitle(getActivity().getString(R.string.new_list));
        mToolbar.setNavigationIcon(R.drawable.back_arrow);
        //set the Toolbar as ActionBar
        ((ActivityMain)getActivity()).setSupportActionBar(mToolbar);
        ((ActivityMain)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
    }

}
