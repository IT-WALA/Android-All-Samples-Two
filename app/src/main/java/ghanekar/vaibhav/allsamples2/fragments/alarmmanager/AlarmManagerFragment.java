package ghanekar.vaibhav.allsamples2.fragments.alarmmanager;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class AlarmManagerFragment extends BaseFragment {

    @InjectView(R.id.btnAfterSomeTime)
    Button btnAfterSomeTime;
    @InjectView(R.id.btnPeriodic)
    Button btnPeriodic;
    @InjectView(R.id.btnSpecificTime)
    Button btnSpecificTime;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private int mHour, mMinute, mSeconds, mMilliSeconds;

    public AlarmManagerFragment() {
        // Required empty public constructor
    }

    public static final String TAG = AlarmManagerFragment.class.getSimpleName() + "_";
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm_manager, container, false);
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
        setToolBar(activity, Constants.TITLE_ALARM_MANAGER, true, this);
        alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(activity, MyBroadCastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(activity, 100, intent, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnAfterSomeTime, R.id.btnPeriodic, R.id.btnSpecificTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAfterSomeTime:
                triggerAlarmAfterSomeTime();
                break;
            case R.id.btnPeriodic:
                triggerRepeatingAlarm();
                break;
            case R.id.btnSpecificTime:
                openTimePicker();
                break;
        }
    }

    private void openTimePicker() {
        Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        mSeconds = calendar.get(Calendar.SECOND);
        mMilliSeconds = calendar.get(Calendar.MILLISECOND);

        TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                mSeconds = 0;
                mMilliSeconds = 0;

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,mHour);
                calendar.set(Calendar.MINUTE, mMinute);
                calendar.set(Calendar.SECOND,mSeconds);
                calendar.set(Calendar.MILLISECOND,mMilliSeconds);

                triggerSpecificTimeAlarm(calendar);
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void triggerSpecificTimeAlarm(Calendar calendar) {
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void triggerRepeatingAlarm() {
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, 1000, 3000, pendingIntent);
    }

    private void triggerAlarmAfterSomeTime() {
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, pendingIntent);
    }

}
