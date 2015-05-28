package apps.cohen.bali.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import apps.cohen.bali.InsetDecoration;
import apps.cohen.bali.adapters.SimpleAdapter;
import apps.cohen.bali.adapters.SimpleStaggeredAdapter;

public class VerticalStaggeredGridFragment extends RecyclerFragment {

    public static VerticalStaggeredGridFragment newInstance() {
        VerticalStaggeredGridFragment fragment = new VerticalStaggeredGridFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new InsetDecoration(getActivity());
    }

    @Override
    protected int getDefaultItemCount() {
        return 100;
    }

    @Override
    protected SimpleAdapter getAdapter() {
        return new SimpleStaggeredAdapter();
    }
}
