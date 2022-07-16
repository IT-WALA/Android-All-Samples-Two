package ghanekar.vaibhav.allsamples2.fragments.recyclerview.viewtype;

public class ViewtypeModel {

    public static final int TEXT_TYPE=0;
    public static final int IMAGE_TYPE=1;

    public int viewType;
    public int image;
    public String text;

    public ViewtypeModel(int viewType, int image, String text) {
        this.viewType = viewType;
        this.image = image;
        this.text = text;
    }
}
