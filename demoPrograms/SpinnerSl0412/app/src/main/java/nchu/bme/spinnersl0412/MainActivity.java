package nchu.bme.spinnersl0412;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);   //获取下拉列表
        /****************通过指定适配器的方式为选择列表框指定列表项********************/
//		方法一
//		String[] ctype=new String[]{"全部","电影","图书","唱片","小事","用户","小组","群聊","游戏","活动"}
//		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ctype);
//		方法二
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//				this, R.array.ctype,android.R.layout.simple_dropdown_item_1line);	//创建一个适配器
//
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // 为适配器设置列表框下拉时的选项样式
//		spinner.setAdapter(adapter); // 将适配器与选择列表框关联

        /***************************************************************************/
        //为下拉列表创建监听事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString(); 	//获取选择项的值
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show(); //显示被选中的值
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}