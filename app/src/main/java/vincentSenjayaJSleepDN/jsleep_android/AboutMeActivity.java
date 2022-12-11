package vincentSenjayaJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import vincentSenjayaJSleepDN.jsleep_android.request.BaseApiService;
import vincentSenjayaJSleepDN.jsleep_android.request.UtilsApi;

public class AboutMeActivity extends AppCompatActivity {

    TextView userName;
    TextView userEmail;
    TextView balance;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        balance = findViewById(R.id.balance);
        logOut = findViewById(R.id.logOut);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(LoginActivity.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        userEmail.setText(sharedPreferences.getString(LoginActivity.firstemail,null));
        userName.setText(sharedPreferences.getString(LoginActivity.firstname,""));
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
    }
}