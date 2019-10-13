package com.example.binnahacks19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView1, mListView2, mListView3;
    private ArrayAdapter<String> adapter1, adapter2, adapter3;

    ArrayList<String> data1 = new ArrayList<>();
    ArrayList<String> data2 = new ArrayList<>();
    ArrayList<String> data3 = new ArrayList<>();

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            String quantity = getIntent().getStringExtra("Quantity");
            String notes = getIntent().getStringExtra("Notes");
            data1 = getIntent().getStringArrayListExtra("ItemList");
            System.out.println(data1);

            mListView1 = (ListView)findViewById(R.id.listView1);
            adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data1);
            adapter1.notifyDataSetChanged();
        }

        mListView1 = (ListView)findViewById(R.id.listView1);
        mListView2 = (ListView)findViewById(R.id.listView2);
        mListView3 = (ListView)findViewById(R.id.listView3);

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data1);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data2);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data3);
        mListView1.setAdapter(adapter1);
        mListView2.setAdapter(adapter2);
        mListView3.setAdapter(adapter3);

        ListUtils.setDynamicHeight(mListView1);
        ListUtils.setDynamicHeight(mListView2);
        ListUtils.setDynamicHeight(mListView3);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanItem();
            }
        });
    }

    public void openScanItem() {
        Intent intent = new Intent(this, ScanItem.class);
        intent.putStringArrayListExtra("ItemList", data1);
        startActivity(intent);
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = MeasureSpec.makeMeasureSpec(mListView.getWidth(), MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }
}
