package nchu.bme.alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity{
    private boolean[] checkedItems;//记录各列表项的状态
    private String[] items;//各列表项要显示的内容
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取“显示带取消、确定按钮的对话框”按钮

        Button button1 = (Button) findViewById(R.id.button1);
        // 为“显示带取消、确定按钮的对话框”按钮添加单击事件监听器
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建对话框对象
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setIcon(R.drawable.advise); //设置对话框的图标
                alertDialog.setTitle("乔布斯:");      //设置对话框的标题
                //设置要显示的内容
                alertDialog.setMessage("活着就是为了改变世界，难道还有其他原因吗？");
                //添加取消按钮
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "否",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "您单击了否按钮",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //添加确定按钮
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "您单击了是按钮 ",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                alertDialog.show(); //显示对话框
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);  // 获取“显示带列表的对话框”按钮
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建名言字符串数组
                final String[] items = new String[]{"当你有使命，它会让你更专注", "要么出众，"
                        + "要么出局", "活着就是为了改变世界", "求知若饥，虚心若愚"};
                //创建列表对话框对象
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.advise1);                    //设置对话框的图标
                builder.setTitle("请选择你喜欢的名言：");            //设置对话框的标题
                //添加列表项
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,
                                "您选择了" + items[which], Toast.LENGTH_SHORT).show();

                    }
                });
                builder.create().show();  // 创建对话框并显示
            }
        });
        // 获取“显示带单选列表项的对话框”按钮

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建名字字符串数组
                final String[] items = new String[]{"扎克伯格", "乔布斯", "拉里.埃里森",
                        "安迪.鲁宾", "马云"};
                // 显示带单选列表项的对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.advise2);                      //设置对话框的图标
                builder.setTitle("如果让你选择，你最想做哪一个：");      //设置对话框的标题
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //显示选择结果
                        Toast.makeText(MainActivity.this,
                                "您选择了" + items[which], Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", null);        //添加确定按钮
                builder.create().show();                         // 创建对话框并显示
            }
        });

        // 获取“显示带多选列表项的对话框”按钮
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //记录各列表项的状态
                checkedItems = new boolean[]{false, true, false, true, false};
                //各列表项要显示的内容
                items = new String[]{"开心消消乐", "球球大作战", "欢乐斗地主",
                        "梦幻西游", "超级玛丽"};
                // 显示带单选列表项的对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.advise2);                 //设置对话框的图标
                builder.setTitle("请选择您喜爱的游戏：");           //设置对话框标题
                builder.setMultiChoiceItems(items, checkedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkedItems[which] = isChecked;            //改变被操作列表项的状态
                            }
                        });
                //为对话框添加“确定”按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String result = "";
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {           //当选项被选择时
                                result += items[i] + "、";   //将选项的内容添加到result中
                            }
                        }
                        //当result不为空时，通过消息提示框显示选择的结果
                        if (!"".equals(result)) {
                            //去掉最后面添加的“、”号
                            result = result.substring(0, result.length() - 1);
                            Toast.makeText(MainActivity.this,
                                    "您选择了[ " + result + " ]", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                builder.create().show();                                // 创建对话框并显示
            }
        });
    }
}
