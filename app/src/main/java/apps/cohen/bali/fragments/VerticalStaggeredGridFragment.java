package apps.cohen.bali.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import apps.cohen.bali.NumberPickerDialog;
import apps.cohen.bali.R;
import apps.cohen.bali.InsetDecoration;
import apps.cohen.bali.adapters.StaggeredAdapter;

public class VerticalStaggeredGridFragment extends Fragment implements AdapterView.OnItemClickListener {

    private RecyclerView mList;
    private StaggeredAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);

        mList = (RecyclerView) rootView.findViewById(R.id.section_list);
        mList.setLayoutManager(getLayoutManager());
        mList.addItemDecoration(getItemDecoration());

        mList.getItemAnimator().setAddDuration(1000);
        mList.getItemAnimator().setChangeDuration(1000);
        mList.getItemAnimator().setMoveDuration(1000);
        mList.getItemAnimator().setRemoveDuration(1000);

        mAdapter = getAdapter();
        mAdapter.setItemCount(getDefaultItemCount());
        mAdapter.setOnItemClickListener(this);
        mList.setAdapter(mAdapter);

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
//                dialog.setPickerRange(0, mAdapter.getItemCount());
//                dialog.setOnNumberSelectedListener(new NumberPickerDialog.OnNumberSelectedListener() {
//                    @Override
//                    public void onNumberSelected(int value) {
//                        mAdapter.addItem(value);
//                    }
//                });
//                dialog.show();
//
//                return true;
//            case R.id.action_remove:
//                dialog = new NumberPickerDialog(getActivity());
//                dialog.setTitle("Position to Remove");
//                dialog.setPickerRange(0, mAdapter.getItemCount()-1);
//                dialog.setOnNumberSelectedListener(new NumberPickerDialog.OnNumberSelectedListener() {
//                    @Override
//                    public void onNumberSelected(int value) {
//                        mAdapter.removeItem(value);
//                    }
//                });
//                dialog.show();
//
//                return true;
//            case R.id.action_empty:
//                mAdapter.setItemCount(0);
//                return true;
//            case R.id.action_small:
//                mAdapter.setItemCount(5);
//                return true;
//            case R.id.action_medium:
//                mAdapter.setItemCount(25);
//                return true;
//            case R.id.action_large:
//                mAdapter.setItemCount(196);
//                return true;
//            case R.id.action_scroll_zero:
//                mList.scrollToPosition(0);
//                return true;
//            case R.id.action_smooth_zero:
//                mList.smoothScrollToPosition(0);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.removeItem(position);
        Toast.makeText(getActivity(),
                "Clicked: " + position + ", index " + mList.indexOfChild(view),
                Toast.LENGTH_SHORT).show();
    }

    public static VerticalStaggeredGridFragment newInstance() {
        VerticalStaggeredGridFragment fragment = new VerticalStaggeredGridFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }


    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new InsetDecoration(getActivity());
    }


    protected int getDefaultItemCount() {
        return 100;
    }


    protected StaggeredAdapter getAdapter() {
        return new StaggeredAdapter();
    }
}
