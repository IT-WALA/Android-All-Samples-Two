package ghanekar.vaibhav.allsamples2.fragments.pattern.mvp;

import android.support.v4.app.Fragment;

import ghanekar.vaibhav.allsamples2.fragments.pattern.mvp.MvpContract.*;

public class MvpPresenterImpl implements MvpPresenter {

    private Fragment mvpBaseFragment;
    private int counter = 0;
    private static Frag1View frag1View;
    private static Frag2View frag2View;

    public MvpPresenterImpl(MvpFrag1 fragment) {
        frag1View = fragment;
    }

    public MvpPresenterImpl(MvpFrag2 fragment) {
        frag2View = fragment;
    }

    @Override
    public void onMinusClicked() {
        --counter;
        frag1View.showValue(counter);
        frag2View.setFinalResult(counter);
    }

    @Override
    public void onPlusClicked() {
        ++counter;
        frag1View.showValue(counter);
        frag2View.setFinalResult(counter);
    }
}
