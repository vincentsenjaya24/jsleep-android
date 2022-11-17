package vincentSenjayaJSleepDN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import java.util.*;
import android.os.Bundle;
import com.google.gson.Gson;
import android.view.Menu;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.IOException;
import java.io.InputStream;
import androidx.appcompat.widget.SearchView;
import androidx.annotation.NonNull;
import vincentSenjayaJSleepDN.jsleep_android.model.Room;


public class MainActivity extends AppCompatActivity {


    ListView l;

    String courseList[] = {"C-Programming", "Data Structure", "Database", "Python",
        "Java", "Operating System", "Compiler Design", "Android Development"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = findViewById(R.id.mainList);
        ArrayAdapter<String> arr;
        String jsonFileString = getJsonFromAssets(getApplicationContext(), "randomRoomList.json");
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Room>>() { }.getType();
        ArrayList<Room> rooms = gson.fromJson(jsonFileString, listUserType);
        ArrayList<String> roomList = readRoomName(rooms);;
        arr = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                roomList);
        l.setAdapter(arr);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem create = menu.findItem(R.id.create);
        MenuItem search = menu.findItem(R.id.search_button);
        MenuItem person = menu.findItem(R.id.personBtn);
        SearchView searchView = (SearchView) search.getActionView();
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.personBtn){
            Intent intentPerson = new Intent(this, AboutMeActivity.class);
            startActivity(intentPerson);
            return true;
        }
        return true;
    }
    public static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
    public static ArrayList<String> readRoomName(ArrayList<Room> room){
        ArrayList<String> roomList = new ArrayList<String>();
        for(Room i : room){
            roomList.add(i.name);
        }
        return roomList;
    }



}