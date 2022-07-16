package ghanekar.vaibhav.allsamples2.fragments.recyclerview.pulltorefresh;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.ModelRecyclerView;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.swipe.canvasview.RecyclerTouchListener;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

/**
 * A simple {@link Fragment} subclass.
 */
public class PulltoRefreshRecylerViewFragment extends BaseFragment {


    @InjectView(R.id.btnRecyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.pullToRefreshLayout)
    SwipeRefreshLayout pullToRefreshLayout;

    public PulltoRefreshRecylerViewFragment() {
        // Required empty public constructor
    }

    public static final String TAG = PulltoRefreshRecylerViewFragment.class.getSimpleName() + "_";
    private Activity activity;
    public static String[] names = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", ""};
    ArrayList<ModelRecyclerView> arrayList = null;
    private RvAdapterPulltoRefresh rvAdapterPulltoRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_normal_recyler_view, container, false);
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
        setToolBar(activity, Constants.TITLE_RECYCLERVIEW_PULL_TO_REFRESH, true, this);
        arrayList = new ArrayList<>();
        setPullToRefresh();
        prepareRecyclerView();
    }

    private void setPullToRefresh() {
        pullToRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                pullToRefreshLayout.setRefreshing(false);
                prepareRecyclerView();
                Toaster.showDefaultToast(activity, "Refreshing the list", Gravity.BOTTOM, Toast.LENGTH_LONG);
            }
        });
    }

    private void prepareRecyclerView() {
        for (int i = 0; i < names.length; i++) {
            arrayList.add(new ModelRecyclerView(names[i]));
        }
        rvAdapterPulltoRefresh = new RvAdapterPulltoRefresh(activity, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
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
        recyclerView.setAdapter(rvAdapterPulltoRefresh);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
