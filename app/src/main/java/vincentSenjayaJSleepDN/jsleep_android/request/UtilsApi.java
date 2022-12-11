package vincentSenjayaJSleepDN.jsleep_android.request;
/**
 *
 * @author Vincent Senjaya
 * @version 1.0
 * @since 11 Desember 2021
 */
import retrofit2.Retrofit;

public class UtilsApi {
    public static final String BASE_URL_API = "http://10.0.2.2:8010/";
    private static RetrofitClass RetrofitClient;

    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
