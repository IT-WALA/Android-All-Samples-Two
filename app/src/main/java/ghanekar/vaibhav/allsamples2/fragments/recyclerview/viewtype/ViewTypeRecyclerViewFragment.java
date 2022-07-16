package ghanekar.vaibhav.allsamples2.fragments.recyclerview.viewtype;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
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
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.normal.NormalRecylerViewFragment;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.normal.RvAdapterNormal;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.swipe.canvasview.RecyclerTouchListener;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewTypeRecyclerViewFragment extends BaseFragment {


    @InjectView(R.id.btnRecyclerView)
    RecyclerView recyclerView;

    public ViewTypeRecyclerViewFragment() {
        // Required empty public constructor
    }

    public static final String TAG = NormalRecylerViewFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_type_recycler_view, container, false);
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
        setToolBar(activity, Constants.TITLE_RECYCLERVIEW_VIEWTYPE, true, this);
        prepareRecyclerView();
    }

    private void prepareRecyclerView() {

        ArrayList<ViewtypeModel> arrayList = new ArrayList<>();
        arrayList.add(new ViewtypeModel(0, 0, "A"));
        arrayList.add(new ViewtypeModel(1, R.drawable.ic_delete, ""));
        arrayList.add(new ViewtypeModel(0, 0, "B"));
        arrayList.add(new ViewtypeModel(1, R.drawable.ic_account_circle, "A"));
        arrayList.add(new ViewtypeModel(0, 0, "C"));
        arrayList.add(new ViewtypeModel(0, 0, "D"));
        arrayList.add(new ViewtypeModel(1, R.drawable.ic_arrow_down, "A"));

        RvAdapterViewType rvAdapterViewType = new RvAdapterViewType(activity,arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(rvAdapterViewType);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
