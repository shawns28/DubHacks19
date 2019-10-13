package com.example.binnahacks19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class ScanItem extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_item);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmScreen();
            }
        });
    }

    public void openConfirmScreen() {
        Intent intent = new Intent(this, ConfirmScreen.class);
        ArrayList<String> itemList = getIntent().getStringArrayListExtra("ItemList");
        intent.putStringArrayListExtra("ItemList", itemList);
        startActivity(intent);
    }
}