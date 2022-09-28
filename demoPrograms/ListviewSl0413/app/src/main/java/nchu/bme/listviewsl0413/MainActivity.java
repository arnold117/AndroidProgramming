package nchu.bme.listviewsl0413;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview = (ListView) findViewById(R.id.listview); // 获取列表视图
        int[] imageId = new int[]{R.mipmap.img01, R.mipmap.img02, R.mipmap.img03,
                R.mipmap.img04, R.mipmap.img05, R.mipmap.img06,
                R.mipmap.img07, R.mipmap.img08, R.mipmap.img09,
        }; // 定义并初始化保存图片id的数组
        String[] title = new String[]{"刘一", "陈二", "张三", "李四", "王五",
                "赵六", "孙七", "周八", "吴九"}; // 定义并初始化保存列表项文字的数组
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // 创建一个list集合
        // 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
        for (int i = 0; i < imageId.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>(); // 实例化Map对象
            map.put("image", imageId[i]);
            map.put("名字", title[i]);
            listItems.add(map); // 将map对象添加到List集合中
        }
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,
                R.layout.main, new String[]{"名字", "image"}, new int[]{
                R.id.title, R.id.image}); // 创建SimpleAdapter
        listview.setAdapter(adapter); // 将适配器与ListView关联
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取选择项的值
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, map.get("名字").
                        toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
