package com.abhimaster.onlinefooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.abhimaster.onlinefooddeliveryapp.Adapters.MainAdapter;
import com.abhimaster.onlinefooddeliveryapp.Models.MainModel;
import com.abhimaster.onlinefooddeliveryapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.yellow));
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lightYellow)));

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.pizza,"Pizza","250","Delightful combination of onion, capsicum, tomato & grilled mushroom "));
        list.add(new MainModel(R.drawable.burger,"Burger","99","Enjoy a combo of Mexican McAloo Tikki + Fries  "));
        list.add(new MainModel(R.drawable.mishalpaw,"Mishalpaw","30","It consists of misal (a spicy curry usually made from moth beans) and pav "));
        list.add(new MainModel(R.drawable.frankie,"Frankie","60","frankie usually includes assorted toppings of veggie cheese, potato tikki, veg cutlet. "));
        list.add(new MainModel(R.drawable.dosa,"Dosa","80","Dosa is a thin made from a fermented batter of ground black lentils and rice "));

        MainAdapter adapter = new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}