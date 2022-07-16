package ghanekar.vaibhav.allsamples2.fragments.recyclerview.timerview;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
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

public class RvAdapterTimerView extends RecyclerView.Adapter<RvAdapterTimerView.MyViewHolder> {

    private Context context;
    private ArrayList<TimerModel> arrayList;

    public RvAdapterTimerView(Activity context, ArrayList<TimerModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_item_with_timer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final TimerModel timerModel = arrayList.get(position);
        holder.txt1.setText(timerModel.getName());

        if (holder.countDownTimer == null) {
            holder.countDownTimer = new CountDownTimer(timerModel.getTimer(), 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    holder.timer.setText("Remaining: " + (millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    holder.timer.setText("Done!");
                }
            }.start();
        }
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.txt1)
        TextView txt1;
        @InjectView(R.id.timer)
        TextView timer;

        CountDownTimer countDownTimer;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
