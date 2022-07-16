package ghanekar.vaibhav.allsamples2.fragments.sqlite;


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
import ghanekar.vaibhav.allsamples2.fragments.sqlite.normal.SqliteNormalFragment;
import ghanekar.vaibhav.allsamples2.fragments.sqlite.room.SqliteRoomFragment;
import ghanekar.vaibhav.allsamples2.utils.AnimateTo;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class SqliteBaseFragment extends BaseFragment {


    @InjectView(R.id.btnNormalSqlite)
    Button btnNormalSqlite;
    @InjectView(R.id.btnRoomSqlite)
    Button btnRoomSqlite;

    public SqliteBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = SqliteBaseFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sqlite_base, container, false);
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
        setToolBar(activity, Constants.TITLE_SQLITE, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnNormalSqlite, R.id.btnRoomSqlite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnNormalSqlite:
                gotoFragment(new SqliteNormalFragment(),R.id.container,true,null,TAG,true,AnimateTo.ANIMATE_INOUT);
                break;
            case R.id.btnRoomSqlite:
                gotoFragment(new SqliteRoomFragment(),R.id.container,true,null,TAG,true,AnimateTo.ANIMATE_INOUT);
                break;
        }
    }
}
