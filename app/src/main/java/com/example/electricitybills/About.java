package com.example.electricitybills;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {

    TextView devDetail, tvCopyright, github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        devDetail = findViewById(R.id.devDetail);
        tvCopyright = findViewById(R.id.tvCopyright);
        github = findViewById(R.id.github);

        // Developer details
        devDetail.setText("Developer: Muhammad Rizal Bin Ismail\nStudent Number: 2022898152 \n Group: RCDS2515A");

        // Copyright information
        tvCopyright.setText("© 2024 Muhammad Rizal Bin Ismail. All Rights Reserved.");

        // Link to GitHub page (replace with your actual GitHub URL)
        github.setText("GitHub: https://github.com/Riezmastar/ICT602");

        // Make the website link clickable
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/username/repository"));
                startActivity(browserIntent);
            }
        });

        // Automatically detect links and apply formatting
        Linkify.addLinks(github, Linkify.WEB_URLS);

        // Change the color of the link to blue programmatically
        github.setLinkTextColor(getResources().getColor(android.R.color.holo_blue_light));
    }
}
