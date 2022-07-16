package ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable.ExpandableRecyclerViewLibrary.com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.normal.NormalRecylerViewFragment;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Logger;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpandableRecyclerViewFragment extends BaseFragment {

    @InjectView(R.id.expanableRecyclerView)
    RecyclerView recyclerView;

    public ExpandableRecyclerViewFragment() {
        // Required empty public constructor
    }

    public static final String TAG = NormalRecylerViewFragment.class.getSimpleName() + "_";
    private Activity activity;
    private ArrayList<MobileOS> mobileOSes;
    private RecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expandable_recycler_view, container, false);
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
        setToolBar(activity, Constants.TITLE_RECYCLERVIEW_EXPANDABLE, true, this);
        prepareRecyclerView();
    }

    private void prepareRecyclerView() {
        mobileOSes = new ArrayList<>();

        setData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(activity, mobileOSes);
        recyclerView.setAdapter(adapter);

        adapter.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(int flatPos) {
                Logger.LogVerbose(TAG + flatPos);
                return false;
            }
        });
    }

    private void setData() {
        ArrayList<Phone> iphones = new ArrayList<>();
        iphones.add(new Phone("iPhone 4"));
        iphones.add(new Phone("iPhone 4S"));
        iphones.add(new Phone("iPhone 5"));
        iphones.add(new Phone("iPhone 5S"));
        iphones.add(new Phone("iPhone 6"));
        iphones.add(new Phone("iPhone 6Plus"));
        iphones.add(new Phone("iPhone 6S"));
        iphones.add(new Phone("iPhone 6S Plus"));

        ArrayList<Phone> nexus = new ArrayList<>();
        nexus.add(new Phone("Nexus One"));
        nexus.add(new Phone("Nexus S"));
        nexus.add(new Phone("Nexus 4"));
        nexus.add(new Phone("Nexus 5"));
        nexus.add(new Phone("Nexus 6"));
        nexus.add(new Phone("Nexus 5X"));
        nexus.add(new Phone("Nexus 6P"));
        nexus.add(new Phone("Nexus 7"));

        ArrayList<Phone> windowPhones = new ArrayList<>();
        windowPhones.add(new Phone("Nokia Lumia 800"));
        windowPhones.add(new Phone("Nokia Lumia 710"));
        windowPhones.add(new Phone("Nokia Lumia 900"));
        windowPhones.add(new Phone("Nokia Lumia 610"));
        windowPhones.add(new Phone("Nokia Lumia 510"));
        windowPhones.add(new Phone("Nokia Lumia 820"));
        windowPhones.add(new Phone("Nokia Lumia 920"));

        mobileOSes.add(new MobileOS("iOS", iphones));
        mobileOSes.add(new MobileOS("Android", nexus));
        mobileOSes.add(new MobileOS("Window Phone", windowPhones));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
