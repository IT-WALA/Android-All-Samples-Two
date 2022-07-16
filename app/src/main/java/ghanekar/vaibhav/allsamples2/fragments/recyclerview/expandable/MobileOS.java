package ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable;

import android.annotation.SuppressLint;
import java.util.List;

import ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable.ExpandableRecyclerViewLibrary.com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

@SuppressLint("ParcelCreator")
public class MobileOS extends ExpandableGroup<Phone> {

    public MobileOS(String title, List<Phone> items) {
        super(title, items);
    }
}