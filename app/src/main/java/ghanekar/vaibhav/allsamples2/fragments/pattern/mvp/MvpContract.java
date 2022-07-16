package ghanekar.vaibhav.allsamples2.fragments.pattern.mvp;

public interface MvpContract {

    interface MvpPresenter {
        void onMinusClicked();
        void onPlusClicked();
    }

    interface Frag1View {
       void showValue(int counter);
    }

    interface Frag2View {
        void setFinalResult(int result);
    }

}
