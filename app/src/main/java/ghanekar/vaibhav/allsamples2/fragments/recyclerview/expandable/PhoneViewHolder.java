package ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable;

import android.view.View;
import android.widget.TextView;


import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable.ExpandableRecyclerViewLibrary.com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable.ExpandableRecyclerViewLibrary.com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import ghanekar.vaibhav.allsamples2.utils.Logger;


public class PhoneViewHolder extends ChildViewHolder {

    private TextView phoneName;

    public PhoneViewHolder(View itemView) {
        super(itemView);

        phoneName = (TextView) itemView.findViewById(R.id.phone_name);
    }

    public void onBind(final Phone phone, ExpandableGroup group) {
        phoneName.setText(phone.getName());
        if (group.getTitle().equals("Android")) {
            phoneName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_delete, 0, 0, 0);
        } else if (group.getTitle().equals("iOS")) {
            phoneName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_delete, 0, 0, 0);
        } else {
            phoneName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_delete, 0, 0, 0);
        }

        phoneName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.LogVerbose("Clicked" + phoneName.getText());
            }
        });
    }
}