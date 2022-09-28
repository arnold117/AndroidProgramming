package com.example.asus.student;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import StudentDBHelper.StudentDBHelper;
import Student.Student;
//import AddStudentActivity;
import TableContanst.TableContanst;

public class MainActivity extends ListActivity implements
        OnClickListener, OnItemClickListener, OnItemLongClickListener {

    private static final String TAG = "TestSQLite";
    private Button addStudent;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private ListView listView;
    private List<Long> list;
    private RelativeLayout relativeLayout;
    private Button searchButton;
    private Button selectButton;
    private Button deleteButton;
    private Button selectAllButton;
    private Button canleButton;
    private LinearLayout layout;
    private StudentDao dao;
    private Student student;
    private Boolean isDeleteList = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate");
        list = new ArrayList<Long>();
        student = new Student();
        dao = new StudentDao(new StudentDBHelper(this));
        addStudent = (Button) findViewById(R.id.btn_add_student);
        searchButton = (Button) findViewById(R.id.bn_search_id);
        selectButton = (Button) findViewById(R.id.bn_select);
        deleteButton = (Button) findViewById(R.id.bn_delete);
        selectAllButton = (Button) findViewById(R.id.bn_selectall);
        canleButton = (Button) findViewById(R.id.bn_canel);
        layout = (LinearLayout) findViewById(R.id.showLiner);
        relativeLayout=(RelativeLayout) findViewById(R.id.RelativeLayout);
        listView = getListView();

        // 为按键设置监听
        addStudent.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        selectButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        canleButton.setOnClickListener(this);
        selectAllButton.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setOnCreateContextMenuListener(this);

    }

    // 调用load()方法将数据库中的所有记录显示在当前页面
    @Override
    protected void onStart() {
        super.onStart();
        load();

    }

    public  void onClick(View v) {
        // 跳转到添加信息的界面
        if (v == addStudent) {
            startActivity(new Intent(MainActivity.this, AddStudentActivity.class));
        } else if (v == searchButton) {
            // 跳转到查询界面
            startActivity(new Intent(this, StudentSearch.class));
        } else if (v == selectButton) {
            // 跳转到选择界面
            isDeleteList = !isDeleteList;
            if (isDeleteList) {
                checkOrClearAllCheckboxs(true);
            } else {
                showOrHiddenCheckBoxs(false);
            }
        } else if (v == deleteButton) {
            // 删除数据
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    long id = list.get(i);
                    Log.e(TAG, "delete id=" + id);
                    int count = dao.deleteStudentById(id);
                }
                dao.closeDB();
                load();
            }
        } else if (v == canleButton) {
            // 点击取消，回到初始界面
            load();
            layout.setVisibility(View.GONE);
            isDeleteList = !isDeleteList;
        } else if (v == selectAllButton) {
            // 全选，如果当前全选按钮显示是全选，则在点击后变为取消全选，如果当前为取消全选，则在点击后变为全选
            selectAllMethods();
        }
    }

    // 创建菜单
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this); //getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    // 对菜单中的按钮添加响应时间
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        student = (Student) listView.getTag();
        Log.v(TAG, "TestSQLite++++student+" + listView.getTag() + "");
        final long student_id = student.getId();
        Intent intent = new Intent();
        Log.v(TAG, "TestSQLite+++++++id"+student_id);
        switch (item_id) {
            /* 添加
            case R.id.add:
                startActivity(new Intent(this, AddStudentActivity.class));
                break;*/
            // 删除
            case R.id.delete:
                deleteStudentInformation(student_id);
                break;
            case R.id.look:
                // 查看学生信息
                Log.v(TAG, "TestSQLite+++++++look"+student+"");
                intent.putExtra("student", student);
                intent.setClass(this, ShowStudentActivity.class);
                this.startActivity(intent);
                break;
            case R.id.write:
                // 修改学生信息
                intent.putExtra("student", student);
                intent.setClass(this, AddStudentActivity.class);
                this.startActivity(intent);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        Student student = (Student) dao.getStudentFromView(view, id);
        listView.setTag(student);
        registerForContextMenu(listView);
        return false;
    }

    // 点击一条记录是触发的事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        if (!isDeleteList) {
            student = dao.getStudentFromView(view, id);
            Log.e(TAG, "student*****" + dao.getStudentFromView(view, id));
            Intent intent = new Intent();
            intent.putExtra("student", student);
            intent.setClass(this, ShowStudentActivity.class);
            this.startActivity(intent);
        } else {
            CheckBox box = (CheckBox) view.findViewById(R.id.cb_box);
            box.setChecked(!box.isChecked());
            list.add(id);
            deleteButton.setEnabled(box.isChecked());
        }
    }

    // 自定义一个加载数据库中的全部记录到当前页面的无参方法
    public void load() {
        StudentDBHelper studentDBHelper = new StudentDBHelper(
                MainActivity.this);
        SQLiteDatabase database = studentDBHelper.getWritableDatabase();
        cursor = database.query(TableContanst.STUDENT_TABLE, null, null, null,
                null, null, TableContanst.StudentColumns.MODIFY_TIME + " desc");
        startManagingCursor(cursor);
        adapter = new SimpleCursorAdapter(this, R.layout.student_list_item,
                cursor, new String[] { TableContanst.StudentColumns.ID,
                TableContanst.StudentColumns.NAME,
                TableContanst.StudentColumns.AGE,
                TableContanst.StudentColumns.SEX,
                TableContanst.StudentColumns.LIKES,
                TableContanst.StudentColumns.PHONE_NUMBER,
                TableContanst.StudentColumns.TRAIN_DATE }, new int[] {
                R.id.tv_stu_id, R.id.tv_stu_name, R.id.tv_stu_age,
                R.id.tv_stu_sex, R.id.tv_stu_likes, R.id.tv_stu_phone,
                R.id.tv_stu_traindate });
        listView.setAdapter(adapter);
    }

    // 全选或者取消全选
    private void checkOrClearAllCheckboxs(boolean b) {
        int childCount = listView.getChildCount();
        Log.e(TAG, "list child size=" + childCount);
        for (int i = 0; i < childCount; i++) {
            View view = listView.getChildAt(i);
            if (view != null) {
                CheckBox box = (CheckBox) view.findViewById(R.id.cb_box);
                box.setChecked(!b);
            }
        }
        showOrHiddenCheckBoxs(true);
    }

    // 显示或者隐藏自定义菜单
    private void showOrHiddenCheckBoxs(boolean b) {
        int childCount = listView.getChildCount();
        Log.e(TAG, "list child size=" + childCount);
        for (int i = 0; i < childCount; i++) {
            View view = listView.getChildAt(i);
            if (view != null) {
                CheckBox box = (CheckBox) view.findViewById(R.id.cb_box);
                int visible = b ? View.VISIBLE : View.GONE;
                box.setVisibility(visible);
                layout.setVisibility(visible);
                deleteButton.setEnabled(false);
            }
        }
    }

    // 自定义一个利用对话框形式进行数据的删除

    private void deleteStudentInformation(final long delete_id) {
        // 利用对话框的形式删除数据
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("学员信息删除")
                .setMessage("确定删除所选记录?")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int raws = dao.deleteStudentById(delete_id);
                        layout.setVisibility(View.GONE);
                        isDeleteList = !isDeleteList;
                        load();
                        if (raws > 0) {
                            Toast.makeText(MainActivity.this, "删除成功!",
                                    Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(MainActivity.this, "删除失败!",
                                    Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    // 点击全选事件时所触发的响应
    private void selectAllMethods() {
        // 全选，如果当前全选按钮显示是全选，则在点击后变为取消全选，如果当前为取消全选，则在点击后变为全选
        if (selectAllButton.getText().toString().equals("全选")) {
            int childCount = listView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View view = listView.getChildAt(i);
                if (view != null) {
                    CheckBox box = (CheckBox) view.findViewById(R.id.cb_box);
                    box.setChecked(true);
                    deleteButton.setEnabled(true);
                    selectAllButton.setText("取消全选");
                }
            }
        } else if (selectAllButton.getText().toString().equals("取消全选")) {
            checkOrClearAllCheckboxs(true);
            deleteButton.setEnabled(false);
            selectAllButton.setText("全选");
        }
    }
}