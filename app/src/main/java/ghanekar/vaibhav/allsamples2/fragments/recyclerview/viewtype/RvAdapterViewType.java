package ghanekar.vaibhav.allsamples2.fragments.recyclerview.viewtype;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ghanekar.vaibhav.allsamples2.R;

/**
 * Created by Vaibhav Ghanekar on 26-04-2018.
 */

public class RvAdapterViewType extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<ViewtypeModel> arrayList;

    public RvAdapterViewType(Activity context, ArrayList<ViewtypeModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case ViewtypeModel.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_viewtype_text, parent, false);
                return new TextTypeViewHolder(view);
            case ViewtypeModel.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_viewtype_image, parent, false);
                return new ImageTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ViewtypeModel model = arrayList.get(position);
        if (model != null) {
            switch (model.viewType) {
                case ViewtypeModel.TEXT_TYPE:
                    ((TextTypeViewHolder) viewHolder).txt.setText(model.text);
                    break;
                case ViewtypeModel.IMAGE_TYPE:
                    ((ImageTypeViewHolder) viewHolder).image.setImageResource(model.image);
                    break;
            }
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
        switch (arrayList.get(position).viewType) {
            case 0:
                return ViewtypeModel.TEXT_TYPE;
            case 1:
                return ViewtypeModel.IMAGE_TYPE;
            default:
                return -1;
        }
    }

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.txt)
        TextView txt;

        public TextTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.image)
        ImageView image;

        public ImageTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
