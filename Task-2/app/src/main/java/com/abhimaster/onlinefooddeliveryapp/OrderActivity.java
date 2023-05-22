package com.abhimaster.onlinefooddeliveryapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import com.abhimaster.onlinefooddeliveryapp.Adapters.OrdersAdapter;
import com.abhimaster.onlinefooddeliveryapp.Models.OrdersModel;
import com.abhimaster.onlinefooddeliveryapp.databinding.ActivityOrderBinding;

import java.util.ArrayList;
import java.util.Objects;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(ContextCompat.getColor(OrderActivity.this,R.color.yellow));
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lightYellow)));

        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getOrders();


        OrdersAdapter adapters =new OrdersAdapter(list,this);
        binding.orderRecyclerView.setAdapter(adapters);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);
    }
}