package ghanekar.vaibhav.allsamples2.fragments.viewpager.dynamic_viewpager;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ghanekar.vaibhav.allsamples2.R;

public class ViewPagerUtil {

    private ImageView[] ivArrayDotsPager;
    private static ViewPagerUtil instance = null;

    private ViewPagerUtil() {
    }

    public static ViewPagerUtil getInstance() {
        if (instance == null) {
            instance = new ViewPagerUtil();
        }
        return instance;
    }

    void setupIndicator(Activity activity, ViewPager viewPager, LinearLayout pagerDots, int size) {
        ivArrayDotsPager = new ImageView[size];
        for (int i = 0; i < size; i++) {
            //create an indicator dot
            ivArrayDotsPager[i] = new ImageView(activity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            ivArrayDotsPager[i].setLayoutParams(params);
            ivArrayDotsPager[i].setImageResource(R.drawable.viewpager_indicator_unselected);
            ivArrayDotsPager[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setAlpha(1);
                }
            });

            //add that indicator to the layout in the xml
            pagerDots.addView(ivArrayDotsPager[i]);
            pagerDots.bringToFront();
        }

        //set current indicator as selected
        ivArrayDotsPager[0].setImageResource(R.drawable.viewpager_indicator_selected);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ivArrayDotsPager.length; i++) {
                    ivArrayDotsPager[i].setImageResource(R.drawable.viewpager_indicator_unselected);
                }
                ivArrayDotsPager[position].setImageResource(R.drawable.viewpager_indicator_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    void onBackPressed(final ViewPager viewPager, final FragmentManager fragmentManager){
        viewPager.setFocusableInTouchMode(true);
        viewPager.requestFocus();
        viewPager.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                    if(viewPager.getCurrentItem() == 0){
                        fragmentManager.popBackStack();
                    }else {
                        viewPager.setCurrentItem(0, true);
                        viewPager.setFocusableInTouchMode(false);
                        viewPager.clearFocus();
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
