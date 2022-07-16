package ghanekar.vaibhav.allsamples2.fragments.apicall.retrofit;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class RetrofitFragment extends BaseFragment implements RetrofitComm.RetrofitCallBack {

    @InjectView(R.id.btnGetVolley)
    Button btnGetVolley;
    @InjectView(R.id.btnPostVolley)
    Button btnPostVolley;
    @InjectView(R.id.btnUploadImage)
    Button btnUploadImage;

    public RetrofitFragment() {
        // Required empty public constructor
    }

    public static final String TAG = RetrofitFragment.class.getSimpleName() + "_";
    private Activity activity;
    private static final String GET_REQUEST = "GET_REQUEST";
    private static final String POST_REQUEST = "POST_REQUEST";
    private static final int PICK_IMAGE = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retrofit, container, false);
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
        setToolBar(activity, Constants.TITLE_RETROFIT, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnGetVolley, R.id.btnPostVolley, R.id.btnUploadImage})
    public void onViewClicked(View view) {
        try{
            switch (view.getId()) {
                case R.id.btnGetVolley:
                    RetrofitComm.getInstance().getJsonObjectRequest(activity, this, GET_REQUEST);
                    break;
                case R.id.btnPostVolley:
                    PostApiRequestModel requestModel = new PostApiRequestModel("Vaibhav", "25000", "26");
                    RetrofitComm.getInstance().postJsonObjectRequest(activity, this, requestModel, POST_REQUEST);
                    break;
                case R.id.btnUploadImage:
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, PICK_IMAGE);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
                Uri selectedImage = data.getData();
                uploadImage(selectedImage, "My Image");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void uploadImage(Uri fileUri, String desc) {
//creating a file
        File file = new File(getRealPathFromURI(fileUri));

        //creating request body for file
        RequestBody requestFile = RequestBody.create(MediaType.parse(activity.getContentResolver().getType(fileUri)), file);
        RequestBody descBody = RequestBody.create(MediaType.parse("text/plain"), desc);

        //The gson builder
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        //creating retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //creating our api
        ApiInterface api = retrofit.create(ApiInterface.class);

        //creating a call and calling the upload image method
        Call<Object> call = api.uploadImage(requestFile, descBody);

        //finally performing the call
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(activity, "File Uploaded Successfully...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity, "Some error occurred...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(activity, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public void retrofitCallBackResult(boolean isSuccessful, Object response, String s) {

    }
}
