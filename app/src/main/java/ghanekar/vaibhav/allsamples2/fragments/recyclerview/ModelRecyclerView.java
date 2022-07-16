package ghanekar.vaibhav.allsamples2.fragments.recyclerview;

/**
 * Created by Vaibhav Ghanekar on 26-04-2018.
 */

public class ModelRecyclerView {
    private String name;

    public ModelRecyclerView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ModelRecyclerView{" +
                "name='" + name + '\'' +
                '}';
    }
}
