package nchu.bme.codetry1109;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends MPermissionsActivity {

    private EditText name;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);              //获取填写联系人的控件
        requestPermission(new String[]{Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.READ_CONTACTS}, 1000);
        Button delete = (Button) findViewById(R.id.delete);     //获取删除按钮
        //删除按钮单击事件
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得到编辑框中输入的联系人
                String nameStr = name.getText().toString();

                //先查询到name对应的联系人的id
                ContentResolver cr = getContentResolver();
                int re = cr.delete(ContactsContract.RawContacts.CONTENT_URI,
                        "display_name = ?",
                        new String[]{nameStr}
                );

                if (re > 0) {
                    Toast.makeText(MainActivity.this, "已将" + nameStr + "从您的通讯录中删除", Toast.LENGTH_SHORT).show();
                    name.setText("");//清空输入框
                } else {
                    Toast.makeText(MainActivity.this, "不存在该联系人", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
