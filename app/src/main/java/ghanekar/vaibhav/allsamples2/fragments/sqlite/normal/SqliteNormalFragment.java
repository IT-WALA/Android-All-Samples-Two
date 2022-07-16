package ghanekar.vaibhav.allsamples2.fragments.sqlite.normal;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sqlcipher.database.SQLiteDatabase;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

/**
 * A simple {@link Fragment} subclass.
 */
public class SqliteNormalFragment extends BaseFragment {

    @InjectView(R.id.btnAddData)
    Button btnAddData;
    @InjectView(R.id.btnRetrieveData)
    Button btnRetrieveData;

    public static final String TAG = SqliteNormalFragment.class.getSimpleName() + "_";
    private Activity activity;
    private DatabaseHelperEncrypted databaseHelper;

    public SqliteNormalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sqlite_normal, container, false);
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
        setToolBar(activity, Constants.TITLE_SQLITE_NORMAL, true, this);
        databaseHelper = new DatabaseHelperEncrypted(activity);

        initializeSqlCipher();
    }

    private void initializeSqlCipher() {
        SQLiteDatabase.loadLibs(activity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnAddData, R.id.btnRetrieveData})
    public void onViewClicked(View view) {
        try {
            switch (view.getId()) {
                case R.id.btnAddData:
                    String name = "Vaibhav";
                    SqlitePojo pojo = new SqlitePojo();
                    pojo.setName(name);

                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add("Home no.");
                    arrayList.add("Street no.");
                    arrayList.add("City");
                    arrayList.add("State");
                    arrayList.add("Pin code");

                    String address = new Gson().toJson(arrayList);
                    pojo.setAddress(address);

                    databaseHelper.insertData(pojo);
                    Toaster.showDefaultToast(activity, "Data inserted.", Gravity.BOTTOM, Toast.LENGTH_SHORT);
                    break;
                case R.id.btnRetrieveData:
                    SqlitePojo data = databaseHelper.getDataByID(1);
                    Logger.LogVerbose("Address: " + data.getAddress());
                    Type type = new TypeToken<ArrayList<String>>() {
                    }.getType();

                    ArrayList<String> addressList = new Gson().fromJson(data.getAddress(), type);
                    if (nullCheck(data))
                        Toaster.showDefaultToast(activity, data.getName() + "\n" + addressList.toString(), Gravity.CENTER, Toast.LENGTH_LONG);
                    break;
            }
        } catch (Exception e) {
            handleException(e);
        }
    }
}
