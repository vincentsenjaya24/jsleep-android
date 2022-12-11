package vincentSenjayaJSleepDN.jsleep_android.request;
/**
 *
 * @author Vincent Senjaya
 * @version 1.0
 * @since 11 Desember 2021
 */
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vincentSenjayaJSleepDN.jsleep_android.model.Account;
import vincentSenjayaJSleepDN.jsleep_android.model.Renter;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount(@Path("id") int id);
    @GET("account/getByEmail")
    Call<Account> getAccountByLogin(@Query("email") String email, @Query("password") String password);
    @POST("account/register")
    Call<Account> postAccountRegister(@Query("name") String name, @Query("email") String email, @Query("password") String password);
    @POST("account/login")
    Call<Account> postAccountLogin(@Query("email") String email, @Query("password") String password);
    @POST("account/{id}/registerRenter")
    Call<Account> postRenter(@Path("id") int id, @Query("username") String username, @Query("address") String address, @Query("phoneNumber") String phoneNumber);
//    @GET("account/page")
//    Call<Account> getPage(int page, int pageSize);
//    @POST("account/{id}/topUp")
//    Call<Account> topUp(@Path("id") int id, double balance);

}
