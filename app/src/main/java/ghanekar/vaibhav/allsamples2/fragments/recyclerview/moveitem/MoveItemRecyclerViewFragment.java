package ghanekar.vaibhav.allsamples2.fragments.recyclerview.moveitem;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoveItemRecyclerViewFragment extends BaseFragment {


    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    public MoveItemRecyclerViewFragment() {
        // Required empty public constructor
    }

    public static final String TAG = NormalRecylerViewFragment.class.getSimpleName() + "_";
    private Activity activity;
    public static String[] names = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    ArrayList<ModelRecyclerView> arrayList = null;
    private RvAdapterMoveItem rvAdapterMoveItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_move_item_recycler_view, container, false);
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
        setToolBar(activity, Constants.TITLE_RECYCLERVIEW_DRAG_AND_DROP, true, this);
        arrayList = new ArrayList<>();
        prepareRecyclerView();
    }

    private void prepareRecyclerView() {
        for (int i = 0; i < names.length; i++) {
            arrayList.add(new ModelRecyclerView(names[i]));
        }
        rvAdapterMoveItem = new RvAdapterMoveItem(arrayList);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(rvAdapterMoveItem);

        recyclerView.addItemDecoration(new RVHItemDividerDecoration(activity, LinearLayoutManager.VERTICAL));

        ItemTouchHelper.Callback callback = new RVHItemTouchHelperCallback(rvAdapterMoveItem, true, true, true);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
