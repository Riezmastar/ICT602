package com.example.electricitybills;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etElectricityUnits, etRebatePercentage;
    TextView tvResult;
    Button btnCalculate, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etElectricityUnits = findViewById(R.id.etElectricityUnits);
        etRebatePercentage = findViewById(R.id.etRebatePercentage);
        tvResult = findViewById(R.id.tvResult);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnAbout = findViewById(R.id.btnAbout);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void calculateBill() {
        // Get the input values
        String unitsStr = etElectricityUnits.getText().toString();
        String rebateStr = etRebatePercentage.getText().toString();
        try {
            // Parse the inputs
            double units = Double.parseDouble(unitsStr);
            double rebate = Double.parseDouble(rebateStr);


            // Initialize bill calculation variables
            double totalBill = 0.0;
            double remainingUnits = units;

            // Block 1: First 200 kWh at 21.8 sen (0.218 RM) per kWh
            if (remainingUnits > 200) {
                totalBill += 200 * 0.218; // 21.8 sen = 0.218 RM
                remainingUnits -= 200;
            } else {
                totalBill += remainingUnits * 0.218; // 21.8 sen = 0.218 RM
                remainingUnits = 0;
            }

            // Block 2: Next 100 kWh at 33.4 sen (0.334 RM) per kWh
            if (remainingUnits > 100) {
                totalBill += 100 * 0.334; // 33.4 sen = 0.334 RM
                remainingUnits -= 100;
            } else {
                totalBill += remainingUnits * 0.334; // 33.4 sen = 0.334 RM
                remainingUnits = 0;
            }

            // Block 3: Next 300 kWh at 51.6 sen (0.516 RM) per kWh
            if (remainingUnits > 300) {
                totalBill += 300 * 0.516; // 51.6 sen = 0.516 RM
                remainingUnits -= 300;
            } else {
                totalBill += remainingUnits * 0.516; // 51.6 sen = 0.516 RM
                remainingUnits = 0;
            }

            // Block 4: Beyond 600 kWh at 54.6 sen (0.546 RM) per kWh
            if (remainingUnits > 0) {
                totalBill += remainingUnits * 0.546; // 54.6 sen = 0.546 RM
            }

            // Apply rebate: Subtract the rebate percentage from the total bill
            double rebateAmount = totalBill * (rebate / 100);
            double finalBill = totalBill - rebateAmount;

            // Display the result with two decimal places
            tvResult.setText("Total Bill: RM " + String.format("%.2f", finalBill));
        } catch (NumberFormatException nfe) {

            Toast.makeText(getApplicationContext(), "Please Enter Valid Value", Toast.LENGTH_SHORT).show();

        }
    }
}