package com.example.administrator.dictionary;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Mdialog extends Dialog {


    EditText editText_word,editText_translate;
    public Button cancel;
    public Button ensure;   //定义取消与确定按钮

    //自定义构造方法
    public Mdialog(Context context) {
        super(context,R.style.mdialog);
        //通过LayouInflater来获取布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.mdialoglayout,null);
        editText_word = (EditText) view.findViewById(R.id.editText);
        editText_translate = (EditText)view. findViewById(R.id.editText2);
        cancel = (Button)view. findViewById(R.id.btn_cancel);    //获取取消按钮
        ensure = (Button)view. findViewById(R.id.btn_ensure_1);    //获取确认退出按钮

        setContentView(view);
    }


}
