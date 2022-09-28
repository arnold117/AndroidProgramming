package nchu.bme.phonering;
import android.media.AudioAttributes;
import android.media.SoundPool;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview = (ListView) findViewById(R.id.listView);   //获取列表视图
        String[] title = new String[]{"布谷鸟叫声", "风铃声", "门铃声", "电话声", "鸟叫声",
                "水流声", "公鸡叫声"};               // 定义并初始化保存列表项文字的数组
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();  //创建一个list集合
        //通过for循环将列表项文字放到Map中，并添加到list集合中
        for (int i = 0; i < title.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();             //实例化Map对象
            map.put("name", title[i]);
            listItems.add(map);                                     //将map对象添加到List集合中
        }

        AudioAttributes attr = new AudioAttributes.Builder()           //设置音效相关属性
                .setUsage(AudioAttributes.USAGE_GAME)                 // 设置音效使用场景
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)  // 设置音效的类型
                .build();
        final SoundPool soundpool = new SoundPool.Builder()           // 创建SoundPool对象
                .setAudioAttributes(attr) // 设置音效池的属性
                .setMaxStreams(10) // 设置最多可容纳10个音频流，
                .build();

        //创建一个HashMap对象,将要播放的音频流保存到HashMap对象中
        final HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>();
        soundmap.put(0, soundpool.load(this, R.raw.cuckoo, 1));
        soundmap.put(1, soundpool.load(this, R.raw.chimes, 1));
        soundmap.put(2, soundpool.load(this, R.raw.notify, 1));
        soundmap.put(3, soundpool.load(this, R.raw.ringout, 1));
        soundmap.put(4, soundpool.load(this, R.raw.bird, 1));
        soundmap.put(5, soundpool.load(this, R.raw.water, 1));
        soundmap.put(6, soundpool.load(this, R.raw.cock, 1));

        //创建SimpleAdapter适配器并将适配器与ListView关联
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,
                R.layout.main, new String[]{"name",}, new int[]{
                R.id.title}); // 创建SimpleAdapter
        listview.setAdapter(adapter); // 将适配器与ListView关联

        //为ListView设置事件监听器，为每个选项设置所对应要播放的音频
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取选项的值
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                soundpool.play(soundmap.get(position), 1, 1, 0, 0, 1);  //播放所选音频
            }
        });

    }
}
