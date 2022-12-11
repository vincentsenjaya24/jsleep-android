package vincentSenjayaJSleepDN.jsleep_android.request;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vincentSenjayaJSleepDN.jsleep_android.model.Account;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount(@Path("id") int id);
    @POST("account/register")
    Call<Account> postAccountRegister(@Query("name") String name, @Query("email") String email, @Query("password") String password);
    @POST("account/login")
    Call<Account> postAccountLogin(@Query("email") String email, @Query("password") String password);
//    @GET("account/page")
//    Call<Account> getPage(int page, int pageSize);
//    @POST("account/{id}/topUp")
//    Call<Account> topUp(@Path("id") int id, double balance);

}
