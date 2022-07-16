package ghanekar.vaibhav.allsamples2.fragments.recyclerview;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable.ExpandableRecyclerViewFragment;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.lazyload.LazyloadRecyclerViewFragment;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.moveitem.MoveItemRecyclerViewFragment;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.normal.NormalRecylerViewFragment;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.pulltorefresh.PulltoRefreshRecylerViewFragment;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.swipe.canvasview.SwipeRecyclerViewCanvasFragment;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.swipe.xmlview.SwipeRecyclerViewXmlFragment;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.timerview.TimerRecyclerViewFragment;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.viewtype.ViewTypeRecyclerViewFragment;
import ghanekar.vaibhav.allsamples2.utils.AnimateTo;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewBaseFragment extends BaseFragment {

    @InjectView(R.id.btnNormalRv)
    Button btnNormalRv;
    @InjectView(R.id.btnSwipeRvCanvas)
    Button btnSwipeRv;
    @InjectView(R.id.btnMoveItemRv)
    Button btnMoveItemRv;
    @InjectView(R.id.btnLazyloadRecyclerView)
    Button btnLazyloadRecyclerView;
    @InjectView(R.id.btnSwipeRvXml)
    Button btnSwipeRvXml;
    @InjectView(R.id.btnPulltoRefresh)
    Button btnPulltoRefresh;
    @InjectView(R.id.btnExpandableRecyclerView)
    Button btnExpandableRecyclerView;
    @InjectView(R.id.btnViewTypeRv)
    Button btnViewTypeRv;
    @InjectView(R.id.btnTimerView)
    Button btnTimerView;

    public RecyclerViewBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = RecyclerViewBaseFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view_base, container, false);
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
        setToolBar(activity, Constants.TITLE_RECYCLERVIEW, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnTimerView, R.id.btnViewTypeRv, R.id.btnExpandableRecyclerView, R.id.btnNormalRv, R.id.btnSwipeRvCanvas, R.id.btnSwipeRvXml, R.id.btnMoveItemRv, R.id.btnPulltoRefresh, R.id.btnLazyloadRecyclerView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnNormalRv:
                gotoFragment(new NormalRecylerViewFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnSwipeRvCanvas:
                gotoFragment(new SwipeRecyclerViewCanvasFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnSwipeRvXml:
                gotoFragment(new SwipeRecyclerViewXmlFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnMoveItemRv:
                gotoFragment(new MoveItemRecyclerViewFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnPulltoRefresh:
                gotoFragment(new PulltoRefreshRecylerViewFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnLazyloadRecyclerView:
                gotoFragment(new LazyloadRecyclerViewFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnExpandableRecyclerView:
                gotoFragment(new ExpandableRecyclerViewFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnViewTypeRv:
                gotoFragment(new ViewTypeRecyclerViewFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnTimerView:
                gotoFragment(new TimerRecyclerViewFragment(), R.id.container, true, null, TAG, true, AnimateTo.ANIMATE_INOUT);
                break;
        }
    }
}
