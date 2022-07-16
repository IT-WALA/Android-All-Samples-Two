package ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable;

import android.util.Log;
import android.view.View;
import android.widget.TextView;


import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable.ExpandableRecyclerViewLibrary.com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable.ExpandableRecyclerViewLibrary.com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;


public class OSViewHolder extends GroupViewHolder {

    private TextView osName;

    public OSViewHolder(View itemView) {
        super(itemView);

        osName = (TextView) itemView.findViewById(R.id.mobile_os);
    }

    @Override
    public void expand() {
        osName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0);
        Log.i("Adapter", "expand");
    }

    @Override
    public void collapse() {
        Log.i("Adapter", "collapse");
        osName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0);
    }

    public void setGroupName(ExpandableGroup group) {
        osName.setText(group.getTitle());
    }
}