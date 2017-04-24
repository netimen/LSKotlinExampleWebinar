package com.loftschool.kotlinexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

// https://antonioleiva.com/kotlin-awesome-tricks-for-android/
public class MainActivity extends AppCompatActivity {

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (LinearLayout) findViewById(R.id.container);

        findViewById(R.id.add_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddItemClicked();
            }
        });

        findViewById(R.id.start_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                intent.putExtra(AnotherActivity.PARAM_NAME, "Some name");
                intent.putExtra(AnotherActivity.PARAM_PRICE, 15);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item:
                onAddItemClicked();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onAddItemClicked() {
        addItem(new Item("item", 0));
        Snackbar.make(container, R.string.added, Snackbar.LENGTH_LONG)
                .setAction(R.string.cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, R.string.cancelled, Toast.LENGTH_SHORT).show();
                    }
                })
                .setActionTextColor(Color.YELLOW)
                .show();
    }

    /// Items

    void addItem(Item item) {
        // adding item...
    }

    static class Item {
        String name;
        int price;

        Item(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

}
