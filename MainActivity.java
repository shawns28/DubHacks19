package com.example.binnahacks19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ListView mListView1, mListView2, mListView3;
    private ArrayAdapter<String> adapter1, adapter2, adapter3;

    ArrayList<String> itemList = new ArrayList<>();

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> list3 = new ArrayList<>();

        if (getIntent().getExtras() != null) {
            String quantity = getIntent().getStringExtra("Quantity");
            String notes = getIntent().getStringExtra("Notes");
            itemList = getIntent().getStringArrayListExtra("ItemList");

            ArrayList<Item> items = new ArrayList<>();
            for (String s : itemList) {
                try {
                    items.add(deserialize(s));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            for (Item item : items) {
                long diffInMillies = item.getExpirationDate().getTime() - Calendar.getInstance().getTime().getTime();
                int diff =  (int) (diffInMillies / (24 * 1000 * 60 * 60));
                System.out.println(diff);
                if (diff <= 3) {
                    list1.add(item.toString());
                } else if (diff <= 7) {
                    list2.add(item.toString());
                } else {
                    list3.add(item.toString());
                }
            }

            mListView1 = (ListView)findViewById(R.id.listView1);
            adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);
            adapter1.notifyDataSetChanged();

            mListView2 = (ListView)findViewById(R.id.listView2);
            adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);
            adapter2.notifyDataSetChanged();

            mListView3 = (ListView)findViewById(R.id.listView3);
            adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list3);
            adapter3.notifyDataSetChanged();
        }

        mListView1 = (ListView)findViewById(R.id.listView1);
        mListView2 = (ListView)findViewById(R.id.listView2);
        mListView3 = (ListView)findViewById(R.id.listView3);

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);
        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list3);
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
        intent.putStringArrayListExtra("ItemList", itemList);
        startActivity(intent);
    }

    public Item deserialize(String s) throws ParseException {
        String[] split = s.split("\t");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        return new Item(split[0], Integer.parseInt(split[1]), split[2], dateFormat.parse(split[3]));
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
