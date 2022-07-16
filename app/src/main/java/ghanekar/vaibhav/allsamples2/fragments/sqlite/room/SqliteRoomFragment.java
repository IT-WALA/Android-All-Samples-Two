package ghanekar.vaibhav.allsamples2.fragments.sqlite.room;


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

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

/**
 * A simple {@link Fragment} subclass.
 */
public class SqliteRoomFragment extends BaseFragment {

    @InjectView(R.id.btnAddData)
    Button btnAddData;
    @InjectView(R.id.btnRetrieveData)
    Button btnRetrieveData;

    public SqliteRoomFragment() {
        // Required empty public constructor
    }

    public static final String TAG = SqliteRoomFragment.class.getSimpleName() + "_";
    private Activity activity;
    private RoomAppDataBase roomAppDataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sqlite_room, container, false);
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
        setToolBar(activity, Constants.TITLE_SQLITE_ROOM, true, this);
        roomAppDataBase = RoomAppDataBase.getInstance(activity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.btnAddData, R.id.btnRetrieveData})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddData:
                String name = "Vaibhav";
                int age = 25;
                SqlitePojo pojo = new SqlitePojo();
                pojo.setName(name);
                pojo.setAge(age);

                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("Home no.");
                arrayList.add("Street no.");
                arrayList.add("City");
                arrayList.add("State");
                arrayList.add("Pin code");

                String address = new Gson().toJson(arrayList);
                pojo.setAddress(address);
                roomAppDataBase.roomDao().addData(pojo);
                Toaster.showDefaultToast(activity, "Data inserted.", Gravity.BOTTOM, Toast.LENGTH_SHORT);
                break;
            case R.id.btnRetrieveData:
                SqlitePojo pojo1 = roomAppDataBase.roomDao().getDataById(1);
                Toaster.showDefaultToast(activity, pojo1.getName() + "\n" + pojo1.toString() , Gravity.CENTER, Toast.LENGTH_LONG);
                break;
        }
    }
}
