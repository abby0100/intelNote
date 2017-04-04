package com.example.xy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.xy.sqlite.SqliteActivity;
import com.example.xy.tasks.TaskListActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "SqliteActivity";
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.bt_jni:
                    startJniActivity();
                    break;
                case R.id.bt_sqlite:
                    startSqliteActivity();
                    break;
                case R.id.bt_task_list:
                    startTaskListActivity();
                    break;
                default:
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        startDemo();
    }

    private void startDemo() {

        Log.d(LOG_TAG, "startDemo");
        Button jniButton = (Button) findViewById(R.id.bt_jni);
        Button sqliteButton = (Button) findViewById(R.id.bt_sqlite);
        Button taskListButton = (Button) findViewById(R.id.bt_task_list);

        jniButton.setOnClickListener(mOnClickListener);
        sqliteButton.setOnClickListener(mOnClickListener);
        taskListButton.setOnClickListener(mOnClickListener);
    }

    private void startJniActivity() {
        Log.d(LOG_TAG, "startJniActivity");
        Intent intent = new Intent(MainActivity.this, JNIActivity.class);
        startActivity(intent);
    }

    private void startSqliteActivity() {
        Log.d(LOG_TAG, "startSqliteActivity");
        Intent intent = new Intent(MainActivity.this, SqliteActivity.class);
        startActivity(intent);
    }

    private void startTaskListActivity() {
        Log.d(LOG_TAG, "startTaskListActivity");
        Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
