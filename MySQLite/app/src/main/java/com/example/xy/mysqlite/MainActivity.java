package com.example.xy.mysqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String LOG_TAG = "MainActivity";
    private SQLiteDatabase db;
    private String db_name = "xy.sqlite";
    private String table_name = "pic";
    final DBHelper helper = new DBHelper(this, db_name, null, 1);
    private View.OnClickListener  mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(LOG_TAG, "View.OnClickListener onClick");
            ContentValues cv = new ContentValues();
            switch (v.getId()) {
                //添加按钮
                case R.id.Button01:
                    cv.put("fileName", "pic5.jpg");
                    cv.put("description", "图片5");
                    //添加方法
                    long long1 = db.insert("pic", "", cv);
                    //添加成功后返回行号，失败后返回-1
                    if (long1 == -1) {
                        Toast.makeText(MainActivity.this,
                                "ID是" + long1 + "的图片添加失败！", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        Toast.makeText(MainActivity.this,
                                "ID是" + long1 + "的图片添加成功！", Toast.LENGTH_SHORT)
                                .show();
                    }
                    //更新下拉列表
                    updateSpinner();
                    break;

                //删除描述是'图片5'的数据行
                case R.id.Button02:
                    //删除方法
                    long long2 = db.delete("pic", "description='图片5'", null);
                    //删除失败返回0，成功则返回删除的条数
                    Toast.makeText(MainActivity.this, "删除了" + long2 + "条记录",
                            Toast.LENGTH_SHORT).show();
                    //更新下拉列表
                    updateSpinner();
                    break;

                //更新文件名是'pic5.jpg'的数据行
                case R.id.Button03:

                    cv.put("fileName", "pic0.jpg");
                    cv.put("description", "图片0");
                    //更新方法
                    int long3 = db.update("pic", cv, "fileName='pic5.jpg'", null);
                    //删除失败返回0，成功则返回删除的条数
                    Toast.makeText(MainActivity.this, "更新了" + long3 + "条记录",
                            Toast.LENGTH_SHORT).show();
                    //更新下拉列表
                    updateSpinner();
                    break;

                //查询当前所有数据
                case R.id.Button04:
                    Cursor c = db.query("pic", null, null, null, null,
                            null, null);
                    //cursor.getCount()是记录条数
                    Toast.makeText(MainActivity.this,
                            "当前共有" + c.getCount() + "条记录，下面一一显示：",
                            Toast.LENGTH_SHORT).show();
                    //循环显示
                    for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
                        Toast.makeText(MainActivity.this,
                                "第"+ c.getInt(0)  +"条数据，文件名是" + c.getString(1) + "，描述是"+c.getString(2),
                                Toast.LENGTH_SHORT).show();
                    }
                    //更新下拉列表
                    updateSpinner();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
        setContentView(R.layout.activity_my_sqlite);

        startMySQLite();
    }

    private void startMySQLite() {
        Log.d(LOG_TAG, "startMySQLite");

        Button b1 = (Button) findViewById(R.id.Button01);
        Button b2 = (Button) findViewById(R.id.Button02);
        Button b3 = (Button) findViewById(R.id.Button03);
        Button b4 = (Button) findViewById(R.id.Button04);

        b1.setOnClickListener(mOnClickListener);
        b2.setOnClickListener(mOnClickListener);
        b3.setOnClickListener(mOnClickListener);
        b4.setOnClickListener(mOnClickListener);

        Log.d(LOG_TAG, "db: getWritableDatabase");
        db = helper.getWritableDatabase();
        initDatabase(db);
        updateSpinner();
    }

    private void initDatabase(SQLiteDatabase db) {
        Log.d(LOG_TAG, "initDatabase");
        ContentValues cv = new ContentValues();

        cv.put("fileName", "pic1.jpg");
        cv.put("description", "图片1");
        db.insert(table_name, "", cv);

        cv.put("fileName", "pic2.jpg");
        cv.put("description", "图片2");
        db.insert(table_name, "", cv);

        cv.put("fileName", "pic3.jpg");
        cv.put("description", "图片3");
        db.insert(table_name, "", cv);

        cv.put("fileName", "pic4.jpg");
        cv.put("description", "图片4");
        db.insert(table_name, "", cv);
    }

    private void updateSpinner() {
        Log.d(LOG_TAG, "updateSpinner");

        //定义UI组件
        final TextView tv = (TextView) findViewById(R.id.TextView02);
        Spinner s = (Spinner) findViewById(R.id.Spinner01);

        //从数据库中获取数据放入游标Cursor对象
        final Cursor cursor = db.query("pic", null, null, null, null, null,
                null);
        Log.d(LOG_TAG, "get cursor: " + cursor);

        //创建简单游标匹配器
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item, cursor, new String[] {
                "fileName", "description" }, new int[] {
                android.R.id.text1, android.R.id.text2 });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //给下拉列表设置匹配器
        s.setAdapter(adapter);

        //定义子元素选择监听器
        AdapterView.OnItemSelectedListener oisl = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.d(LOG_TAG, "AdapterView.OnItemSelectedListener onItemSelected");
                cursor.moveToPosition(position);
                tv.setText("当前pic的描述为：" + cursor.getString(2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(LOG_TAG, "AdapterView.OnItemSelectedListener onNothingSelected");
            }
        };

        //给下拉列表绑定子元素选择监听器
        s.setOnItemSelectedListener(oisl);
    }

    //窗口销毁时删除表中数据
    @Override
    public void onDestroy() {
        super.onDestroy();
        db.delete(table_name, null, null);
        updateSpinner();
    }
}
