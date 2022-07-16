package ghanekar.vaibhav.allsamples2.fragments.apicall.volley;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
public class VolleyFragment extends BaseFragment implements VolleyComm.VolleyCallBack {

    @InjectView(R.id.btnGetVolley)
    Button btnGetVolley;
    @InjectView(R.id.btnPostVolley)
    Button btnPostVolley;
    @InjectView(R.id.btnImageLoader)
    Button btnImageLoader;
    @InjectView(R.id.imgNetworkingImage)
    NetworkImageView imgNetworkingImage;
    @InjectView(R.id.btnUploadImage)
    Button btnUploadImage;

    public VolleyFragment() {
        // Required empty public constructor
    }

    public static final String TAG = VolleyFragment.class.getSimpleName() + "_";
    private Activity activity;
    private static final String GET_REUQEST = "GET_REUQEST";
    private static final String POST_REQUEST = "POST_REQUEST";
    private View view;
    private String getUrl = "http://dummy.restapiexample.com/api/v1/employees";
    private String postUrl = "http://dummy.restapiexample.com/api/v1/create";
    private final String imageUrl = "https://www.truiton.com/truiton_sq.jpg";//"http://goo.gl/0rkaBz";
    private ImageLoader imageLoader;
    private static final int PICK_A_PHOTO = 100;
    private Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_volley, container, false);
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
        setToolBar(activity, Constants.TITLE_VOLLEY, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnGetVolley, R.id.btnPostVolley, R.id.btnImageLoader, R.id.btnUploadImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGetVolley:
                VolleyComm.getInstance(activity).getRequest(activity, this, getUrl, GET_REUQEST);
                break;
            case R.id.btnPostVolley:
                PostApiRequestModel requestModel = new PostApiRequestModel("Vaibhav", "25000", "26");
                String json = new Gson().toJson(requestModel);
                VolleyComm.getInstance(activity).postRequest(activity, this,
                        postUrl, json, POST_REQUEST, Constants.COMMON_API_TAG);
                break;
            case R.id.btnImageLoader:
                imageLoader = VolleyComm.getInstance(activity).getImageLoader();
                imageLoader.get(imageUrl, ImageLoader.getImageListener(imgNetworkingImage, R.mipmap.ic_launcher,
                        R.drawable.ic_delete));
                imgNetworkingImage.setImageUrl(imageUrl, imageLoader);
                break;
            case R.id.btnUploadImage:
                pickImage();
                break;
        }
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_A_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_A_PHOTO && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            try {
                InputStream inputStream = activity.getContentResolver().openInputStream(data.getData());
                 bitmap = BitmapFactory.decodeStream(inputStream);

                 uploadImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, "",
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(activity, obj.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            /*
             * If you want to add more parameters with the image
             * you can do it here
             * here we have only one parameter with the image
             * which is tags
             * */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tags", TAG);
                return params;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(activity).add(volleyMultipartRequest);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public void volleyCallBackResult(boolean isSuccessful, String response, String tag) {
        try {
            if (isSuccessful) {
                switch (tag) {
                    case GET_REUQEST: {
                    }
                    break;
                    case POST_REQUEST: {
                    }
                    break;
                }

            } else {
                Toaster.showDefaultToast(activity, "Server Error.", Gravity.CENTER, Toast.LENGTH_LONG);
            }
        } catch (Exception e) {

            handleException(e);
        }
    }
}

