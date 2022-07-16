package ghanekar.vaibhav.allsamples2.fragments.recyclerview.swipe.canvasview;


import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.ModelRecyclerView;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.normal.NormalRecylerViewFragment;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeRecyclerViewCanvasFragment extends BaseFragment {


    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.fragContainer)
    LinearLayout fragContainer;

    public SwipeRecyclerViewCanvasFragment() {
        // Required empty public constructor
    }

    public static final String TAG = NormalRecylerViewFragment.class.getSimpleName() + "_";
    private Activity activity;
    public static String[] names = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    ArrayList<ModelRecyclerView> arrayList = null;
    private RvAdapterSwipeCanvas rvAdapterSwipeCanvas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_swipe_recycler_view, container, false);
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
        setToolBar(activity, Constants.TITLE_RECYCLERVIEW_SWIPE_TO_DELETE, true, this);
        arrayList = new ArrayList<>();
        prepareRecyclerView();
        enableSwipeToDeleteAndUndo();
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallbackCanvas swipeToDeleteCallbackCanvas = new SwipeToDeleteCallbackCanvas(activity) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final ModelRecyclerView item = rvAdapterSwipeCanvas.getData().get(position);

                rvAdapterSwipeCanvas.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(fragContainer, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (nullCheck(recyclerView) && nullCheck(rvAdapterSwipeCanvas)) {
                            rvAdapterSwipeCanvas.restoreItem(item, position);
                            recyclerView.scrollToPosition(position);
                        }
                    }
                });

                snackbar.setActionTextColor(Color.WHITE);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallbackCanvas);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    private void prepareRecyclerView() {
        for (int i = 0; i < names.length; i++) {
            arrayList.add(new ModelRecyclerView(names[i]));
        }
        rvAdapterSwipeCanvas = new RvAdapterSwipeCanvas(activity, arrayList);
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
        recyclerView.setAdapter(rvAdapterSwipeCanvas);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
