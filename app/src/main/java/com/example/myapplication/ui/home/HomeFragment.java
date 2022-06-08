package com.example.myapplication.ui.home;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.DBHelper;
import com.example.myapplication.MainActivity;
import com.example.myapplication.MyTheatre;
import com.example.myapplication.MyTheatreAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.util.Random;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    Random r = new Random();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        MyTheatreAdapter adapter = new MyTheatreAdapter(view.getContext(), makeTheatre());
        ListView lv = (ListView) view.findViewById(R.id.listView);
        lv.setAdapter(adapter);






        return view;
    }


    MyTheatre[] makeTheatre() {
        MyTheatre[] arr = new MyTheatre[10];

        DBHelper dbHelper = new DBHelper(this.getActivity());
        SQLiteDatabase DB = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = DB.query(dbHelper.DB_NAME, null, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            int i = 0;
            do {
                int chairIndex = cursor.getColumnIndex(dbHelper.KEY_CHAIRS);
                int nameIndex = cursor.getColumnIndex(dbHelper.KEY_NAME);
                int genreIndex = cursor.getColumnIndex(dbHelper.KEY_GENRE);
                arr[i] = new MyTheatre(cursor.getString(nameIndex), cursor.getString(genreIndex), cursor.getInt(chairIndex));
                i++;
            } while (cursor.moveToNext());
            cursor.close();
        }

        return arr;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}