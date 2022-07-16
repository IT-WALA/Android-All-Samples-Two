package ghanekar.vaibhav.allsamples2.utils;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import ghanekar.vaibhav.allsamples2.R;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission_group.CAMERA;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    private String TAG = BaseActivity.class.getSimpleName();

    public BaseFragment() {
        // Required empty public constructor
    }

    protected void gotoActivity(Activity activity1, Class activity2, Bundle bundle, boolean finishPrevActivity, String Tag) {
        try {
            Intent intent = new Intent(activity1, activity2);
            if (null != bundle)
                intent.putExtras(bundle);

            startActivity(intent);

            if (finishPrevActivity)
                getActivity().finish();
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void gotoFragment(Fragment fragment, int container, boolean addToBackStack, Bundle bundle,
                                String Tag, boolean isAnimateTransition, String animationType) {

        try {
            if (null != bundle)
                fragment.setArguments(bundle);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if (isAnimateTransition) {
                AnimateTo.onFramentTransition(animationType, fragmentTransaction);
            }

            fragmentTransaction.replace(container, fragment, Tag);

            if (addToBackStack)
                fragmentTransaction.addToBackStack(Tag);

            fragmentTransaction.commit();
            //fragmentManager.executePendingTransactions();
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void hideKeyBoard(Activity activity) {
        try {
            View v = activity.getCurrentFocus();
            if (nullCheck(v)) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void inflateToolbarMenu(int menuName, int menuId, Fragment fragment) {
        try {
            Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
            Menu menu = null;
            if (menuName == 0) {
                //nothing
            } else {
                menu = toolbar.getMenu();
                menu.clear();
                toolbar.inflateMenu(menuName);

                if (menuId == 0) {
                    //nothing
                } else {
                    MenuItem menuItem = menu.findItem(menuId);
                    menuItem.setVisible(true);
                    menuItem.setOnMenuItemClickListener((MenuItem.OnMenuItemClickListener) fragment);
                }
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void clearToolbarMenu(int menuName) {
        try {
            Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
            toolbar.inflateMenu(menuName);
            Menu menu = toolbar.getMenu();
            menu.clear();
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void setToolBar(Activity activity, String title, boolean isNavIcon, final Fragment currentFragment) {
        try {
            Toolbar toolbar = activity.findViewById(R.id.toolbar);
            toolbar.setTitle(title);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            if (isNavIcon) {
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            } else {
                toolbar.setNavigationIcon(null);
            }

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goUp(currentFragment);
                }
            });
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void goUp(Fragment currentFragment) {
        try {
            getFragmentManager().beginTransaction().remove(currentFragment).commitAllowingStateLoss();
            getFragmentManager().popBackStack();
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected String trimmer(String str) {
        return str.replaceAll("\\s{2,}", " ");
    }

    protected boolean nullCheck(Object obj) {
        try {
            if (null == obj) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            handleException(e);
        }
        return false;
    }

    protected void handleException(Exception e) {
        try {
            Log.e("Error", "/******************************************************************/");
            e.printStackTrace();
            Bundle bundle = new Bundle();
            bundle.putSerializable("ex", e);
            //gotoActivity(getActivity(), ErrorActivity.class, bundle, true, TAG);
        } catch (Exception ex) {
            e.printStackTrace();
        }
    }
}
