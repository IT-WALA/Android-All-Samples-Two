package ghanekar.vaibhav.allsamples2.fragments.apicall.volley;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

import static com.android.volley.toolbox.Volley.newRequestQueue;

/**
 * Created by Vaibhav Ghanekar on 06-04-2018.
 */

public class VolleyComm {

    private String TAG = VolleyComm.class.getSimpleName() + "_";
    String requestBody;

    private static RequestQueue queue;
    private VolleyCallBack callBack;
    private VolleyCallBack serviceCallBack;
    private static VolleyComm instance = null;
    private ImageLoader mImageLoader;
    public static final String COMMON_APITAG = "COMMON_APITAG";

    //Singleton.
    private VolleyComm(Activity activity) {
        Cache cache = new DiskBasedCache(activity.getCacheDir(), 10 * 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        queue = new RequestQueue(cache, network);
        //queue = newRequestQueue(activity);
        queue.start();

        setupImageLoader();
    }

    public static VolleyComm getInstance(Activity activity) {
        if (null == instance) {
            instance = new VolleyComm(activity);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        return queue;
    }

    public void postRequest(final Activity activity, Fragment fragment,
                            String url, final String json, final String apiTag, String commonTag) {
        final long startTime = System.currentTimeMillis();
        //Logger.LogVerbose("Request: " + json);

        if (null != fragment)
            callBack = (VolleyCallBack) fragment;
        else
            callBack = (VolleyCallBack) activity;

        if (null != json) {
            requestBody = json.toString();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toaster.showDefaultToast(activity, "Time Taken: " +
                        String.valueOf((System.currentTimeMillis() - startTime) / 1000) + "Sec", Gravity.TOP, Toast.LENGTH_SHORT);
                //Logger.LogVerbose(TAG + "Response: " + response);
                Toaster.showDefaultToast(activity, response, Gravity.TOP, Toast.LENGTH_SHORT);

                callBack.volleyCallBackResult(true, response, apiTag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != error && null != error.getMessage()) {
                    Toaster.showDefaultToast(activity, error.getMessage(), Gravity.TOP, Toast.LENGTH_SHORT);
                    Logger.LogError(TAG + "Error: " + error.getMessage());
                }
                callBack.volleyCallBackResult(false, error.getMessage(), apiTag);
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/MCNT_MH4102090001; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                if (null != json) {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        //VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
                return null;
            }

           /* @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headerInfo = new HashMap<>();
                headerInfo.put("token", SPUtils.getString(activity, Constants.SP_KEY_TOKEN));
                return headerInfo;
            }*/
        };
        stringRequest.setTag(COMMON_APITAG);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(1000 * 10, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(stringRequest);
    }

    //this is for service
    public void postRequest(final Context context, Fragment fragment, String url,
                            final String json, final String tag, String commonTag) {
        try {
            Logger.LogVerbose(TAG + "Request: " + "" + json);

            serviceCallBack = (VolleyCallBack) context;

            if (null != json) {
                requestBody = json.toString();
            }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Logger.LogVerbose(TAG + "Response: " + "" + response);
                    serviceCallBack.volleyCallBackResult(true, response, tag);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Log.v(TAG + "Error: ", error.getMessage());
                    Logger.LogError(TAG + "Error: " + error.getMessage());
                    serviceCallBack.volleyCallBackResult(false, error.getMessage(), tag);
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/MCNT_MH4102090001; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    if (null != json) {
                        try {
                            return requestBody == null ? null : requestBody.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            //VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                            return null;
                        }
                    }
                    return null;
                }
            };
            stringRequest.setTag(COMMON_APITAG);

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(1000 * 10, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRequest(final Activity activity, Fragment fragment, String url, final String tag) {
        final long startTime = System.currentTimeMillis();
        if (null != fragment)
            callBack = (VolleyCallBack) fragment;
        else
            callBack = (VolleyCallBack) activity;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toaster.showDefaultToast(activity, "Time Taken: "
                        + String.valueOf((System.currentTimeMillis() - startTime) / 1000) + "sec", Gravity.TOP, Toast.LENGTH_SHORT);
                //Logger.LogVerbose(TAG + "Response: " + response);
                Toaster.showDefaultToast(activity, response, Gravity.TOP, Toast.LENGTH_SHORT);
                callBack.volleyCallBackResult(true, response, tag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != error && null != error.getMessage()) {
                    Toaster.showDefaultToast(activity, error.getMessage(), Gravity.TOP, Toast.LENGTH_SHORT);
                    //Logger.LogError(TAG + "Error: " + error.getMessage());
                }
                callBack.volleyCallBackResult(false, error.getMessage(), tag);
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/MCNT_MH4102090001; charset=utf-8";
            }

           /* @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headerInfo = new HashMap<>();
                headerInfo.put("token", SPUtils.getString(activity, Constants.SP_KEY_TOKEN));
                return headerInfo;
            }*/
        };

        stringRequest.setTag(COMMON_APITAG);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(1000 * 10, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(stringRequest);
    }

    public void cancelRequests() {
        queue.cancelAll(COMMON_APITAG);
    }

    //CallBack navigates to the activity/frag after web service communication is done.
    public interface VolleyCallBack {
        public void volleyCallBackResult(boolean isSuccessful, String response, String tag);
    }

    //Image loader
    private void setupImageLoader() {
        mImageLoader = new ImageLoader(queue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });

    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}