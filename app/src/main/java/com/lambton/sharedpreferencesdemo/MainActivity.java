package com.lambton.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String SHARED_PREFERENCES_NAME = "username";
    public static final String KEY_NAME = "key_username";
    public static final String KEY_NAMES = "key_usernames";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //there is a class named SharedPref
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        // write into shared pref
        sharedPreferences.edit().putString(KEY_NAME, "Anmol").apply();
        // read from shared pref
        String name = sharedPreferences.getString(KEY_NAME,"NA");

        Log.d(TAG, "onCreate: "+ name);

        ArrayList<String> names = new ArrayList<>(Arrays.asList("Anmol","Aman", "Chaitnaya", "Raghav"));
//        sharedPreferences.edit().putStringSet(KEY_NAMES, new HashSet<>(names)).apply();

//        Set <String> receiveNames = sharedPreferences.getStringSet(KEY_NAMES, new HashSet<String>());
//        Log.d(TAG, "onCreate: "+ receiveNames);

        try {
            sharedPreferences.edit().putString(KEY_NAMES,ObjectSerializer.serialize(names)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> namesDeserialize = new ArrayList<>();
        String receiveData = sharedPreferences.getString(KEY_NAMES, null);
        Log.d(TAG, "onCreate: "+receiveData);
        try {
            namesDeserialize = (ArrayList)ObjectSerializer.deserialize(receiveData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onCreate: "+ namesDeserialize);
    }
}