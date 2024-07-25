package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBarHorizontal;
    private ProgressBar progressBarCircular;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarHorizontal = findViewById(R.id.progressBarHorizontal);
        progressBarCircular = findViewById(R.id.progressBarCircular);

        progressBarCircular.setVisibility(View.GONE);

        progress = 0;
    }

    public void loadProgressBar(View view){
        this.progress = this.progress + 1;
        progressBarHorizontal.setProgress(this.progress);

        progressBarCircular.setVisibility(View.VISIBLE);

        if (this.progress > 10) {
            progressBarCircular.setVisibility(View.GONE);
        }
    }
}