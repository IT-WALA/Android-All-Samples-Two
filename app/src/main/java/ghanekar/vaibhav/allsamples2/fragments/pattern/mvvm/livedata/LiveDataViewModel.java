package ghanekar.vaibhav.allsamples2.fragments.pattern.mvvm.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class LiveDataViewModel extends ViewModel {

    MutableLiveData<CounterModel> liveData = new MutableLiveData<>();
    private int counter = 0;

    public MutableLiveData<CounterModel> getLiveData() {
        return liveData;
    }

    public void setLiveData(MutableLiveData<CounterModel> liveData) {
        this.liveData = liveData;
    }

    public void incrementCounter() {
        CounterModel counterModel = new CounterModel();
        counterModel.setCounter(++counter);
        getLiveData().setValue(counterModel);
    }

    public void decrementCounter() {
        CounterModel counterModel = new CounterModel();
        counterModel.setCounter(--counter);
        getLiveData().setValue(counterModel);
    }
}
