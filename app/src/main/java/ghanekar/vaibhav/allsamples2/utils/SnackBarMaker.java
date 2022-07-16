package ghanekar.vaibhav.allsamples2.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ghanekar.vaibhav.allsamples2.R;

public class SnackBarMaker {

    private static Snackbar snackbar;

    public static Snackbar showDefaultSnackBar(View view, String msg, int duration) {
        snackbar = Snackbar.make(view, msg, duration);
        snackbar.show();
        return snackbar;
    }

    public static void showCustomSnackBar(Activity activity, View view, String msg, int duration) {
        final Snackbar snackbar = Snackbar.make(view, "", duration);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View snackView = layoutInflater.inflate(R.layout.custom_snackbar, null);
        ImageView imageView = (ImageView) snackView.findViewById(R.id.imgView);
        imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_delete));
        TextView textViewTop = (TextView) snackView.findViewById(R.id.textView);
        textViewTop.setText(msg);
        textViewTop.setTextColor(Color.WHITE);

//If the view is not covering the whole snackbar layout, add this line
        layout.setPadding(0, 0, 0, 0);

// Add the view to the Snackbar's layout
        layout.addView(snackView, 0);
// Show the Snackbard
        snackbar.show();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
    }

    public static void cancelSnackBar() {
        snackbar.dismiss();
    }
}
