package vincentSenjayaJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vincentSenjayaJSleepDN.jsleep_android.model.Account;
import vincentSenjayaJSleepDN.jsleep_android.request.BaseApiService;
import vincentSenjayaJSleepDN.jsleep_android.request.UtilsApi;
/**
 * Class untuk melakukan proses register account
 * @author Vincent Senjaya
 * @version 1.0
 * @since 11 Desember 2021
 */
public class RegisterActivity extends AppCompatActivity {
    static BaseApiService mApiService;
    EditText name;
    EditText email;
    EditText password;
    Context mContext;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.RegisterName);
        email = findViewById(R.id.RegisterEmail);
        password = findViewById(R.id.RegisterPassword);
        registerButton = findViewById(R.id.registerButton);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Username/Password/Email required", Toast.LENGTH_LONG).show();
                } else {
                    requestRegister();
                }
//
            }
        });
    }

    public void requestRegister(){
        mApiService.postAccountRegister(name.getText().toString(),email.getText().toString(),password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Toast.makeText(mContext, "Register Successful", Toast.LENGTH_LONG).show();
                    Intent move = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(move);
                }
                else{
                    Toast.makeText(mContext, "Register Failed"+response.message(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(mContext, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}