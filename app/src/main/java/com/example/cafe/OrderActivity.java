package com.example.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    private static final String EXTRA_USER_NAME = "name";
    private TextView tvGreetings;
    private TextView tvAdditives;

    private RadioGroup rgDrinks;
    private RadioButton rbTea;
    private RadioButton rbCoffee;


    private CheckBox cbSugar;
    private CheckBox cbMilk;
    private CheckBox cbLemon;

    private Spinner sCoffee;
    private Spinner sTea;

    private Button bMakeOrder;
    private String drink;
    private String userName;

    private void initViews() {
        tvGreetings = findViewById(R.id.textViewGreetings);
        rgDrinks = findViewById(R.id.radioGroupDrinks);
        rbTea = findViewById(R.id.radioButtonDrinksTea);
        rbCoffee = findViewById(R.id.radioButtonDrinksCoffee);
        tvAdditives = findViewById(R.id.textViewAdditives);
        cbSugar = findViewById(R.id.checkboxSugar);
        cbMilk = findViewById(R.id.checkboxMilk);
        cbLemon = findViewById(R.id.checkboxLemon);
        sCoffee = findViewById(R.id.spinnerCoffee);
        sTea = findViewById(R.id.spinnerTea);
        bMakeOrder = findViewById(R.id.buttonMakeOrder);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initViews();
        setupUserName();
        rgDrinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                if (id == rbTea.getId()) {
                    onChooseTea();
                } else if (id == rbCoffee.getId()) {
                    onChooseCoffee();
                }

            }
        });
        rbTea.setChecked(true);
        bMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMadeOrder();
            }
        });
    }

    private void onMadeOrder() {
        ArrayList<String> additives = new ArrayList<>();
        if (cbSugar.isChecked()) {
            additives.add(String.valueOf(R.string.sugar));
        }
        if (cbMilk.isChecked()) {
            additives.add(String.valueOf(R.string.milk));
        }
        if (rbTea.isChecked() && cbLemon.isChecked()) {
            additives.add(String.valueOf(R.string.lemon));
        }
        String drinkType = "";
        if (rbTea.isChecked()) {
            drinkType = sTea.getSelectedItem().toString();
        } else if (rbCoffee.isChecked()) {
            drinkType = sCoffee.getSelectedItem().toString();
        }
        Intent intent = OrderDetailActivity.newIntent(this, userName,drink,drinkType,additives.toString());
        startActivity(intent);
    }

    private void onChooseTea() {
        drink = getString(R.string.tea);
        String additivesLabel = getString(R.string.additives, drink);
        tvAdditives.setText(additivesLabel);
        cbLemon.setVisibility(View.VISIBLE);
        sCoffee.setVisibility(View.INVISIBLE);
        sTea.setVisibility(View.VISIBLE);
    }

    private void onChooseCoffee() {
        drink = getString(R.string.coffee);
        String additivesLabel = getString(R.string.additives, drink);
        tvAdditives.setText(additivesLabel);
        cbLemon.setVisibility(View.INVISIBLE);
        sTea.setVisibility(View.INVISIBLE);
        sCoffee.setVisibility(View.VISIBLE);
    }

    private void setupUserName() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greetings = getString(R.string.greetings, userName);
        tvGreetings.setText(greetings);
    }

    public static Intent newIntent(Context context, String userName) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        return intent;
    }
}