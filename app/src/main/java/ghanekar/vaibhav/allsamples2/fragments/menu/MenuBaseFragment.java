package ghanekar.vaibhav.allsamples2.fragments.menu;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuBaseFragment extends BaseFragment {

    @InjectView(R.id.btnPopupMenu)
    Button btnPopupMenu;
    @InjectView(R.id.btnContextualMenu)
    Button btnContextualMenu;
    android.support.v7.widget.Toolbar toolbar;

    public MenuBaseFragment() {
        // Required empty public constructor
    }

    public static final String TAG = MenuBaseFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_base, container, false);
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
        setToolBar(activity, Constants.TITLE_MENU, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnPopupMenu, R.id.btnContextualMenu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPopupMenu:
                showPopupMenu();
                break;
            case R.id.btnContextualMenu:
                showContextualMenu();
                break;
        }
    }

    private void showContextualMenu() {
        toolbar = activity.findViewById(R.id.toolbar);
        activity.registerForContextMenu(toolbar);
        toolbar.startActionMode(new android.view.ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                activity.getMenuInflater().inflate(R.menu.popupmenu, menu);//Inflate the menu over action mode
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popupmenuOne:
                        Toaster.showDefaultToast(activity, "One", Gravity.CENTER, Toast.LENGTH_SHORT);
                        mode.finish();
                        break;
                    case R.id.popupmenuTwo:
                        Toaster.showDefaultToast(activity, "Two", Gravity.CENTER, Toast.LENGTH_SHORT);
                        mode.finish();
                        break;
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {
                mode.finish();
            }
        });
    }

    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(activity, btnPopupMenu);
        popupMenu.inflate(R.menu.popupmenu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popupmenuOne:
                        Toaster.showDefaultToast(activity, "One", Gravity.CENTER, Toast.LENGTH_SHORT);
                        break;
                    case R.id.popupmenuTwo:
                        Toaster.showDefaultToast(activity, "Two", Gravity.CENTER, Toast.LENGTH_SHORT);
                        break;
                }
                return false;
            }
        });
    }

}
