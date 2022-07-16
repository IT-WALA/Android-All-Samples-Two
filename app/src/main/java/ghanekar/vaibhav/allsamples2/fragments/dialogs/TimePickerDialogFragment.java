package ghanekar.vaibhav.allsamples2.fragments.dialogs;


import android.app.Activity;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerDialogFragment extends BaseFragment {


    @InjectView(R.id.btnNormalTimePicker)
    Button btnNormalTimePicker;
    @InjectView(R.id.btnMaxTimePicker)
    Button btnMaxTimePicker;
    @InjectView(R.id.tvTime)
    TextView tvTime;
    @InjectView(R.id.btnMinTimePicker)
    Button btnMinTimePicker;
    @InjectView(R.id.btnRangeTimePicker)
    Button btnRangeTimePicker;

    public TimePickerDialogFragment() {
        // Required empty public constructor
    }

    public static final String TAG = TimePickerDialogFragment.class.getSimpleName() + "_";
    private Activity activity;
    private Calendar calendar;
    private int mHour, mMinute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_picker_dialog, container, false);
        ButterKnife.inject(this, view);
        try {
            init();
        } catch (Exception e) {
            handleException(e);
        }
        return view;
    }

    private void init() {
        activity = getActivity();
        setToolBar(activity, Constants.TITLE_TIMEPICKER_DIALOG, true, this);
        calendar = Calendar.getInstance();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnRangeTimePicker, R.id.btnMinTimePicker, R.id.btnNormalTimePicker, R.id.btnMaxTimePicker})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnNormalTimePicker:
                showNormalTimePickerDialog();
                break;
            case R.id.btnMaxTimePicker:
                showMaxTimePickerDialog();
                break;
            case R.id.btnMinTimePicker:
                showMinTimePickerDialog();
                break;
            case R.id.btnRangeTimePicker:
                showRangeTimePickerDialog();
                break;
        }
    }

    private void showRangeTimePickerDialog() {
        calendar.set(Calendar.HOUR, 5);
        calendar.set(Calendar.MINUTE, 0);

        RangeTimePickerDialog rangeTimePickerDialog = new RangeTimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setupTime(hourOfDay, minute);
            }
        }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), false);
        rangeTimePickerDialog.setMin(5, 0);
        rangeTimePickerDialog.setMax(7, 0);
        rangeTimePickerDialog.show();
    }

    private void showMinTimePickerDialog() {
        calendar.set(Calendar.HOUR, 5);
        calendar.set(Calendar.MINUTE, 0);

        RangeTimePickerDialog rangeTimePickerDialog = new RangeTimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setupTime(hourOfDay, minute);
            }
        }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), false);
        rangeTimePickerDialog.setMin(5, 0);
        rangeTimePickerDialog.show();
    }

    private void showMaxTimePickerDialog() {
        mHour = calendar.get(Calendar.HOUR);
        mMinute = calendar.get(Calendar.MINUTE);

        RangeTimePickerDialog rangeTimePickerDialog = new RangeTimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setupTime(hourOfDay, minute);
            }
        }, mHour, mMinute, false);
        rangeTimePickerDialog.setMax(5, 0);
        rangeTimePickerDialog.show();
    }

    private void showNormalTimePickerDialog() {
        mHour = calendar.get(Calendar.HOUR);
        mMinute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setupTime(hourOfDay, minute);
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void setupTime(int hour, int minute) {
        StringBuilder time = new StringBuilder();

        if ((hour) < 10) {
            time.append(String.format("%02d", (hour)));
            time.append(":");
        } else {
            time.append(hour);
            time.append(":");
        }

        if (minute < 10) {
            time.append(String.format("%02d", (minute)));
        } else {
            time.append(minute);
        }

        tvTime.setText(time.toString());
    }
}
