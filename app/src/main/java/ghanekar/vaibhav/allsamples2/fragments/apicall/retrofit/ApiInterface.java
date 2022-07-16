package ghanekar.vaibhav.allsamples2.fragments.apicall.retrofit;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @GET("employees")
    Call<Object> getJsonObject();

    @GET("volleyJsonArray")
    Call<List<Object>> getJsonArray();

    @POST("create")
    Call<Object> getMerchant(@Body Object requestObject);

    @Multipart
    @POST("")
    Call<Object> uploadImage(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("desc") RequestBody desc);
}