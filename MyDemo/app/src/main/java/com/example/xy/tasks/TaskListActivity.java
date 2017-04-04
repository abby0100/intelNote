package com.example.xy.tasks;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xy.NdkJniUtil;
import com.example.xy.R;

public class TaskListActivity extends Activity {


    private static final String LOG_TAG = "TaskListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Log.d(LOG_TAG, "onCreate");

        startTasks();
    }

    private void startTasks() {
        Log.d(LOG_TAG, "startTasks");
        ListView jniTV = (ListView) findViewById(R.id.list_task);

    }
}
