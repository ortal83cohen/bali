package apps.cohen.bali.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import apps.cohen.bali.MyApplication;
import apps.cohen.bali.R;
import apps.cohen.bali.activities.ActivityMain;
import apps.cohen.bali.adapters.CategorySpinnerAdapter;
import apps.cohen.bali.model.Category;
import apps.cohen.bali.model.List;
import apps.cohen.bali.utils.Apis;


/**
 * @author user
 * @date 2015-06-03
 */
public class FragmentEditList extends Fragment {

    private Toolbar mToolbar;

    private ViewGroup mContainerToolbar;

    private Category mSelectedCategory;

    private View mRootView;

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
        mRootView = inflater.inflate(R.layout.fragment_edit_list, container, false);
        setupToolbar(mRootView);
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        final Apis apis = new Apis(getActivity());
        final ArrayList<Category> categories = apis.getCategories();
        CategorySpinnerAdapter spinnerAdapter = new CategorySpinnerAdapter(getActivity());
        spinnerAdapter.addItems(categories);

        final Spinner spinner = (Spinner) mRootView.findViewById(R.id.category_spinner);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedCategory = categories.get(position);
                setBackgroundColor(mRootView, position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return mRootView;
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void setBackgroundColor(View mItemView, int id) {
        switch (id) {
            case 0:
                mItemView.setBackgroundColor(
                        getResources().getColor(R.color.colorCategory0));
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorCategory0));
                break;
            case 1:
                mItemView.setBackgroundColor(
                        getResources().getColor(R.color.colorCategory1));
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorCategory1));
                break;
            case 2:
                mItemView.setBackgroundColor(
                        getResources().getColor(R.color.colorCategory2));
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorCategory2));
                break;
            case 3:
                mItemView.setBackgroundColor(
                        getResources().getColor(R.color.colorCategory3));
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorCategory3));
                break;
            case 4:
                mItemView.setBackgroundColor(
                        getResources().getColor(R.color.colorCategory4));
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorCategory4));
                break;
            case 5:
                mItemView.setBackgroundColor(
                        getResources().getColor(R.color.colorCategory5));
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorCategory5));
                break;
        }
    }

    private void setupToolbar(View rootView) {
        mToolbar = (Toolbar) rootView.findViewById(R.id.app_bar);
        mContainerToolbar = (ViewGroup) rootView.findViewById(R.id.container_app_bar);
        mToolbar.setTitle(getActivity().getString(R.string.new_list));
        mToolbar.setNavigationIcon(R.drawable.back_arrow);
        mToolbar.setElevation(12);
        //set the Toolbar as ActionBar
        ((ActivityMain) getActivity()).setSupportActionBar(mToolbar);
        ((ActivityMain) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        hideKeyboard();
        int id = item.getItemId();
        if (id == R.id.action_next) {
            EditText listName = (EditText) mRootView.findViewById(R.id.list_name);
            if (listName.getText().toString().equals("")) {
                Toast.makeText(getActivity(), getActivity().getString(R.string.list_must_have_name),
                        Toast.LENGTH_SHORT).show();
            } else {
                Apis apis = new Apis(getActivity());
                MyApplication.provide(getActivity()).getMyLists().add(0,
                        new List(0, listName.getText().toString(), mSelectedCategory.getId()));
                ((ActivityMain) getActivity()).closeFragmentEditList();
            }
        } else {
            ((ActivityMain) getActivity()).closeFragmentEditList();
        }

        return super.onOptionsItemSelected(item);
    }

}
