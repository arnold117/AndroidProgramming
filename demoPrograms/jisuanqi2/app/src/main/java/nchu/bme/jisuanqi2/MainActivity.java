package nchu.bme.jisuanqi2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.media.MediaPlayer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Double> value_list = new ArrayList<Double>();// 存用户输入的数字
    private List<Integer> operator_list = new ArrayList<Integer>();// 存用户输入的运算符，定义+为0，-为1，×为2，÷为3
    // 状态记录
    private boolean add_flag = false;// +按下
    private boolean minus_flag = false;// -按下
    private boolean multi_flag = false;// ×按下
    private boolean div_flag = false;// ÷按下
    private boolean result_flag = false;// =按下
    private boolean can_operate_flag = false;// 按下=是否响应

    private TextView textView1;
    private EditText editText1;
    private MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.ling).setOnClickListener(this);
        findViewById(R.id.yi).setOnClickListener(this);
        findViewById(R.id.er).setOnClickListener(this);
        findViewById(R.id.san).setOnClickListener(this);
        findViewById(R.id.si).setOnClickListener(this);
        findViewById(R.id.wu).setOnClickListener(this);
        findViewById(R.id.liu).setOnClickListener(this);
        findViewById(R.id.qi).setOnClickListener(this);
        findViewById(R.id.ba).setOnClickListener(this);
        findViewById(R.id.jiu).setOnClickListener(this);
        findViewById(R.id.point).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.mul).setOnClickListener(this);
        findViewById(R.id.div).setOnClickListener(this);
        findViewById(R.id.result).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        //findViewById(R.id.history).setOnClickListener(this);
        textView1 = (TextView) findViewById(R.id.textview1);
        editText1 = (EditText) findViewById(R.id.num1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.history:
                Intent intent_calc = new Intent(this, MainActivity2.class);
                startActivity(intent_calc);*/
            case R.id.ling:
                num_down("0") ;
                mp= MediaPlayer.create(getApplicationContext(),R.raw.ling);
                mp.start();
                break;
            case R.id.yi:
                num_down("1");
                mp= MediaPlayer.create(getApplicationContext(),R.raw.yi);
                mp.start();
                break;
            case R.id.er:
                num_down("2");
                mp= MediaPlayer.create(getApplicationContext(),R.raw.er);
                mp.start();
                break;
            case R.id.san:
                num_down("3");
                mp= MediaPlayer.create(getApplicationContext(),R.raw.san);
                mp.start();
                break;
            case R.id.si:
                num_down("4");
                mp= MediaPlayer.create(getApplicationContext(),R.raw.si);
                mp.start();
                break;
            case R.id.wu:
                num_down("5");
                mp= MediaPlayer.create(getApplicationContext(),R.raw.wu);
                mp.start();
                break;
            case R.id.liu:
                num_down("6");
                mp= MediaPlayer.create(getApplicationContext(),R.raw.liu);
                mp.start();
                break;
            case R.id.qi:
                num_down("7");
                mp= MediaPlayer.create(getApplicationContext(),R.raw.qi);
                mp.start();
                break;
            case R.id.ba:
                num_down("8");
                mp= MediaPlayer.create(getApplicationContext(),R.raw.ba);
                mp.start();
                break;
            case R.id.jiu:
                num_down("9");
                mp= MediaPlayer.create(getApplicationContext(),R.raw.jiu);
                mp.start();
                break;
            case R.id.point:
                num_down(".");
                mp= MediaPlayer.create(getApplicationContext(),R.raw.point);
                mp.start();
                break;
            case R.id.plus:
                if (!add_flag)// 防止用户多次输入一个符号键，符号键只允许输入一次
                {
                    mp= MediaPlayer.create(getApplicationContext(),R.raw.plus);
                    mp.start();
                    result_flag = false;
                    value_list.add(Double.parseDouble(editText1.getText().toString()));// 将当前已输入的数字放入value_list
                    operator_list.add(0);
                    textView1.setText(textView1.getText() + "+");
                    add_flag = true;
                    can_operate_flag = false;// 刚刚输入完符号，不能构成一条正常的表达式，如111+，设置为不可运行状态
                }
                break;
            case R.id.minus:
                if (!minus_flag) {
                    mp= MediaPlayer.create(getApplicationContext(),R.raw.minus);
                    mp.start();
                    result_flag = false;
                    value_list.add(Double.parseDouble(editText1.getText().toString()));
                    operator_list.add(1);
                    textView1.setText(textView1.getText() + "-");
                    minus_flag = true;
                    can_operate_flag = false;
                }
                break;
            case R.id.mul:
                if (!multi_flag) {
                    mp= MediaPlayer.create(getApplicationContext(),R.raw.mul);
                    mp.start();
                    result_flag = false;
                    value_list.add(Double.parseDouble(editText1.getText().toString()));
                    operator_list.add(2);
                    textView1.setText("(" + textView1.getText() + ")×");// 给前面的已经输入的东西加个括号。（运算符栈问题是一个很复杂的数据结构问题，这里不做，：P）
                    multi_flag = true;
                    can_operate_flag = false;
                }
                break;
            case R.id.div:
                if (!div_flag) {
                    mp= MediaPlayer.create(getApplicationContext(),R.raw.div);
                    mp.start();
                    result_flag = false;
                    value_list.add(Double.parseDouble(editText1.getText().toString()));
                    operator_list.add(3);
                    textView1.setText("(" + textView1.getText() + ")÷");
                    div_flag = true;
                    can_operate_flag = false;
                }
                break;
            case R.id.result:
                if (value_list.size() > 0 && operator_list.size() > 0 && can_operate_flag) {// 需要防止用户没输入数字，或者只输入了一个数，就按=。
                    if(value_list.get(0)==0){
                        Toast.makeText(MainActivity.this, "除数不能为零",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        value_list.add(Double.parseDouble(editText1.getText().toString()));
                        double total = value_list.get(0);
                        for (int i = 0; i < operator_list.size(); i++) {
                            int _operator = operator_list.get(i);// operator是C#的运算符重载的关键字，前面加个_来区别
                            switch (_operator) {
                                case 0:
                                    total += value_list.get(i + 1);
                                    break;
                                case 1:
                                    total -= value_list.get(i + 1);
                                    break;
                                case 2:
                                    total *= value_list.get(i + 1);
                                    break;
                                case 3:
                                    total /= value_list.get(i + 1);
                                    break;
                            }
                        }
                        new Thread(new Runnable() {
                            public void run() {
                                mp = MediaPlayer.create(getApplicationContext(), R.raw.result);
                                mp.start();
                            }
                        }).start();
                        editText1.setText(total + "");
                        textView1.setText(total + "");
                        if(total<0) {
                            new Thread(new Runnable() {
                                public void run() {
                                    try {
                                        Thread.sleep(600);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    mp = MediaPlayer.create(getApplicationContext(), R.raw.fu);
                                    mp.start();
                                }
                            }).start();
                            new Thread(new Runnable() {
                                public void run() {
                                    String tom = textView1.getText().toString();
                                    int position = tom.indexOf(".");
                                    for (int k = 1; k < position; k++) {
                                        String s = tom.substring(k, k + 1);
                                        int x = Integer.parseInt(s);
                                        switch (x) {
                                            case 0:
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.ling);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 1:
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.yi);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 2:
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.er);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 3:
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.san);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 4:
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.si);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 5:
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.wu);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 6:
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.liu);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 7:
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.qi);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 8:
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.ba);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 9:
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.jiu);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                        }
                                        if (k == 0) {
                                            if (position == 2) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.shi);
                                                mp.start();
                                            }
                                            if (position == 3) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.bai);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            if (position == 4) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.qian);
                                                mp.start();
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        if (k == 1) {
                                            if (position == 4) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.bai);
                                                mp.start();
                                            }
                                            if (position == 3) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.shi);
                                                mp.start();
                                            }
                                        }
                                        if (k == 2) {
                                            if (position == 4) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.shi);
                                                mp.start();
                                            }
                                        }
                                    }
                                }
                            }).start();
                        }else {
                            new Thread(new Runnable() {
                                public void run() {
                                    String tom = textView1.getText().toString();
                                    int position = tom.indexOf(".");
                                    for (int k = 0; k < position; k++) {
                                        String s = tom.substring(k, k + 1);
                                        int x = Integer.parseInt(s);
                                        switch (x) {
                                            case 0:
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.ling);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 1:
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.yi);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 2:
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.er);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 3:
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.san);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 4:
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.si);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 5:
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.wu);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 6:
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.liu);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 7:
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.qi);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 8:
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.ba);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case 9:
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.jiu);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                        }
                                        if (k == 0) {
                                            if (position == 2) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.shi);
                                                mp.start();
                                            }
                                            if (position == 3) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.bai);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            if (position == 4) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.qian);
                                                mp.start();
                                                try {
                                                    Thread.sleep(600);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        if (k == 1) {
                                            if (position == 4) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.bai);
                                                mp.start();
                                            }
                                            if (position == 3) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.shi);
                                                mp.start();
                                            }
                                        }
                                        if (k == 2) {
                                            if (position == 4) {
                                                mp = MediaPlayer.create(getApplicationContext(), R.raw.shi);
                                                mp.start();
                                            }
                                        }
                                    }
                                }
                            }).start();
                        }
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(6000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                mp = MediaPlayer.create(getApplicationContext(), R.raw.point);
                                mp.start();
                            }
                        }).start();
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(6500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                String tom = textView1.getText().toString();
                                int position = tom.indexOf(".");
                                for(int k = position+1; k< tom.length(); k++) {
                                    String s = tom.substring(k, k + 1);
                                    int x = Integer.parseInt(s);
                                    switch (x) {
                                        case 0:
                                            mp = MediaPlayer.create(getApplicationContext(), R.raw.ling);
                                            mp.start();
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 1:
                                            mp = MediaPlayer.create(getApplicationContext(), R.raw.yi);
                                            mp.start();
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 2:
                                            mp = MediaPlayer.create(getApplicationContext(), R.raw.er);
                                            mp.start();
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 3:
                                            mp = MediaPlayer.create(getApplicationContext(), R.raw.san);
                                            mp.start();
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 4:
                                            mp = MediaPlayer.create(getApplicationContext(), R.raw.si);
                                            mp.start();
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 5:
                                            mp = MediaPlayer.create(getApplicationContext(), R.raw.wu);
                                            mp.start();
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 6:
                                            mp = MediaPlayer.create(getApplicationContext(), R.raw.liu);
                                            mp.start();
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 7:
                                            mp = MediaPlayer.create(getApplicationContext(), R.raw.qi);
                                            mp.start();
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 8:
                                            mp = MediaPlayer.create(getApplicationContext(), R.raw.ba);
                                            mp.start();
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 9:
                                            mp = MediaPlayer.create(getApplicationContext(), R.raw.jiu);
                                            mp.start();
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                    }
                                }
                            }
                        }).start();
                        operator_list.clear();// 算完，就清空累积数字与运算数组
                        value_list.clear();
                        result_flag = true;// 表示=按下
                    }
                }
                break;
            case R.id.clear:
                mp= MediaPlayer.create(getApplicationContext(),R.raw.clear);
                mp.start();
                operator_list.clear();
                value_list.clear();
                add_flag = false;
                minus_flag = false;
                multi_flag = false;
                div_flag = false;
                result_flag = false;
                can_operate_flag = false;
                editText1.setText("");
                textView1.setText("");
                break;
            case R.id.delete:
                String str=textView1.getText().toString();
                String str1=editText1.getText().toString();
                textView1.setText(str.substring(0,str.length()-1));
                editText1.setText(str1.substring(0,str.length()-1));
                break;
        }
    }

    // 数字键按下，含0与.，类似000001223这类情况这里允许，因为java可以讲000001223自己转化为1223
    private void num_down(String num) {
        if (add_flag ||minus_flag || multi_flag || div_flag || result_flag) {
            if (result_flag)// 按下等号，刚刚算完一个运算的状态
            {
                textView1.setText("");
            }
            editText1.setText("");// 如果用户刚刚输入完一个运算符
            add_flag = false;
            minus_flag = false;
            multi_flag = false;
            div_flag = false;
            result_flag = false;
        }
        if ((num.equals(".") && editText1.getText().toString().indexOf(".") < 0)
                || !num.equals(".")) {
            // 如果用户输入的是小数点.，则要判断当前已输入的数字中是否含有小数点.才允许输入
            editText1.setText(editText1.getText() + num);
            textView1.setText(textView1.getText() + num);
            can_operate_flag = true;
        }
    }
}