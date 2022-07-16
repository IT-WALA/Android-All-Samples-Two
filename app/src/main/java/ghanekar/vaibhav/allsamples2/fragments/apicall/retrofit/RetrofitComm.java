package ghanekar.vaibhav.allsamples2.fragments.apicall.retrofit;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.List;

import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vaibhav Ghanekar on 07-04-2018.
 */

public class RetrofitComm {

    private static final String TAG = RetrofitComm.class.getSimpleName();
    static RetrofitComm instance;
    private RetrofitCallBack callBack;

    private RetrofitComm() {
    }

    public static RetrofitComm getInstance() {
        if (instance == null) {
            instance = new RetrofitComm();
        }
        return instance;
    }

    public interface RetrofitCallBack {
        public void retrofitCallBackResult(boolean isSuccessful, Object response, String s);
    }

    /***/
    public void getJsonObjectRequest(final Activity activity, Fragment fragment, final String tag) {
        final long startTime = System.currentTimeMillis();

        if (null != fragment)
            callBack = (RetrofitCallBack) fragment;
        else
            callBack = (RetrofitCallBack) activity;

        ApiInterface apiService = APIClient.getClientGet().create(ApiInterface.class);
        Call<Object> call = apiService.getJsonObject();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toaster.showDefaultToast(activity, "Time taken: " + String.valueOf((System.currentTimeMillis() - startTime) / 1000) + "sec", Gravity.TOP, Toast.LENGTH_LONG);
                Toaster.showDefaultToast(activity, response.body().toString(), Gravity.TOP, Toast.LENGTH_LONG);
                Log.d(TAG, "Response: " + response.body().toString());
                callBack.retrofitCallBackResult(true, response.body(), tag);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable error) {
                if (null != error) {
                    Toaster.showDefaultToast(activity, error.getMessage(), Gravity.TOP, Toast.LENGTH_LONG);
                    Log.d(TAG, "Error: " + error.getMessage());
                }
                callBack.retrofitCallBackResult(false, null, tag);
            }
        });
    }

    public void getJsonArrayRequest(Activity activity, Fragment fragment, final String tag) {


        if (null != fragment)
            callBack = (RetrofitCallBack) fragment;
        else
            callBack = (RetrofitCallBack) activity;

        ApiInterface apiService = APIClient.getClientGet().create(ApiInterface.class);
        Call<List<Object>> call = apiService.getJsonArray();
        call.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                Log.d(TAG, "Response: " + response.body().toString());
                callBack.retrofitCallBackResult(true, response.body(), tag);
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable error) {
                Log.d(TAG, "Error: " + error.getMessage());
                callBack.retrofitCallBackResult(false, null, tag);
            }
        });
    }

    public void postJsonObjectRequest(final Activity activity, Fragment fragment,
                                      Object requestObject, final String tag) {
        final long startTime = System.currentTimeMillis();
        Logger.LogVerbose(TAG + "Reuqest: " + requestObject.toString());
        if (null != fragment)
            callBack = (RetrofitCallBack) fragment;
        else
            callBack = (RetrofitCallBack) activity;

        ApiInterface apiService = APIClient.getClientPost().create(ApiInterface.class);
        Call<Object> call = apiService.getMerchant(requestObject);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toaster.showDefaultToast(activity, "Time taken: " + String.valueOf((System.currentTimeMillis() - startTime) / 1000) + "sec", Gravity.TOP, Toast.LENGTH_LONG);
                Toaster.showDefaultToast(activity, response.body().toString(), Gravity.TOP, Toast.LENGTH_LONG);
                Logger.LogVerbose(TAG + "Response: " + response.body().toString());
                callBack.retrofitCallBackResult(true, response.body(), tag);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable error) {
                if (null != error) {
                    Toaster.showDefaultToast(activity, error.getMessage(), Gravity.TOP, Toast.LENGTH_LONG);
                    Logger.LogVerbose(TAG + "Error: " + error.getMessage());
                }
                callBack.retrofitCallBackResult(false, null, tag);
            }
        });
    }
}
