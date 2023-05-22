package com.abhimaster.onlinefooddeliveryapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abhimaster.onlinefooddeliveryapp.databinding.ActivityDetailBinding;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getWindow().setStatusBarColor(ContextCompat.getColor(DetailActivity.this, R.color.yellow));
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lightYellow)));

        final DBHelper helper = new DBHelper(this);
        if (getIntent().getIntExtra("type", 0) == 1) {
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageResource(image);
            binding.priceLbL.setText(String.format("%d", price));
            binding.textView.setText(name);
            binding.detailDescription.setText(description);


            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = helper.insertOrder(
                            binding.textView.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );
                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Ordered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
                    int id = getIntent().getIntExtra("id",0);
                    Cursor cursor = helper.getOrderById(id);
                    final int image = cursor.getInt(4);
                    binding.detailImage.setImageResource(image);
                    binding.priceLbL.setText(String.format("%d", cursor.getInt(3)));
                    binding.textView.setText(cursor.getString(6));
                    binding.detailDescription.setText(cursor.getString(5));

                    binding.nameBox.setText(cursor.getString(1));
                    binding.phoneBox.setText(cursor.getString(2));
                    binding.insertButton.setText("Update Now");

                    binding.insertButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean isUpdated = helper.updateOrder(
                                    binding.nameBox.getText().toString(),
                                    binding.phoneBox.getText().toString(),
                                    Integer.parseInt(binding.priceLbL.getText().toString()),
                                    image,
                                    binding.detailDescription.getText().toString(),
                                    binding.textView.getText().toString(),
                                    1,
                                    id
                            );
                            if(isUpdated){
                                Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(DetailActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
    }
}