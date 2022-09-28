package com.example.administrator.dictionary.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.dictionary.R;
import com.example.administrator.dictionary.database.DBResult;
import com.example.administrator.dictionary.database.DBWords;
import com.example.administrator.dictionary.info.Word;
import com.example.administrator.dictionary.util.ToastUtil;



public class FragmentA extends android.support.v4.app.Fragment {

    /**
     * 创建控件
     */
    String title;

    public String getTitle() {
        return title;
    }

    TextView textView;
    EditText editText;
    Button button_ensure, button_delete;
    DBWords dbWords;
    DBResult dbResult;
    int wordscount = 0;
    int number = 0;
    Word word = null;
    int count = 0;
    int right = 0; //正确数量
    int wrong = 0; //错误数量
    int delete = 0; //删除数量

    public FragmentA(String title)
    {
        super();
        this.title = title;;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getContext();
        textView = (TextView) view.findViewById(R.id.tv_word_1);
        editText = (EditText) view.findViewById(R.id.et_translate);
        button_ensure = (Button) view.findViewById(R.id.btn_ensure);
        button_delete = (Button) view.findViewById(R.id.btn_delete);
        dbWords = new DBWords(getActivity(), "tb_words", null, 1);
        dbResult = new DBResult(getActivity(), "tb_result", null, 1);

        setButtonListener();
        Test();
    }

    /**
     * 随机取单词测试
     */
    public void Test() {
        if (count < 10) {
            wordscount = dbWords.getCount();
            Log.d(wordscount+"", "Test: 1111");
            number = (int) (wordscount * Math.random());
            word = dbWords.getWord(number);
            textView.setText(word.getWord());
            count++;
        } else {
            ifBackToFirst();
        }
    }

    /**
     * 设置按钮的监听
     */
    public void setButtonListener() {
        /**
         * 设置确定按钮的监听
         * 判断是否正确
         */
        button_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (word.getTranslate().equals(editText.getText().toString())) {
                    ToastUtil.showMsg(getActivity(), "回答正确！");
                    right++;
                    editText.setText("");
                } else {
                    ToastUtil.showMsg(getActivity(), "回答错误！");
                    wrong++;
                    editText.setText("");
                }
                Test();
            }
        });

        /**
         * 设置删除按钮的监听
         * 点击删除单词
         */
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbWords.deleteWord(number+"");
                textView.setText("");
                editText.setText("");
                delete++;
                Test();
            }
        });
    }

    /**
     * 判断是否再来一组
     * 选“是”则再来一组
     * 选“否”则停止背词
     */
    public void ifBackToFirst() {
        dbResult.writeData(dbResult.getReadableDatabase(), "正确：" + right, "错误：" + wrong, "删除：" + delete);
        wrong = 0;
        delete = 0;
        right = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("已经背到最后了").setMessage("是否再来一组？")
                .setIcon(R.drawable.icon_image)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        count = 0;
                        Test();
                    }
                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ToastUtil.showMsg(getActivity(), "已背到完一组啦！");
                textView.setText("");
                editText.setText("");
            }
        }).show();
    }
}
