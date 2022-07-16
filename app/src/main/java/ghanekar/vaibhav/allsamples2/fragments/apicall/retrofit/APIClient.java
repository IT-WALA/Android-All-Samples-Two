package ghanekar.vaibhav.allsamples2.fragments.apicall.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class APIClient {

    public static final String BASE_URL_GET = "https://androidtutorialpoint.com/api/";
    public static final String BASE_URL_POST = "http://13.126.200.200:8080/SmartStore/";

    private static String getUrl = "http://dummy.restapiexample.com/api/v1/";
    private static String postUrl = "http://dummy.restapiexample.com/api/v1/";

    private static Retrofit retrofit = null;

    public static Retrofit getClientPost() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(postUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientGet() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(getUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}