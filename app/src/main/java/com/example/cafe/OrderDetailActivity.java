package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderDetailActivity extends AppCompatActivity {
    private static final String EXTRA_USER_NAME = "userName";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_DRINK_TYPE = "drinkType";
    private static final String EXTRA_ADDITIVES = "additives";
    private TextView tvName;
    private TextView tvDrink;
    private TextView tvDrinkType;
    private TextView tvAdditives;
    private void initViews() {
         tvName = findViewById(R.id.textViewName);
         tvDrink = findViewById(R.id.textViewDrink);
         tvDrinkType = findViewById(R.id.textViewDrinkType);
         tvAdditives = findViewById(R.id.textViewAdditives);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initViews();
        Intent intent = getIntent();
         tvName.setText(intent.getStringExtra(EXTRA_USER_NAME));
         tvDrink.setText(intent.getStringExtra(EXTRA_DRINK));
         tvDrinkType.setText(intent.getStringExtra(EXTRA_DRINK_TYPE));
         tvAdditives.setText(intent.getStringExtra(EXTRA_ADDITIVES));
    }
    public static Intent newIntent(Context context, String userName, String drink, String drinkType, String additives){
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        intent.putExtra(EXTRA_ADDITIVES, additives);
        return intent;
    }
}