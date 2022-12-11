package vincentSenjayaJSleepDN.jsleep_android;

/**
 * Class untuk melakukan proses login account
 * @author Vincent Senjaya
 * @version 1.0
 * @since 11 Desember 2021
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vincentSenjayaJSleepDN.jsleep_android.model.Account;
import vincentSenjayaJSleepDN.jsleep_android.request.BaseApiService;
import vincentSenjayaJSleepDN.jsleep_android.request.UtilsApi;

public class LoginActivity extends AppCompatActivity {
    static BaseApiService mApiService;
    private EditText email;
    private EditText password;
    private Context mContext;
    public static String firstemail = "first";
    public static String firstname = "firstn";
    public final static String SHARED_PREF_NAME = "log_user_info";
    SharedPreferences sharedPreferences;
    public static Account account = null;
    public static String rentername = "hai";
    public static String renterphone = "hai2";
    public static String renteraddress = "hai3";
    public static int account_id;
    public static Account getLoggedAccount(){
        return account;
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView register = findViewById(R.id.registerNow);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent move = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(move);
            }
        });
        Button login = findViewById(R.id.loginButton);
        email = findViewById(R.id.usernameTextBox);
        password = findViewById(R.id.passwordTextBox);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Username/Password required", Toast.LENGTH_LONG).show();
                } else {
                    requestLogin();
                    account = requestAccount();

                }
            }
        });


    }
    protected Account requestAccount (){
        mApiService.getAccountByLogin(email.getText().toString(),password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    //Account account;
                    account = response.body();
                    account_id = account.id;
                    SharedPreferences.Editor editor =  sharedPreferences.edit();
                    editor.putString(firstemail, account.email);
                    editor.putString(firstname,account.name);
                    if (account.renter != null){
                        editor.putString(rentername,account.renter.username);
                        editor.putString(renterphone,account.renter.phoneNumber);
                        editor.putString(renteraddress,account.renter.address);
                    }
//
                    editor.commit();
                }
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(mContext, "no account id = 0", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
    public void requestLogin(){
        //email.getText().toString(),password.getText().toString()
        mApiService.postAccountLogin(email.getText().toString(),password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Toast.makeText(mContext, "Login Successful", Toast.LENGTH_LONG).show();
                    Intent move = new Intent(LoginActivity.this, MainActivity.class);
                    move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(move);
                }
                else{
                    Toast.makeText(mContext, "Login Failed"+response.message(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(mContext, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}