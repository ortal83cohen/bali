package apps.cohen.bali.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import apps.cohen.bali.R;
import apps.cohen.bali.activities.ActivityMain;
import apps.cohen.bali.model.Category;
import apps.cohen.bali.model.Item;


public class FragmentEditItem extends Fragment {

    private Toolbar mToolbar;

    private View mRootView;

    private apps.cohen.bali.model.List mList;

    private EditText mItemName;

    private int mItemPosition;

    private int mListPosition;

    public static FragmentEditItem newInstance(apps.cohen.bali.model.List list, int listPosition,int itemPosition) {

        FragmentEditItem fragment = new FragmentEditItem();
        fragment.setList(list, listPosition, itemPosition);
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
        mRootView = inflater.inflate(R.layout.fragment_edit_item, container, false);
        setupToolbar(mRootView);
//        mRootView.setOnTouchItemener(new View.OnTouchItemener() {
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        mItemName = (EditText) mRootView.findViewById(R.id.item_name);

        if (mItemPosition != -1) {
            setupOldItem();
        }
        return mRootView;
    }

    private void setupOldItem() {
        mItemName.setText(mList.getItems().get(mItemPosition).getName());

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
        if (mItemPosition == -1) {
            mToolbar.setTitle(getActivity().getString(R.string.new_item));
        } else {
            mToolbar.setTitle(mList.getItems().get(mItemPosition).getName());
        }
        mToolbar.setNavigationIcon(R.drawable.back_arrow);
        mToolbar.setElevation(12);
        //set the Toolbar as ActionBar
        ((ActivityMain) getActivity()).setSupportActionBar(mToolbar);
        ((ActivityMain) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (mItemPosition == -1) {
            inflater.inflate(R.menu.menu_new, menu);
        } else {
            inflater.inflate(R.menu.menu_edit, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        hideKeyboard();
        int id = item.getItemId();
        if (id == R.id.action_next) {

            if (mItemName.getText().toString().equals("")) {
                Toast.makeText(getActivity(), getActivity().getString(R.string.item_must_have_name),
                        Toast.LENGTH_SHORT).show();
            } else {
                if (mItemPosition == -1) {
                    mList.getItems().add(new Item(0, mList.getCategory(),mItemName.getText().toString(), "", ""));
                } else {
                    mList.getItems().get(mItemPosition).setName(mItemName.getText().toString());
                }
                ((ActivityMain) getActivity()).closeFragmentEditItem();
                ((ActivityMain) getActivity()).openFragmentItemsInList(null, mListPosition);
            }
        } else {
            ((ActivityMain) getActivity()).closeFragmentEditItem();
        }

        return super.onOptionsItemSelected(item);
    }

    public void setList(apps.cohen.bali.model.List list, int listPosition ,int itemPosition) {
        mList = list;
        mItemPosition = itemPosition;
        mListPosition = listPosition;
    }
}
