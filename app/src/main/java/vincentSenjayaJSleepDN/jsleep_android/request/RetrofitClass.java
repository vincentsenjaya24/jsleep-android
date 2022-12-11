package vincentSenjayaJSleepDN.jsleep_android.request;
/**
 *
 * @author Vincent Senjaya
 * @version 1.0
 * @since 11 Desember 2021
 */
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String baseUrl){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        if(retrofit  == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(client)
                    .build();
        }
        return retrofit;
    }
}
