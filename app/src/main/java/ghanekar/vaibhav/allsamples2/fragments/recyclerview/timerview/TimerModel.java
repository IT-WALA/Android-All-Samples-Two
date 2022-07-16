package ghanekar.vaibhav.allsamples2.fragments.recyclerview.timerview;

public class TimerModel {
    private String name;
    private int timer;

    public TimerModel(String name, int timer) {
        this.name = name;
        this.timer = timer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "TimerModel{" +
                "name='" + name + '\'' +
                ", timer=" + timer +
                '}';
    }
}
