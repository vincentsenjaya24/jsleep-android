package vincentSenjayaJSleepDN.jsleep_android;

import static vincentSenjayaJSleepDN.jsleep_android.LoginActivity.account;
import static vincentSenjayaJSleepDN.jsleep_android.LoginActivity.rentername;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vincentSenjayaJSleepDN.jsleep_android.model.Account;
import vincentSenjayaJSleepDN.jsleep_android.model.Renter;
import vincentSenjayaJSleepDN.jsleep_android.request.BaseApiService;
import vincentSenjayaJSleepDN.jsleep_android.request.UtilsApi;
/**
 * Class untuk menampilkan informasi account
 * @author Vincent Senjaya
 * @version 1.0
 * @since 11 Desember 2021
 */
public class AboutMeActivity extends AppCompatActivity {

    TextView userName;
    TextView userEmail;
    TextView balance;
    Button logOut;
    Button registerFix;
    Button cancel;
    EditText username;
    EditText address;
    EditText phoneNumber;
    Context mContext;
    Button registerRenter;
    TextView registeredName = null;
    TextView registeredAddress = null;
    TextView registeredPhone = null;
    TextView namelabel;
    TextView addresslabel;
    TextView phonelabel;
    CardView renterCard;
    private static Account account = null;
    static BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        mContext = this;
        mApiService = UtilsApi.getApiService();
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        balance = findViewById(R.id.balance);
        registerFix = findViewById(R.id.RegisterFixButton);
        cancel = findViewById(R.id.CancelButton);
        username = findViewById(R.id.registerRenterName);
        address = findViewById(R.id.registerRenterAddress);
        phoneNumber = findViewById(R.id.registerRenterNumber);
        logOut = findViewById(R.id.logOut);
        renterCard = findViewById(R.id.renterCard);
        registerRenter = findViewById(R.id.registRent);
        registeredName = findViewById(R.id.renterName);
        registeredAddress = findViewById(R.id.renterAddress);
        registeredPhone = findViewById(R.id.renterPhone);
        namelabel = findViewById(R.id.renterNameLabel);
        addresslabel = findViewById(R.id.renterAddressLabel);
        phonelabel = findViewById(R.id.renterPhoneLabel);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(LoginActivity.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        userEmail.setText(sharedPreferences.getString(LoginActivity.firstemail,null));
        userName.setText(sharedPreferences.getString(LoginActivity.firstname,""));
        registeredName.setText(sharedPreferences.getString(LoginActivity.rentername,null));
        registeredAddress.setText(sharedPreferences.getString(LoginActivity.renteraddress,""));
        registeredPhone.setText(sharedPreferences.getString(LoginActivity.renterphone,""));
        //checkRenter();
        if (LoginActivity.getLoggedAccount().renter==null){
            renterCard.setVisibility(View.GONE);
            registerFix.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
            username.setVisibility(View.VISIBLE);
            address.setVisibility(View.VISIBLE);
            phoneNumber.setVisibility(View.VISIBLE);
            registerRenter.setVisibility(View.GONE);
        }
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
               SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.clear();
               editor.commit();
               Intent move = new Intent(AboutMeActivity.this,LoginActivity.class);
               startActivity(move);
            }

        });
        registerRenter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view){
                        if(registeredName!=null){
                            renterCard.setVisibility(View.VISIBLE);
                            username.setVisibility(View.GONE);
                        } else{
                            renterCard.setVisibility(View.GONE);
                            registerFix.setVisibility(View.VISIBLE);
                            cancel.setVisibility(View.VISIBLE);
                            username.setVisibility(View.VISIBLE);
                            address.setVisibility(View.VISIBLE);
                            phoneNumber.setVisibility(View.VISIBLE);
                            registerRenter.setVisibility(View.GONE);
                        }

                    }

        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
//                registerFix.setVisibility(View.GONE);
//                cancel.setVisibility(View.GONE);
//                username.setVisibility(View.GONE);
//                address.setVisibility(View.GONE);
//                phoneNumber.setVisibility(View.GONE);
                renterCard.setVisibility(View.GONE);
                registerRenter.setVisibility(View.VISIBLE);
            }

        });
        registerFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                renterCard.setVisibility(View.VISIBLE);
                registerRenter.setVisibility(View.GONE);
                registerFix.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
                username.setVisibility(View.GONE);
                address.setVisibility(View.GONE);
                phoneNumber.setVisibility(View.GONE);
                requestRegisterRenter();
                Intent move = new Intent(AboutMeActivity.this, LoginActivity.class);
                move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(move);
            }

        });


    }
    protected void requestRegisterRenter (){
        mApiService.postRenter(LoginActivity.getLoggedAccount().id,username.getText().toString(),address.getText().toString(),phoneNumber.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Toast.makeText(mContext, "Register Successful", Toast.LENGTH_LONG).show();
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
    public void checkRenter(){
        if (account.renter != null){
            registerRenter.setVisibility(View.GONE);
            renterCard.setVisibility(View.VISIBLE);

        } else if (account.renter == null){
            registerRenter.setVisibility(View.VISIBLE);
            renterCard.setVisibility(View.GONE);
        }
    }
}