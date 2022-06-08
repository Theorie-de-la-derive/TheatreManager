package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_seances, R.id.nav_involvement, R.id.nav_profile)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        DBHelper dbHelper = new DBHelper(this);
        DBHelperSecond dbsecHelper = new DBHelperSecond(this);
        SQLiteDatabase DB = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        Random r = new Random();

        String[][] theatreNames = {{"Кармен", "Драма"}, {"Записки сумасшедшего", "Драма"}, {"Недоросль", "Комедия"}, {"Горе от ума", "Драма"}, {"Ревизор", "Комедия"}, {"Женитьба", "Комедия"}, {"Гроза", "Трагедия"}, {"Гордость и предубеждение", "Мюзикл"}, {"Евгений Онегин", "Драма"}, {"Бесприданница", "Драма"},};
        DB.delete(dbHelper.DB_NAME, null, null);

        int counter = 0;
        while (counter < theatreNames.length) {
            contentValues.put(dbHelper.KEY_NAME, theatreNames[counter][0]);
            contentValues.put(dbHelper.KEY_GENRE, theatreNames[counter][1]);
            contentValues.put(dbHelper.KEY_CHAIRS, r.nextInt(100) );

            DB.insert(dbHelper.DB_NAME, null, contentValues);
            counter++;
        }


        Cursor cursor = DB.query(dbHelper.DB_NAME, null, null, null, null, null, null);




    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}