package ghanekar.vaibhav.allsamples2.fragments.recyclerview.swipe.canvasview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.recyclerview.ModelRecyclerView;

/**
 * Created by Vaibhav Ghanekar on 26-04-2018.
 */

public class RvAdapterSwipeCanvas extends RecyclerView.Adapter<RvAdapterSwipeCanvas.MyViewHolder> {

    private Context context;
    private ArrayList<ModelRecyclerView> arrayList;

    public RvAdapterSwipeCanvas(Activity context, ArrayList<ModelRecyclerView> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ModelRecyclerView modelRecyclerView = arrayList.get(position);
        holder.txt1.setText(modelRecyclerView.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void removeItem(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(ModelRecyclerView item, int position) {
        arrayList.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<ModelRecyclerView> getData() {
        return arrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.txt1)
        TextView txt1;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
