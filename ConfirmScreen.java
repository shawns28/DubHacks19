package com.example.binnahacks19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ConfirmScreen extends AppCompatActivity {

    private EditText mEditText1, mEditText2, mEditText3;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_screen);

        mEditText1 = (EditText)findViewById(R.id.editText1);
        mEditText2 = (EditText)findViewById(R.id.editText2);
        mEditText3 = (EditText)findViewById(R.id.editText3);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(mEditText1.getText().toString(), Integer.parseInt(mEditText2.getText().toString()), mEditText3.getText().toString());
            }
        });
    }

    public void openMainActivity(String name, int quantity, String notes) {
        Intent intent = new Intent(ConfirmScreen.this, MainActivity.class);
        ArrayList<String> itemList = getIntent().getStringArrayListExtra("ItemList");
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        Random random = new Random();
        int day = random.nextInt(4)+14;
        Date expirationDate = new Date(19, 9, day);

        Item item = new Item(name, quantity, notes, expirationDate);
        itemList.add(item.serialized());
        intent.putStringArrayListExtra("ItemList", itemList);
        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("Quantity", quantity);
        intent.putExtra("Notes", notes);
        startActivity(intent);
    }
}
