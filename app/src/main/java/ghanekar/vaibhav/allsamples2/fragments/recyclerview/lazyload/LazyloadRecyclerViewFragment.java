package ghanekar.vaibhav.allsamples2.fragments.recyclerview.lazyload;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.ModelRecyclerView;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.swipe.canvasview.RecyclerTouchListener;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.normal.NormalRecylerViewFragment;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.swipe.canvasview.RvAdapterSwipeCanvas;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class LazyloadRecyclerViewFragment extends BaseFragment {


    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    public LazyloadRecyclerViewFragment() {
        // Required empty public constructor
    }

    public static final String TAG = NormalRecylerViewFragment.class.getSimpleName() + "_";
    private Activity activity;
    public static String[] names = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public static String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    ArrayList<ModelRecyclerView> arrayList = null;
    private RvAdapterLazyLoad rvAdapterLazyLoad;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 1;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lazyload_recycler_view, container, false);
        ButterKnife.inject(this, view);
        try {
            init();
        } catch (Exception e) {
            handleException(e);
        }
        return view;
    }

    private void init() {
        activity = getActivity();
        setToolBar(activity, Constants.TITLE_RECYCLERVIEW_LAZYLOAD, true, this);
        arrayList = new ArrayList<>();
        prepareRecyclerView();
    }

    private void prepareRecyclerView() {
        for (int i = 0; i < names.length; i++) {
            arrayList.add(new ModelRecyclerView(names[i]));
        }
        rvAdapterLazyLoad = new RvAdapterLazyLoad(activity, arrayList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(activity, LinearLayoutManager.VERTICAL));

        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(rvAdapterLazyLoad);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached

                    Logger.LogVerbose(TAG + "End is here.");

                    // Do something
                    loadMoreData();

                    loading = true;
                }
            }
        });
    }

    private void loadMoreData() {
        for (int i = 0; i < numbers.length; i++) {
            arrayList.add(new ModelRecyclerView(numbers[i]));
        }
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                rvAdapterLazyLoad.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
