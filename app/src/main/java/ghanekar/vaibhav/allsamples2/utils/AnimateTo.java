package ghanekar.vaibhav.allsamples2.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;

import ghanekar.vaibhav.allsamples2.R;


public class AnimateTo {

    public static final String ANIMATE_ZOOM = "ANIMATE_ZOOM";
    public static final String ANIMATE_FADE = "ANIMATE_FADE";
    public static final String ANIMATE_WINDMILL = "ANIMATE_WINDMILL";
    public static final String ANIMATE_SPIN = "ANIMATE_SPIN";
    public static final String ANIMATE_DIAGONAL = "ANIMATE_DIAGONAL";
    public static final String ANIMATE_SPLIT = "ANIMATE_SPLIT";
    public static final String ANIMATE_SHRINK = "ANIMATE_SHRINK";
    public static final String ANIMATE_CARD = "ANIMATE_CARD";
    public static final String ANIMATE_INOUT = "ANIMATE_INOUT";
    public static final String ANIMATE_SWIPELEFT = "ANIMATE_SWIPELEFT";
    public static final String ANIMATE_SWIPERIGHT = "ANIMATE_SWIPERIGHT";
    public static final String ANIMATE_SLIDERIGHT = "ANIMATE_SLIDERIGHT";
    public static final String ANIMATE_SLIDELEFT = "ANIMATE_SLIDELEFT";
    public static final String ANIMATE_SLIDEUP = "ANIMATE_SLIDEUP";
    public static final String ANIMATE_SLIDEDOWN = "ANIMATE_SLIDEDOWN";

    public static void onActivityTransition(String animationType, Activity activity) {
        switch (animationType) {
            case ANIMATE_ZOOM:
                activityAnimateZoom(activity);
                break;
            case ANIMATE_FADE:
                activityAnimateFade(activity);
                break;
            case ANIMATE_WINDMILL:
                activityAnimateWindmill(activity);
                break;
            case ANIMATE_SPIN:
                activityAnimateSpin(activity);
                break;
            case ANIMATE_DIAGONAL:
                activityAnimateDiagonal(activity);
                break;
            case ANIMATE_SPLIT:
                activityAnimateSplit(activity);
                break;
            case ANIMATE_SHRINK:
                activityAnimateShrink(activity);
                break;
            case ANIMATE_CARD:
                activityAnimateCard(activity);
                break;
            case ANIMATE_INOUT:
                activityAnimateInAndOut(activity);
                break;
            case ANIMATE_SWIPELEFT:
                activityAnimateSwipeLeft(activity);
                break;
            case ANIMATE_SWIPERIGHT:
                activityAnimateSwipeRight(activity);
                break;
            case ANIMATE_SLIDERIGHT:
                activityAnimateSlideRight(activity);
                break;
            case ANIMATE_SLIDELEFT:
                activityAnimateSlideLeft(activity);
                break;
            case ANIMATE_SLIDEUP:
                activityAnimateSlideUp(activity);
                break;
            case ANIMATE_SLIDEDOWN:
                activityAnimateSlideDown(activity);
                break;
            default:
                activityAnimateSlideLeft(activity);
        }
    }

    public static void onFramentTransition(String animationType, FragmentTransaction transaction) {
        switch (animationType) {
            case ANIMATE_ZOOM:
                fragmentAnimateZoom(transaction);
                break;
            case ANIMATE_FADE:
                fragmentAnimateFade(transaction);
                break;
            case ANIMATE_WINDMILL:
                fragmentAnimateWindmill(transaction);
                break;
            case ANIMATE_SPIN:
                fragmentAnimateSpin(transaction);
                break;
            case ANIMATE_DIAGONAL:
                fragmentAnimateDiagonal(transaction);
                break;
            case ANIMATE_SPLIT:
                fragmentAnimateSplit(transaction);
                break;
            case ANIMATE_SHRINK:
                fragmentAnimateShrink(transaction);
                break;
            case ANIMATE_CARD:
                fragmentAnimateCard(transaction);
                break;
            case ANIMATE_INOUT:
                fragmentAnimateInAndOut(transaction);
                break;
            case ANIMATE_SWIPELEFT:
                fragmentAnimateSwipeLeft(transaction);
                break;
            case ANIMATE_SWIPERIGHT:
                fragmentAnimateSwipeRight(transaction);
                break;
            case ANIMATE_SLIDERIGHT:
                fragmentAnimateSlideRight(transaction);
                break;
            case ANIMATE_SLIDELEFT:
                fragmentAnimateSlideLeft(transaction);
                break;
            case ANIMATE_SLIDEUP:
                fragmentAnimateSlideUp(transaction);
                break;
            case ANIMATE_SLIDEDOWN:
                fragmentAnimateSlideDown(transaction);
                break;
            default:
                fragmentAnimateSlideLeft(transaction);
        }
    }

    /*Activity*/
    private static void activityAnimateZoom(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_zoom_enter, R.anim.animate_zoom_exit);
    }

    private static void activityAnimateFade(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_fade_enter, R.anim.animate_fade_exit);
    }

    private static void activityAnimateWindmill(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_windmill_exit, R.anim.animate_windmill_exit);
    }

    private static void activityAnimateSpin(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_spin_enter, R.anim.animate_split_enter);
    }

    private static void activityAnimateDiagonal(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_diagonal_right_enter, R.anim.animate_diagonal_right_exit);
    }

    private static void activityAnimateSplit(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_split_enter, R.anim.animate_split_exit);
    }

    private static void activityAnimateShrink(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_shrink_enter, R.anim.animate_shrink_exit);
    }

    private static void activityAnimateCard(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_card_enter, R.anim.animate_card_exit);
    }

    private static void activityAnimateInAndOut(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_in_out_enter, R.anim.animate_in_out_exit);
    }

    private static void activityAnimateSwipeLeft(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_swipe_left_enter, R.anim.animate_swipe_left_exit);
    }

    private static void activityAnimateSwipeRight(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_swipe_right_enter, R.anim.animate_swipe_right_exit);
    }

    private static void activityAnimateSlideLeft(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_slide_left_enter, R.anim.animate_slide_left_exit);
    }

    private static void activityAnimateSlideRight(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_slide_in_left, R.anim.animate_slide_out_right);
    }

    private static void activityAnimateSlideDown(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_slide_down_enter, R.anim.animate_slide_down_exit);
    }

    private static void activityAnimateSlideUp(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.animate_slide_up_enter, R.anim.animate_slide_up_exit);
    }

    /*ApiCallBaseFragment*/
    private static void fragmentAnimateZoom(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_zoom_enter, R.anim.animate_zoom_exit);
    }

    private static void fragmentAnimateFade(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_fade_enter, R.anim.animate_fade_exit);
    }

    private static void fragmentAnimateWindmill(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_windmill_exit, R.anim.animate_windmill_exit);
    }

    private static void fragmentAnimateSpin(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_spin_enter, R.anim.animate_spin_enter);
    }

    private static void fragmentAnimateDiagonal(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_diagonal_right_enter, R.anim.animate_diagonal_right_exit);
    }

    private static void fragmentAnimateSplit(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_split_enter, R.anim.animate_split_exit);
    }

    private static void fragmentAnimateShrink(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_shrink_enter, R.anim.animate_shrink_exit);
    }

    private static void fragmentAnimateCard(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_card_enter, R.anim.animate_card_exit);
    }

    private static void fragmentAnimateInAndOut(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_in_out_enter, R.anim.animate_in_out_exit);
    }

    private static void fragmentAnimateSwipeLeft(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_swipe_left_enter, R.anim.animate_swipe_left_exit);
    }

    private static void fragmentAnimateSwipeRight(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_swipe_right_enter, R.anim.animate_swipe_right_exit);
    }

    private static void fragmentAnimateSlideLeft(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_slide_left_enter, R.anim.animate_slide_left_exit);
    }

    private static void fragmentAnimateSlideRight(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_slide_in_left, R.anim.animate_slide_out_right);
    }

    private static void fragmentAnimateSlideDown(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_slide_down_enter, R.anim.animate_slide_down_exit);
    }

    private static void fragmentAnimateSlideUp(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.animate_slide_up_enter, R.anim.animate_slide_up_exit);
    }
}