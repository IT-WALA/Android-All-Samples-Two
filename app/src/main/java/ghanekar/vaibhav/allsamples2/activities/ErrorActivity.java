package ghanekar.vaibhav.allsamples2.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseActivity;


public class ErrorActivity extends BaseActivity {

    @InjectView(R.id.btnRestartApp)
    Button btnRestartApp;
    @InjectView(R.id.tvException)
    TextView tvException;

    private final String TAG = ErrorActivity.class.getSimpleName();
    private Activity activity = null;
    private Exception serializableException;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        ButterKnife.inject(this);

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //init

    private void init() {
        activity = ErrorActivity.this;

        if (nullCheck(getIntent()) && nullCheck(getIntent().getExtras())) {

            serializableException = (Exception) getIntent().getExtras().getSerializable("ex");

            Writer writer = new StringWriter();
            serializableException.printStackTrace(new PrintWriter(writer));
            String strException = writer.toString();
            strException = strException.replaceAll("at", "at\n");

            tvException.setText(strException);
        }
    }
    //endpoint

    @OnClick(R.id.btnRestartApp)
    public void onViewClicked() {
        try {
            finish();
            //restartApplication();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //restartApplication


    @Override
    protected void onDestroy() {
        try {
            activity = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.gc();
        }
        super.onDestroy();
    }
    //endpoint
}
