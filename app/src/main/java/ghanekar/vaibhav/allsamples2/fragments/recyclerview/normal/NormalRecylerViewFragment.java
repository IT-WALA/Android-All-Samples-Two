package ghanekar.vaibhav.allsamples2.fragments.recyclerview.normal;


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
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.swipe.canvasview.RecyclerTouchListener;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class NormalRecylerViewFragment extends BaseFragment {


    @InjectView(R.id.btnRecyclerView)
    RecyclerView recyclerView;

    public NormalRecylerViewFragment() {
        // Required empty public constructor
    }

    public static final String TAG = NormalRecylerViewFragment.class.getSimpleName() + "_";
    private Activity activity;
    public static String[] names = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    ArrayList<ModelRecyclerView> arrayList = null;
    private RvAdapterNormal rvAdapterNormal;

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
        setToolBar(activity, Constants.TITLE_RECYCLERVIEW_NORMAL, true, this);
        arrayList = new ArrayList<>();
        prepareRecyclerView();
    }

    private void prepareRecyclerView() {
        for (int i = 0; i < names.length; i++) {
            arrayList.add(new ModelRecyclerView(names[i]));
        }
        rvAdapterNormal = new RvAdapterNormal(activity, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(activity, LinearLayoutManager.VERTICAL));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(rvAdapterNormal);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
