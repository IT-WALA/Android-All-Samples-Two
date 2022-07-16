package ghanekar.vaibhav.allsamples2.fragments.dialogs;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends BaseFragment {


    @InjectView(R.id.btnNormalPicker)
    Button btnNormalPicker;
    @InjectView(R.id.btnTodayPicker)
    Button btnRangePicker;
    @InjectView(R.id.tvDate)
    TextView tvDate;
    @InjectView(R.id.btnMaxDatePicker)
    Button btnMaxDatePicker;
    @InjectView(R.id.btnMinDatePicker)
    Button btnMinDatePicker;
    @InjectView(R.id.btnRangeDatePicker)
    Button btnRangeDatePicker;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    public static final String TAG = DatePickerFragment.class.getSimpleName() + "_";
    private Activity activity;
    private int mYear, mMonth, mDay;
    private Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);
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
        setToolBar(activity, Constants.TITLE_DATEPICKER_DIALOG, true, this);
        calendar = Calendar.getInstance();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnRangeDatePicker, R.id.btnMinDatePicker, R.id.btnMaxDatePicker, R.id.btnNormalPicker, R.id.btnTodayPicker})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnNormalPicker:
                showNormalDatePicker();
                break;
            case R.id.btnTodayPicker:
                showTodayDatePicker();
                break;
            case R.id.btnMaxDatePicker:
                showMaxDatePicker();
                break;
            case R.id.btnMinDatePicker:
                showMinDatePicker();
                break;
            case R.id.btnRangeDatePicker:
                showRangeDatePicker();
                break;
        }
    }

    private void showRangeDatePicker() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setupDate(year,(month+1),dayOfMonth);
            }
        }, mYear, mMonth, mDay);

        Calendar calendarMinDate = Calendar.getInstance();
        calendarMinDate.set(Calendar.YEAR, 2018);
        calendarMinDate.set(Calendar.MONTH, 11);
        calendarMinDate.set(Calendar.DAY_OF_MONTH, 1);
        datePickerDialog.getDatePicker().setMinDate(calendarMinDate.getTimeInMillis());

        Calendar calendarMaxDate = Calendar.getInstance();
        calendarMaxDate.set(Calendar.YEAR, 2018);
        calendarMaxDate.set(Calendar.MONTH, 11);
        calendarMaxDate.set(Calendar.DAY_OF_MONTH, 7);
        datePickerDialog.getDatePicker().setMaxDate(calendarMaxDate.getTimeInMillis());


        datePickerDialog.show();
    }

    private void showMinDatePicker() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setupDate(year,(month+1),dayOfMonth);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        datePickerDialog.show();
    }

    private void showMaxDatePicker() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setupDate(year,(month+1),dayOfMonth);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }

    private void showTodayDatePicker() {
        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setupDate(year,(month+1),dayOfMonth);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void showNormalDatePicker() {
        calendar.set(1900, 0, 1);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setupDate(year, (month + 1), dayOfMonth);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void setupDate(int year, int month, int dayOfMonth) {
        StringBuilder date = new StringBuilder();

        if (dayOfMonth < 10) {
            date.append(String.format("%02d", (dayOfMonth)));
            date.append("/");
        }else {
            date.append(dayOfMonth);
            date.append("/");
        }

        if ((month) < 10) {
            date.append(String.format("%02d", (month)));
            date.append("/");
        }else {
            date.append(month);
            date.append("/");
        }

        date.append(year);

        tvDate.setText(date.toString());
    }
}
