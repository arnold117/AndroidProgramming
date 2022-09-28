package nchu.bme.savecontact;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CursorAdapter;
import androidx.core.widget.SimpleCursorAdapter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MPermissionsActivity {

    private ListView lv = null;             //显示联系人的列表控件
    private SQLiteDatabase db = null;       //定义数据库
    private List<PersonEntity> list;        //定义用于保存从手机中获取的号码与名字
    DBOpenHelper dbOpenHelper;              //定义DBOpenHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //得到读取通讯录权限
        requestPermission(new String[]{Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.READ_CONTACTS}, 1000);
        setContentView(R.layout.activity_main);
        //创建DBOpenHelper对象,指定名称、版本号并保存在databases目录下
        dbOpenHelper = new DBOpenHelper(MainActivity.this, "my.db", null, 1);
        db = dbOpenHelper.getReadableDatabase();    //得到可读数据库
        list = new ArrayList();                      //用于保存从手机中获取的号码与名字
        lv = (ListView) findViewById(R.id.lv);       //获取显示号码与名字的ListView列表控件
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db != null) {
                    db.delete("tb_info", null, null);        //防止多次插入相同数据，这里先删除上一次数据表
                }
                getPhoneContacts();                           //调用获取电话中联系人名称与号码的方法
                for (int i = 0; i < list.size(); i++) {    //便利从手机中获取的号码与名字然后进行插入数据库
                    String key = list.get(i).getNum();
                    String value = list.get(i).getName();
                    try {
                        insertData(db, key, value);     //调用插入数据的方法
                    } catch (Exception e) {
                        dbOpenHelper = new DBOpenHelper(MainActivity.this, "my.db", null, 1);   //没有数据表就创建数据表
                        insertData(db, key, value);     //调用插入数据的方法
                    }
                }
                Cursor cursor = db.query("tb_info", null, null, null, null, null, null);      //在数据表中查询数据
                inflateListView(cursor);            //调用向ListView中填充数据的方法

            }
        });

    }

    // 向数据库中插入数据
    private void insertData(SQLiteDatabase db, String key, String value) {
        ContentValues values = new ContentValues();
        values.put("key", key);       //保存号码
        values.put("value", value);  //保存名字
        db.insert("tb_info", null, values);   //插入数据

    }

    // 向ListView中填充数据
    @SuppressLint("NewApi")
    public void inflateListView(Cursor cursor) {

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this,
                R.layout.mytextview, cursor, new String[]{"key", "value"},
                new int[]{R.id.listkey, R.id.listvalue},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(adapter);
    }

    /**
     * 界面销毁  关掉数据库
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    /**
     * 该方法用于获取电话中的联系人名称与号码
     */
    private void getPhoneContacts() {
        final String[] PHONES_PROJECTION = new String[]{
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        ContentResolver resolver = getContentResolver();
        try {
            // 获取手机联系人
            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    PHONES_PROJECTION, null, null, null);
            if (phoneCursor != null) {
                while (phoneCursor.moveToNext()) {
                    // 得到手机号码
                    String phoneNumber = phoneCursor
                            .getString(1);
                    // 当手机号码为空的或者为空字段 跳过当前循环
                    if (TextUtils.isEmpty(phoneNumber))
                        continue;
                    // 得到联系人名称
                    String contactName = phoneCursor
                            .getString(0);
                    //创建实体类解析联系人
                    PersonEntity mContact = new PersonEntity(contactName,
                            phoneNumber);
                    list.add(mContact);  //添加到集合里
                }
                phoneCursor.close();  //关流
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}